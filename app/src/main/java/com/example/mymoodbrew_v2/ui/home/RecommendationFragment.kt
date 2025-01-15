package com.example.mymoodbrew_v2.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.example.mymoodbrew_v2.R
import com.example.mymoodbrew_v2.databinding.FragmentRecommendationBinding
import com.example.mymoodbrew_v2.models.CoffeeRecipe
import dagger.hilt.android.AndroidEntryPoint
import java.net.URLEncoder
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecommendationFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentRecommendationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recommendation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRecommendationBinding.bind(view)

        val recipeImage: ImageView = binding.recipeImage
        val recipeTitle: TextView = binding.recipeTitle
        val recipeDescription: TextView = binding.recipeDescription
        val recipeIngredients: TextView = binding.recipeIngredients
        val recipePreparation: TextView = binding.recipePreparation

        val userId = 1
        homeViewModel.getRecommendedCoffee(userId).observe(viewLifecycleOwner) { recipe ->
            if (recipe != null) {
                updateUI(
                    recipe,
                    recipeImage,
                    recipeTitle,
                    recipeDescription,
                    recipeIngredients,
                    recipePreparation
                )
            }
        }

        recipeTitle.textSize = 20f
        recipeDescription.textSize = 16f
        recipeIngredients.textSize = 14f
        recipePreparation.textSize = 14f

        recipeTitle.setTextColor(resources.getColor(R.color.black, null))
    }

    @SuppressLint("SetTextI18n")
    private fun updateUI(
        recipe: CoffeeRecipe,
        recipeImage: ImageView,
        recipeTitle: TextView,
        recipeDescription: TextView,
        recipeIngredients: TextView,
        recipePreparation: TextView
    ) {
        recipeTitle.text = recipe.title
        recipeDescription.text = "Description: ${recipe.description}"
        recipeIngredients.text = "Ingredients: ${recipe.ingredients}"
        recipePreparation.text = "Preparation: ${recipe.preparationSteps}"

        recipeImage.load(recipe.imageUrl) {
            crossfade(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
