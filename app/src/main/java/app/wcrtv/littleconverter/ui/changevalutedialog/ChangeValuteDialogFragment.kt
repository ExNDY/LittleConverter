package app.wcrtv.littleconverter.ui.changevalutedialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import app.wcrtv.littleconverter.databinding.FragmentChangeValuteDialogBinding
import app.wcrtv.littleconverter.model.Valute
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*
import kotlin.concurrent.schedule

class ChangeValuteDialogFragment : BottomSheetDialogFragment(), ChangeRateAdapterListener {
    private var changeRateListAdapter: ChangeRateListAdapter? = null

    private var _binding: FragmentChangeValuteDialogBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ChangeValuteDialogViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChangeValuteDialogBinding.inflate(inflater, container, false)

        initRateList()

        context?.let { it ->
            viewModel.restoreData(it)!!.observe(this, {
                if (it != null) {
                    viewModel.restoreFromData(it)
                }
            }) }

        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun initRateList(){
        changeRateListAdapter = context?.let { ChangeRateListAdapter(this) }
        binding.currencyRateList.adapter = changeRateListAdapter
        binding.currencyRateList.setHasFixedSize(true)

        viewModel.getAllValutesList().observe(viewLifecycleOwner, {
            changeRateListAdapter?.setValuteList(it)
        })


    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ChangeValuteDialogFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onCurrencyRateClick(position: Int, valute: Valute) {
        setFragmentResult("newIndexTo", bundleOf("indexTo" to position))

        Toast.makeText(context, "Валюта выбрана: " + valute.name, Toast.LENGTH_SHORT).show()

        dismiss()
    }
}