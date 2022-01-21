package app.wcrtv.littleconverter.ui.currencyratelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import app.wcrtv.littleconverter.data.network.RetrofitService
import app.wcrtv.littleconverter.databinding.FragmentCurrencyRateListBinding
import app.wcrtv.littleconverter.extension.CustomViewModelFactory
import app.wcrtv.littleconverter.repository.Repository

class CurrencyRateListFragment : Fragment() {
    private var _binding: FragmentCurrencyRateListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CurrencyRateListViewModel
    private var rv: RecyclerView? = null
    private var rateListAdapter = RateListAdapter()
    private val retrofitService = RetrofitService.getInstance()

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

        viewModel.getErrorMessage().observe(viewLifecycleOwner, {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun initRateList() {
        binding.currencyRateList.adapter = rateListAdapter
        binding.currencyRateList.setHasFixedSize(true)

        viewModel.getDailyRates().observe(viewLifecycleOwner, {
            rateListAdapter.setValuteList(it)
        })

        viewModel.getLatestDailyRates()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}