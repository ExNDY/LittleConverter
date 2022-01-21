package app.wcrtv.littleconverter.extension

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.wcrtv.littleconverter.repository.Repository
import app.wcrtv.littleconverter.ui.currencyratelist.CurrencyRateListViewModel

class CustomViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CurrencyRateListViewModel::class.java)) {
            CurrencyRateListViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}