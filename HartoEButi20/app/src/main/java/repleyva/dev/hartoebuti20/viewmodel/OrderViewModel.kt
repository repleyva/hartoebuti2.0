package repleyva.dev.hartoebuti20.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import repleyva.dev.hartoebuti20.model.OrderData

class OrderViewModel: ViewModel() {
    val order = MutableLiveData<OrderData?>()
    /*val orderAdd = MutableLiveData<OrderData?>()
    val orderLess = MutableLiveData<OrderData?>()*/

    fun setOrderActually (order: OrderData?) {
        this.order.postValue(order)
    }

   /* fun setAdd (orderAdd: OrderData?) {
        this.orderAdd.postValue(orderAdd)
    }

    fun setLess (orderLess: OrderData?) {
        this.orderLess.postValue(orderLess)
    }*/
}