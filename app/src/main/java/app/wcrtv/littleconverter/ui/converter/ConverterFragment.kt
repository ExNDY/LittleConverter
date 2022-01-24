package app.wcrtv.littleconverter.ui.converter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import app.wcrtv.littleconverter.databinding.FragmentConverterBinding
import app.wcrtv.littleconverter.ui.changevalutedialog.ChangeValuteDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ConverterFragment : Fragment() {

    companion object {
        fun newInstance() = ConverterFragment()
    }

    private val viewModel: ConverterViewModel by viewModels()
    private var _binding: FragmentConverterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConverterBinding.inflate(inflater, container, false)

        if (savedInstanceState == null) {
            context?.let { it ->
                viewModel.restoreData(it)!!.observe(this, {
                    viewModel.restoreFromData(it)
                })
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFromField()

        initButton()

        viewModel.getErrorMessage().observe(this, {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.getFromFieldValue().observe(this, {
            binding.fromEditText.setText(it)
        })

        viewModel.getChosenCurrency().observe(this, {
            binding.changeToCurrencyButton.text = it.charCode
        })

        viewModel.getToFieldValue().observe(this, {
            binding.toEditText.setText(it)
        })
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null){
            binding.fromEditText.setText(savedInstanceState.getString("fromFieldValue"))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("fromFieldValue", binding.fromEditText.text.toString())
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun initFromField() {
        binding.fromEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                CoroutineScope(IO).launch {
                    delay(150)
                    viewModel.calc(p0.toString())
                }
            }

        })
    }

    private fun initButton() {
        binding.button0.setOnClickListener {
            viewModel.inputNum(0)
        }
        binding.button1.setOnClickListener {
            viewModel.inputNum(1)
        }
        binding.button2.setOnClickListener {
            viewModel.inputNum(2)
        }
        binding.button3.setOnClickListener {
            viewModel.inputNum(3)
        }
        binding.button4.setOnClickListener {
            viewModel.inputNum(4)
        }
        binding.button5.setOnClickListener {
            viewModel.inputNum(5)
        }
        binding.button6.setOnClickListener {
            viewModel.inputNum(6)
        }
        binding.button7.setOnClickListener {
            viewModel.inputNum(7)
        }
        binding.button8.setOnClickListener {
            viewModel.inputNum(8)
        }
        binding.button9.setOnClickListener {
            viewModel.inputNum(9)
        }
        binding.buttonDot.setOnClickListener {
            viewModel.inputDot()
        }
        binding.buttonDel.setOnClickListener {
            viewModel.inputDel()
        }

        binding.changeToCurrencyButton.setOnClickListener {
            changeToValute()
        }
    }

    private fun changeToValute() {
        val changeToCurrencyDialog = ChangeValuteDialogFragment.newInstance()
        val transaction = childFragmentManager.beginTransaction()

        childFragmentManager.setFragmentResultListener("newIndexTo", this) {_, bundle ->
            val newIndex = bundle.getInt("indexTo")

            viewModel.changeChosenValute(newIndex)

            binding.toEditText.setText("")
        }

        changeToCurrencyDialog.show(transaction, "ChangeDialog")
    }
}