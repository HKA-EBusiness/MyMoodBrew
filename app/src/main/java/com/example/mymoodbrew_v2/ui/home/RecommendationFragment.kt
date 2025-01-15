package com.example.mymoodbrew_v2.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.example.mymoodbrew_v2.R
import com.example.mymoodbrew_v2.databinding.FragmentRecommendationBinding
import com.example.mymoodbrew_v2.models.CoffeeRecipe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecommendationFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentRecommendationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecommendationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        configureDefaultStyles()
    }

    private fun setupObservers() {
        val userId = 1 // Example user ID; replace as needed
        homeViewModel.getRecommendedCoffee(userId).observe(viewLifecycleOwner) { recipe ->
            if (recipe != null) {
                updateUI(recipe)
            } else {
                showError("No recommendations found.")
            }
        }
    }

    private fun configureDefaultStyles() {
        with(binding) {
            recipeTitle.apply {
                textSize = 20f
                setTextColor(resources.getColor(R.color.black, null))
            }
            recipeDescription.textSize = 16f
            recipeIngredients.textSize = 14f
            recipePreparation.textSize = 14f
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateUI(recipe: CoffeeRecipe) {
        with(binding) {
            recipeTitle.text = recipe.title
            recipeDescription.text = "Description: ${recipe.description}"
            recipeIngredients.text = "Ingredients: ${recipe.ingredients}"
            recipePreparation.text = "Preparation: ${recipe.preparationSteps}"

            recipeImage.load(recipe.imageUrl) {
                crossfade(true)
                placeholder(R.drawable.placeholder_image)
                error(R.drawable.error_image)
                transformations(RoundedCornersTransformation(16f))
                listener(
                    onError = { _, throwable -> showError("Failed to load image: ${""}") }
                )
            }
        }
    }

    private fun showError(message: String) {
        binding.recipeTitle.text = message
        binding.recipeDescription.text = ""
        binding.recipeIngredients.text = ""
        binding.recipePreparation.text = ""
        binding.recipeImage.setImageResource(R.drawable.error_image)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
