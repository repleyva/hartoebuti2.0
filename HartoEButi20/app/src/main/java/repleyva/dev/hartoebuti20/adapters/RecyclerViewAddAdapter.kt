package repleyva.dev.hartoebuti20.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import repleyva.dev.hartoebuti20.R
import repleyva.dev.hartoebuti20.databinding.ActivityMainBinding
import repleyva.dev.hartoebuti20.databinding.ItemAdditionalBinding
import repleyva.dev.hartoebuti20.databinding.ItemOrderBinding
import repleyva.dev.hartoebuti20.model.OrderData
import repleyva.dev.hartoebuti20.viewmodel.OrderViewModel

class RecyclerViewAddAdapter(
    private val data: ArrayList<OrderData>,
    val orderViewModel: OrderViewModel
) :
    RecyclerView.Adapter<RecyclerViewAddAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemAdditionalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: OrderData) {
            binding.listItemAdd = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val listItemBinding = ItemAdditionalBinding.inflate(inflater, parent, false)
        return ViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
        holder.binding.btnAdd.setOnClickListener {
            orderViewModel.setIncCountAdd(holder.binding.listItemAdd)
        }
        holder.binding.btnLess.setOnClickListener {
            orderViewModel.setDecCountAdd(holder.binding.listItemAdd)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    companion object {
        @JvmStatic
        @BindingAdapter("loadImage")
        fun loadImage(thubmImage: ImageView, url: String) {
            Glide.with(thubmImage)
                .load(url)
                .circleCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .fallback(R.drawable.ic_launcher_foreground)
                .into(thubmImage)
        }
    }
}