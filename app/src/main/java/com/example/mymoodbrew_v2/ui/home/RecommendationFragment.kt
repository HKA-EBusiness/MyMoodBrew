package com.example.mymoodbrew_v2.ui.home

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
import com.example.mymoodbrew_v2.models.CoffeeRecipe
import dagger.hilt.android.AndroidEntryPoint
import java.net.URLEncoder

@AndroidEntryPoint
class RecommendationFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recommendation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recipeImage: ImageView = view.findViewById(R.id.recipe_image)
        val recipeTitle: TextView = view.findViewById(R.id.recipe_title)
        val recipeDescription: TextView = view.findViewById(R.id.recipe_description)
        val recipeIngredients: TextView = view.findViewById(R.id.recipe_ingredients)
        val recipePreparation: TextView = view.findViewById(R.id.recipe_preparation)

        // Fetch data from ViewModel
        val userId = 1
        homeViewModel.getRecommendedCoffee(userId).observe(viewLifecycleOwner) { recipe ->
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
}
