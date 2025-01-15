package com.example.mymoodbrew_v2.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.mymoodbrew_v2.R
import com.example.mymoodbrew_v2.models.CoffeeRecipe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeeklySpecialFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_recommendation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI(view)
    }

    private fun setupUI(view: View) {
        val recipeImage: ImageView = view.findViewById(R.id.recipe_image)
        val recipeTitle: TextView = view.findViewById(R.id.recipe_title)
        val recipeDescription: TextView = view.findViewById(R.id.recipe_description)
        val recipeIngredients: TextView = view.findViewById(R.id.recipe_ingredients)
        val recipePreparation: TextView = view.findViewById(R.id.recipe_preparation)

        configureDefaultStyles(
            recipeTitle,
            recipeDescription,
            recipeIngredients,
            recipePreparation
        )

        // Observe Weekly Special data
        homeViewModel.getWeeklySpecial().observe(viewLifecycleOwner) { recipe ->
            if (recipe != null) {
                updateUI(
                    recipe,
                    recipeImage,
                    recipeTitle,
                    recipeDescription,
                    recipeIngredients,
                    recipePreparation
                )
            } else {
                showError(
                    recipeTitle,
                    recipeDescription,
                    recipeIngredients,
                    recipePreparation,
                    recipeImage
                )
            }
        }
    }

    private fun configureDefaultStyles(
        recipeTitle: TextView,
        recipeDescription: TextView,
        recipeIngredients: TextView,
        recipePreparation: TextView
    ) {
        recipeTitle.apply {
            textSize = 20f
            setTextColor(resources.getColor(R.color.black, null))
        }
        recipeDescription.textSize = 16f
        recipeIngredients.textSize = 14f
        recipePreparation.textSize = 14f
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
        with(recipe) {
            recipeTitle.text = title
            recipeDescription.text = "Description: $description"
            recipeIngredients.text = "Ingredients: $ingredients"
            recipePreparation.text = "Preparation: $preparationSteps"
        }

        recipeImage.load(recipe.imageUrl) {
            crossfade(true)
            placeholder(R.drawable.placeholder_image)
            error(R.drawable.error_image)
            transformations(RoundedCornersTransformation(16f))
        }
    }

    private fun showError(
        recipeTitle: TextView,
        recipeDescription: TextView,
        recipeIngredients: TextView,
        recipePreparation: TextView,
        recipeImage: ImageView
    ) {
        recipeTitle.text = "No Weekly Special Found"
        recipeDescription.text = ""
        recipeIngredients.text = ""
        recipePreparation.text = ""
        recipeImage.setImageResource(R.drawable.error_image)
    }
}
