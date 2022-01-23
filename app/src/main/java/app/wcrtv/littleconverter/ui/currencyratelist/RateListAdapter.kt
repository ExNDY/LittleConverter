package app.wcrtv.littleconverter.ui.currencyratelist

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import app.wcrtv.littleconverter.R
import app.wcrtv.littleconverter.model.Valute
import app.wcrtv.littleconverter.databinding.RateListItemBinding
import app.wcrtv.littleconverter.utils.TextUtils
import java.util.*

class RateListAdapter(private val context: Context) : RecyclerView.Adapter<RateListAdapter.ModelHolder>() {
    private var valuteList: ArrayList<Valute> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelHolder {
        val itemBinding = RateListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ModelHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ModelHolder, position: Int) {
        val valute = valuteList[position]

        holder.bind(valute)
    }

    override fun getItemCount(): Int {
        return valuteList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setValuteList(valuteList: ArrayList<Valute>){
        this.valuteList = valuteList

        notifyDataSetChanged()
    }

    inner class ModelHolder(private val itemBinding: RateListItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(valute: Valute) {
            bindCharCode(valute.charCode)
            bindName(valute.name)
            bindNominal(valute.nominal)
            bindValue(valute.value)
            bindDifferenceValue(valute.getDifference())

            itemBinding.container.setOnClickListener{
                Toast.makeText(context, valute.name, Toast.LENGTH_SHORT).show()
            }
        }

        private fun bindCharCode(charCode: String){
            itemBinding.charCode.text = charCode
        }

        private fun bindName(name: String){
            itemBinding.currencyName.text = name
        }

        private fun bindNominal(nominal: Int){
            val stringValue = context.resources.getString(R.string.nominal) + nominal
            itemBinding.nominal.text = stringValue
        }

        private fun bindValue(value: Double){
            val stringValue = TextUtils.formattingDifferenceValue(value) + context.resources.getString(R.string.rouble)

            itemBinding.currencyValue.text = stringValue
        }

        private fun bindDifferenceValue(value: Double){
            if (value > 0) {
                itemBinding.difference.setTextColor(ContextCompat.getColor(context, R.color.differenceColorGreen))
            }

            if (value < 0) {
                itemBinding.difference.setTextColor(ContextCompat.getColor(context, R.color.differenceColorRed))
            }

            val formattedText = "(" + TextUtils.formattingDifferenceValue(value) + ")"

            itemBinding.difference.text = formattedText
        }
    }
}