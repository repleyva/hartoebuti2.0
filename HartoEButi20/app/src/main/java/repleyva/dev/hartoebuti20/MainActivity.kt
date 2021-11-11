package repleyva.dev.hartoebuti20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import repleyva.dev.hartoebuti20.adapters.RecyclerViewAdapter
import repleyva.dev.hartoebuti20.databinding.ActivityMainBinding
import repleyva.dev.hartoebuti20.model.Avatar
import repleyva.dev.hartoebuti20.model.OrderData

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var manager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()

        var data = ArrayList<OrderData>();

        data.add(
            OrderData(
                "Cogela Suave",
                "Cogela Suaves",
                "Cogela Suavep",
                Avatar("Cogela Suaveurl")
            )
        )
        data.add(
            OrderData(
                "Cogela Suave",
                "Cogela Suaves",
                "Cogela Suavep",
                Avatar("Cogela Suaveurl")
            )
        )
        data.add(
            OrderData(
                "Cogela Suave",
                "Cogela Suaves",
                "Cogela Suavep",
                Avatar("Cogela Suaveurl")
            )
        )
        data.add(
            OrderData(
                "Cogela Suave",
                "Cogela Suaves",
                "Cogela Suavep",
                Avatar("Cogela Suaveurl")
            )
        )
        data.add(
            OrderData(
                "Cogela Suave",
                "Cogela Suaves",
                "Cogela Suavep",
                Avatar("Cogela Suaveurl")
            )
        )


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        manager = LinearLayoutManager(this)
        binding.recyclerOrders.apply {
            adapter = RecyclerViewAdapter(data)
            layoutManager = manager
        }


    }
}