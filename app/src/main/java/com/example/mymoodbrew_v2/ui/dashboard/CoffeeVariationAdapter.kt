import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
        private val descriptionTextView: TextView = itemView.findViewById(R.id.textViewDescription)
        private val caffeineContentTextView: TextView = itemView.findViewById(R.id.textViewCaffeineContent)
        private val caloriesTextView: TextView = itemView.findViewById(R.id.textViewCalories)
        private val fatContentTextView: TextView = itemView.findViewById(R.id.textViewFatContent)
        private val sugarTextView: TextView = itemView.findViewById(R.id.textViewSugar)
        private val imageView: ImageView = itemView.findViewById(R.id.imageViewVariation)

        init {
            itemView.setBackgroundColor(android.graphics.Color.parseColor("#EFEFEF"))
        }

        @SuppressLint("SetTextI18n")
        fun bind(variation: CoffeeVariation) {
            nameTextView.text = variation.name
            descriptionTextView.text = variation.description
            caffeineContentTextView.text = "Caffeine Content: ${variation.caffeineContent} mg"
            caloriesTextView.text = "Calories: ${variation.calories} kcal"
            fatContentTextView.text = "Fat Content: ${variation.fatContent} g"
            sugarTextView.text = "Sugar: ${variation.sugar} g"
            Glide.with(itemView.context)
                .load(variation.imageUrl)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .into(imageView)
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
