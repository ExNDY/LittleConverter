package app.wcrtv.littleconverter.ui.currencyratelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.wcrtv.littleconverter.databinding.FragmentCurrencyRateListBinding

class CurrencyRateListFragment : Fragment() {
/* May be delete after test
    companion object {
        fun newInstance() = CurrencyRateListFragment()
    }
    */

    private val viewModel: CurrencyRateListViewModel by viewModels()
    private var rateListView: RecyclerView? = null
    private var rateListAdapter:RateListAdapter? = null
    private lateinit var binding : FragmentCurrencyRateListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrencyRateListBinding.inflate(inflater, container, false)

        initRateList()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun initRateList(){
        rateListView = binding.currencyRateList
        rateListView?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rateListView?.setHasFixedSize(true)
        rateListAdapter = context?.let { RateListAdapter() }
        rateListView?.adapter = rateListAdapter

        viewModel.getRateList().observe(viewLifecycleOwner, {rateList ->
            if (rateList != null) {
                rateListAdapter?.updateDataSet(rateList)
            }
        })
    }
}