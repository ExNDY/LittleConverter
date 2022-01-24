package app.wcrtv.littleconverter.ui.changevalutedialog

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.wcrtv.littleconverter.databinding.ChangeRateListItemBinding
import app.wcrtv.littleconverter.model.Valute
import java.util.*

class ChangeRateListAdapter(private val clickListener: ChangeRateAdapterListener) : RecyclerView.Adapter<ChangeRateListAdapter.ModelHolder>() {
    private var valuteList: ArrayList<Valute> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelHolder {
        val itemBinding = ChangeRateListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ModelHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ModelHolder, position: Int) {
        val valute = valuteList[position]

        holder.bind(valute)

        holder.itemView.setOnClickListener {
            clickListener.onCurrencyRateClick(position, valuteList[position])
        }
    }

    override fun getItemCount(): Int {
        return valuteList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setValuteList(valuteList: ArrayList<Valute>){
        this.valuteList = valuteList

        notifyDataSetChanged()
    }

    inner class ModelHolder(private val itemBinding: ChangeRateListItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(valute: Valute) {
            bindCharCode(valute.charCode)
            bindName(valute.name)
        }

        private fun bindCharCode(charCode: String){
            itemBinding.charCode.text = charCode
        }

        private fun bindName(name: String){
            itemBinding.currencyName.text = name
        }
    }
}

interface ChangeRateAdapterListener{
    fun onCurrencyRateClick(position: Int, valute: Valute)
}