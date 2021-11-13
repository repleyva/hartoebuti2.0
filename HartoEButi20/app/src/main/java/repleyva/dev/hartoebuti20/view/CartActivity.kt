package repleyva.dev.hartoebuti20.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import repleyva.dev.hartoebuti20.R
import repleyva.dev.hartoebuti20.databinding.ActivityCartBinding
import repleyva.dev.hartoebuti20.viewmodel.OrderViewModel

class CartActivity : AppCompatActivity() {

    lateinit var binding: ActivityCartBinding

    // inspeccionar el pedido
    private val orderViewModel: OrderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        val title = intent.getStringExtra("title")
        val img = intent.getStringExtra("img")
        val desc = intent.getStringExtra("desc")
        val price = intent.getStringExtra("price")

        binding.tvTitleCart.text = title
        binding.tvDescCart.text = desc
        binding.tvPriceCart.text = price

        Glide.with(binding.ivOrderCart)
            .load(img)
            .circleCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
            .fallback(R.drawable.ic_launcher_foreground)
            .into(binding.ivOrderCart)
    }

    override fun onBackPressed() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.stay)
    }
}