package repleyva.dev.hartoebuti20.viewmodel

import androidx.lifecycle.MutableLiveData
import repleyva.dev.hartoebuti20.model.OrderData

class RecyclerOrderViewModel {
    private var recyclerListData: MutableLiveData<OrderData> = MutableLiveData()
    fun getListDataObserver(): MutableLiveData<OrderData> {
        return recyclerListData
    }

    fun makeApiCall() {
        //val retroInstance = RetroInstance
    }
}