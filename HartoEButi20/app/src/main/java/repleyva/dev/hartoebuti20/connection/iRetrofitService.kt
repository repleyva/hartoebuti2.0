package repleyva.dev.hartoebuti20.connection

import repleyva.dev.hartoebuti20.model.OrderModel
import retrofit2.Call
import retrofit2.http.GET

interface iRetrofitService {

    @GET("api/db.json")
    fun getDataFromAPI() : Call<OrderModel>

}