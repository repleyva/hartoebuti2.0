package repleyva.dev.hartoebuti20.view

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import repleyva.dev.hartoebuti20.R
import repleyva.dev.hartoebuti20.adapters.RecyclerViewOrderAdapter
import repleyva.dev.hartoebuti20.connection.ApiInterface
import repleyva.dev.hartoebuti20.connection.ConnectionLiveData
import repleyva.dev.hartoebuti20.databinding.ActivityMainBinding
import repleyva.dev.hartoebuti20.model.OrderData
import repleyva.dev.hartoebuti20.viewmodel.OrderViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {

    /*
    * TODO:
    *  - Ver crrito de compras
    *  */

    lateinit var binding: ActivityMainBinding
    lateinit var manager: RecyclerView.LayoutManager

    // para chequear si hay coneccion a internet
    lateinit var connectionLiveData: ConnectionLiveData

    // inspeccionar el pedido
    private val orderViewModel: OrderViewModel by viewModels()

    lateinit var order: OrderData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        manager = LinearLayoutManager(this)

        connectionLiveData = ConnectionLiveData(this)
        connectionLiveData.observe(this, { isNetworkAvailable ->
            onlineStatus(isNetworkAvailable)
        })

        if (!testConnectionInternet(this)) {
            Toast.makeText(
                this,
                "Compruebe su conexi??n a Internet",
                Toast.LENGTH_LONG
            ).show()
        }

        binding.btnCart.shrink()

        orderViewModel.order.observe(this, Observer {
            order = OrderData(it?.title, it?.desc, it?.price, it?.img)
            binding.btnCart.text = "(hacer pedido) x1 ${it?.title}"
            binding.btnCart.extend()
        })

        binding.btnCart.setOnClickListener {
            if (binding.btnCart.isExtended && binding.btnCart.text == "") {
                binding.btnCart.shrink()
            } else {
                binding.btnCart.extend()
                if (binding.btnCart.text != "") {
                    val intent = Intent(applicationContext, CartActivity::class.java)
                    intent.putExtra("title", order.title)
                    intent.putExtra("desc", order.desc)
                    intent.putExtra("price", order.price)
                    intent.putExtra("img", order.img)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_in_right, R.anim.stay)
                }
            }
        }

    }

    private fun onlineStatus(status: Boolean) {
        if (status) {
            binding.progressLinear.setVisibility(View.VISIBLE);
            getDataApi()
        } else {
            Toast.makeText(
                this,
                "Compruebe su conexi??n a Internet",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun testConnectionInternet(context: Context): Boolean {
        var connected = false
        val connec = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        // Recupera todas las redes (tanto m??viles como wifi)
        val redes = connec.allNetworkInfo
        for (i in redes.indices) {
            // Si alguna red tiene conexi??n, se devuelve true
            if (redes[i].state == NetworkInfo.State.CONNECTED) {
                connected = true
            }
        }
        return connected
    }

    private fun getDataApi() {
        val apiInterface = ApiInterface.getInstance().create().getOrders("combos.json")
        //apiInterface.enqueue( Callback<List<Movie>>())
        apiInterface.enqueue(object : Callback<ArrayList<OrderData>> {
            override fun onResponse(
                call: Call<ArrayList<OrderData>>?,
                response: Response<ArrayList<OrderData>>?
            ) {
                if (response?.body() != null) {
                    binding.progressLinear.setVisibility(View.GONE);
                    binding.recyclerOrders.adapter =
                        RecyclerViewOrderAdapter(response.body()!!, orderViewModel)
                    binding.recyclerOrders.layoutManager = manager
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