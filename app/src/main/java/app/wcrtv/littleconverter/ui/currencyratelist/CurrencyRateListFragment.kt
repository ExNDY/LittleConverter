package app.wcrtv.littleconverter.ui.currencyratelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import app.wcrtv.littleconverter.data.network.retrofit.RetrofitService
import app.wcrtv.littleconverter.databinding.FragmentCurrencyRateListBinding
import app.wcrtv.littleconverter.extension.CustomViewModelFactory
import app.wcrtv.littleconverter.repository.Repository

class CurrencyRateListFragment : Fragment() {

    companion object {
        fun newInstance() = CurrencyRateListFragment()
    }

    private lateinit var viewModel: CurrencyRateListViewModel
    private var rateListAdapter: RateListAdapter? = null
    private val retrofitService = RetrofitService.getInstance()
    private var wasLoaded = false
    private var _binding: FragmentCurrencyRateListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            CustomViewModelFactory(Repository(retrofitService))
        )[CurrencyRateListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrencyRateListBinding.inflate(inflater, container, false)

        initRateList()
        initSwipeToRefresh()

        viewModel.getErrorMessage().observe(viewLifecycleOwner, {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

        if (savedInstanceState == null) {
            context?.let { it ->
                viewModel.restoreData(it)!!.observe(this, {
                    if (it != null) {
                        viewModel.restoreFromData(it)
                    }
                }) }

            context?.let { viewModel.loadLatestData(it) }
        }

        return binding.root
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null){
            wasLoaded = savedInstanceState.getBoolean("wasLoaded")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("wasLoaded", wasLoaded)
    }

    private fun initSwipeToRefresh() {
        binding.swipeToRefresh.setOnRefreshListener {
            context?.let { viewModel.loadLatestData(it) }
        }
    }

    private fun initRateList() {
        rateListAdapter = context?.let { RateListAdapter(it) }
        rateListAdapter?.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        binding.currencyRateList.adapter = rateListAdapter
        binding.currencyRateList.setHasFixedSize(true)

        viewModel.getValutesList().observe(viewLifecycleOwner, {
            if (binding.swipeToRefresh.isRefreshing) {
                binding.swipeToRefresh.isRefreshing = false
            }

            wasLoaded = true

            rateListAdapter?.setValuteList(it)
        })
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}