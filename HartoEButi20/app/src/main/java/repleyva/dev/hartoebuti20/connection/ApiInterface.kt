package repleyva.dev.hartoebuti20.connection

import repleyva.dev.hartoebuti20.model.OrderData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("{pedido}")
    fun getOrders(@Path("pedido") pedido: String): Call<ArrayList<OrderData>>

    companion object {

        var BASE_URL = "http://192.168.38.244/api/"
        private var INSTANCE = ApiInterface

        fun getInstance(): Companion {
            if (INSTANCE == null) {
                INSTANCE = ApiInterface
            }
            return INSTANCE
        }

        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}