import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mymoodbrew_v2.models.CoffeeVariation
import com.example.mymoodbrew_v2.R


class CoffeeVariationAdapter : ListAdapter<CoffeeVariation, CoffeeVariationAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coffee_variation, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val variation = getItem(position)
        holder.bind(variation)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.textViewName)

        fun bind(variation: CoffeeVariation) {
            nameTextView.text = variation.name
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<CoffeeVariation>() {
        override fun areItemsTheSame(oldItem: CoffeeVariation, newItem: CoffeeVariation): Boolean {
            return oldItem.variationId == newItem.variationId
        }

        override fun areContentsTheSame(oldItem: CoffeeVariation, newItem: CoffeeVariation): Boolean {
            return oldItem == newItem
        }
    }
}