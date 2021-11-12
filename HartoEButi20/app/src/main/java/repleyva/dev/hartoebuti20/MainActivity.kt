package repleyva.dev.hartoebuti20

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import repleyva.dev.hartoebuti20.adapters.RecyclerViewAdapter
import repleyva.dev.hartoebuti20.api.ApiFake.Companion.getData
import repleyva.dev.hartoebuti20.databinding.ActivityMainBinding
import repleyva.dev.hartoebuti20.model.OrderData

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var manager: RecyclerView.LayoutManager
    var data = ArrayList<OrderData>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        manager = LinearLayoutManager(this)

        getData(data)
        setData()
    }

    fun setData() {
        binding.recyclerOrders.apply {
            adapter = RecyclerViewAdapter(data)
            layoutManager = manager
        }
    }
}