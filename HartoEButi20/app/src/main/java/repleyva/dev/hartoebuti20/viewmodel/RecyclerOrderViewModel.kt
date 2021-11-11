package repleyva.dev.hartoebuti20.viewmodel

import androidx.lifecycle.MutableLiveData
import repleyva.dev.hartoebuti20.model.OrderModel

class RecyclerOrderViewModel {
    private var recyclerListData: MutableLiveData<OrderModel> = MutableLiveData()
    fun getListDataObserver(): MutableLiveData<OrderModel> {
        return recyclerListData
    }

    fun makeApiCall() {
        //val retroInstance = RetroInstance
    }
}