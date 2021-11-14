package repleyva.dev.hartoebuti20.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import repleyva.dev.hartoebuti20.R
import repleyva.dev.hartoebuti20.adapters.RecyclerViewAddAdapter
import repleyva.dev.hartoebuti20.adapters.RecyclerViewOrderAdapter
import repleyva.dev.hartoebuti20.connection.ApiInterface
import repleyva.dev.hartoebuti20.connection.ConnectionLiveData
import repleyva.dev.hartoebuti20.databinding.ActivityCartBinding
import repleyva.dev.hartoebuti20.helpers.MathOperations
import repleyva.dev.hartoebuti20.model.OrderData
import repleyva.dev.hartoebuti20.viewmodel.OrderViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class CartActivity : AppCompatActivity() {

    lateinit var binding: ActivityCartBinding
    lateinit var manager: RecyclerView.LayoutManager
    var count: Int = 1

    // para chequear si hay coneccion a internet
    lateinit var connectionLiveData: ConnectionLiveData

    lateinit var orderAdd: OrderData
    lateinit var orderLess: OrderData
    var countAdd: Int = 0
    var countLess: Int = 0

    // inspeccionar el pedido
    private val orderViewModel: OrderViewModel by viewModels()

    var total: Int = 0

    var orderAddPrice: Int = 0
    var priceAdditional: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        manager = LinearLayoutManager(this)

        connectionLiveData = ConnectionLiveData(this)
        connectionLiveData.observe(this, { isNetworkAvailable ->
            onlineStatus(isNetworkAvailable)
        })

        if (!testConnectionInternet(this)) {
            Toast.makeText(
                this,
                "Compruebe su conexión a Internet",
                Toast.LENGTH_LONG
            ).show()
        }

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        val title = intent.getStringExtra("title")
        val img = intent.getStringExtra("img")
        val desc = intent.getStringExtra("desc")
        val price = intent.getStringExtra("price")

        binding.tvTitleCart.text = title
        binding.tvDescCart.text = desc
        binding.tvPriceCart.text = "$ ${price}"
        binding.tvTotal.text = "Total: $ ${price}"
        binding.tvCountOrder.text = "1"

        total = Integer.parseInt(price)

        Glide.with(binding.ivOrderCart)
            .load(img)
            .circleCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
            .fallback(R.drawable.ic_launcher_foreground)
            .into(binding.ivOrderCart)

        binding.ivAdd.setOnClickListener {
            count = MathOperations.add(count)
            binding.tvCountOrder.text = count.toString()
            total = count * Integer.parseInt(price)
            binding.tvTotal.text = "Total: $ ${total}"
        }

        binding.ivLess.setOnClickListener {
            count = MathOperations.less(count)
            binding.tvCountOrder.text = count.toString()
            total = count * Integer.parseInt(price)
            binding.tvTotal.text = "Total: $ ${total}"
        }

        orderViewModel.orderIncAdd.observe(this, Observer {
            orderAdd = OrderData(it?.title, it?.desc, it?.price, it?.img)
            countAdd = MathOperations.add(countAdd)
            orderAddPrice = Integer.parseInt(orderAdd.price)
            priceAdditional = orderAddPrice * countAdd
            total += priceAdditional
            binding.tvTotal.text = "Total: $ ${total}"
        })

        /*orderViewModel.orderDecAdd.observe(this, Observer {
            orderLess = OrderData(it?.title, it?.desc, it?.price, it?.img)
            if (orderAdd != null && orderLess.title == orderAdd.title) {
                countAdd = MathOperations.less(countAdd)
                priceAdditional = orderAddPrice * countAdd
                total -= priceAdditional
                binding.tvTotal.text = "Total: $ ${total}"
            }
        })*/

    }

    private fun testConnectionInternet(context: Context): Boolean {
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

    private fun onlineStatus(status: Boolean) {
        if (status) {
            binding.progressLinearAdd.setVisibility(View.VISIBLE);
            getDataApi()
        } else {
            Toast.makeText(
                this,
                "Compruebe su conexión a Internet",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onBackPressed() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.stay)
    }

    private fun getDataApi() {
        val apiInterface = ApiInterface.getInstance().create().getOrders("adicionales.json")
        //apiInterface.enqueue( Callback<List<Movie>>())
        apiInterface.enqueue(object : Callback<ArrayList<OrderData>> {
            override fun onResponse(
                call: Call<ArrayList<OrderData>>?,
                response: Response<ArrayList<OrderData>>?
            ) {
                if (response?.body() != null) {
                    binding.progressLinearAdd.setVisibility(View.GONE);
                    binding.recyclerAdditional.isNestedScrollingEnabled = false
                    binding.recyclerAdditional.adapter =
                        RecyclerViewAddAdapter(response.body()!!, orderViewModel)
                    binding.recyclerAdditional.layoutManager = manager
                }
            }

            override fun onFailure(call: Call<ArrayList<OrderData>>?, t: Throwable?) {
                if (t != null) {
                    binding.progressLinearAdd.setVisibility(View.GONE);
                    Toast.makeText(this@CartActivity, t.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

}