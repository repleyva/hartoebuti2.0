package repleyva.dev.hartoebuti20.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import repleyva.dev.hartoebuti20.R
import repleyva.dev.hartoebuti20.databinding.ActivityCartBinding
import repleyva.dev.hartoebuti20.helpers.MathOperations

class CartActivity : AppCompatActivity() {

    lateinit var binding: ActivityCartBinding
    var count: Int = 1

    @SuppressLint("SetTextI18n")
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
        binding.tvPriceCart.text = "$ ${price}"
        binding.tvTotal.text = "Total: $ ${price}"
        binding.tvCountOrder.text = "1"

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
            setTotal(Integer.parseInt(price))
        }

        binding.ivLess.setOnClickListener {
            count = MathOperations.less(count)
            binding.tvCountOrder.text = count.toString()
            setTotal(Integer.parseInt(price))
        }

    }

    override fun onBackPressed() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.stay)
    }

    fun setTotal(price: Int) {
        var total = count * price
        binding.tvTotal.text = "Total: $ ${total}"
    }


}