package app.wcrtv.littleconverter.ui.currencyratelist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.wcrtv.littleconverter.data.network.model.Valute
import app.wcrtv.littleconverter.databinding.RateListItemBinding
import java.util.ArrayList

class RateListAdapter : RecyclerView.Adapter<RateListAdapter.ModelHolder>() {
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
            bindDifferenceValue(valute.value, valute.previous)
        }

        private fun bindCharCode(charCode: String){
            itemBinding.charCode.text = charCode
        }

        private fun bindName(name: String){
            itemBinding.currencyName.text = name
        }

        private fun bindNominal(nominal: Int){
            itemBinding.nominal.text = nominal.toString()
        }

        private fun bindValue(value: Double){
            itemBinding.currencyValue.text = value.toString()
        }

        private fun bindDifferenceValue(value: Double, previous: Double){
            val diff = value - previous

            itemBinding.difference.text = diff.toString()
        }
    }
}