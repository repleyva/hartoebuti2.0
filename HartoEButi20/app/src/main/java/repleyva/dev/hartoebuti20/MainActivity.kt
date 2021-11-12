package repleyva.dev.hartoebuti20

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import repleyva.dev.hartoebuti20.adapters.RecyclerViewAdapter
import repleyva.dev.hartoebuti20.api.ApiFake.Companion.getData
import repleyva.dev.hartoebuti20.connection.ApiInterface
import repleyva.dev.hartoebuti20.databinding.ActivityMainBinding
import repleyva.dev.hartoebuti20.model.OrderData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    /*
    * TODO:
    *  - Imprementar LiveData para observar cuando cambie la coneccion de internet
    *  - Idear El carrito de compras e implementar el touch para cada tarjeta
    *  */

    lateinit var binding: ActivityMainBinding
    lateinit var manager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        manager = LinearLayoutManager(this)

        if (testConnectionInternet(this)) {
            binding.progressLinear.setVisibility(View.VISIBLE);
            getDataApi()
        } else {
            Toast.makeText(
                this,
                "Compruebe su conexión a Internet",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun testConnectionInternet(context: Context): Boolean {
        var connected = false
        val connec = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        // Recupera todas las redes (tanto móviles como wifi)
        val redes = connec.allNetworkInfo
        for (i in redes.indices) {
            // Si alguna red tiene conexión, se devuelve true
            if (redes[i].state == NetworkInfo.State.CONNECTED) {
                connected = true
            }
        }
        return connected
    }

    fun getDataApi() {
        val apiInterface = ApiInterface.create().getOrders("combos.json")

        //apiInterface.enqueue( Callback<List<Movie>>())
        apiInterface.enqueue(object : Callback<ArrayList<OrderData>> {
            override fun onResponse(
                call: Call<ArrayList<OrderData>>?,
                response: Response<ArrayList<OrderData>>?
            ) {
                if (response?.body() != null) {
                    binding.progressLinear.setVisibility(View.GONE);
                    binding.recyclerOrders.apply {
                        adapter = RecyclerViewAdapter(response.body()!!)
                        layoutManager = manager
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<OrderData>>?, t: Throwable?) {
                if (t != null) {
                    binding.progressLinear.setVisibility(View.GONE);
                    Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}