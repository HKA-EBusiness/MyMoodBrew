package com.example.mymoodbrew_v2.ui.dashboard

import CoffeeVariationAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymoodbrew_v2.R
import com.example.mymoodbrew_v2.databinding.FragmentCoffeeVariationListBinding
import com.example.mymoodbrew_v2.models.CoffeeVariation
import com.example.mymoodbrew_v2.ui.dashboard.CoffeeVariationViewModel.SortBy
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentCoffeeVariationListBinding? = null
    private val binding get() = _binding!!
    private val coffeeVariationViewModel: CoffeeVariationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoffeeVariationListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CoffeeVariationAdapter()
        binding.recyclerViewCoffeeVariations.adapter = adapter
        binding.recyclerViewCoffeeVariations.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewCoffeeVariations.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()

        coffeeVariationViewModel.coffeeVariations.observe(viewLifecycleOwner) { variations ->
            adapter.submitList(variations)
        }

        coffeeVariationViewModel.loadCoffeeVariations()

        // Set up Floating Action Button
        binding.fabAddCoffeeVariation.setOnClickListener {
            showAddCoffeeVariationDialog()
        }

        binding.buttonSortByName.setOnClickListener {
            coffeeVariationViewModel.sortCoffeeVariations(SortBy.NAME)
        }

        binding.buttonSortByCaffeine.setOnClickListener {
            coffeeVariationViewModel.sortCoffeeVariations(SortBy.CAFFEINE)
        }
    }

    private fun showAddCoffeeVariationDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.add_coffee_variation_dialog, null)

        val dialog = androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle("Add a New Coffee Variation")
            .create()

        dialogView.findViewById<Button>(R.id.buttonAdd).setOnClickListener {
            val name = dialogView.findViewById<EditText>(R.id.editTextName).text.toString()
            val description = dialogView.findViewById<EditText>(R.id.editTextDescription).text.toString()
            val caffeineContent = dialogView.findViewById<EditText>(R.id.editTextCaffeineContent).text.toString().toDoubleOrNull() ?: 0.0
            val calories = dialogView.findViewById<EditText>(R.id.editTextCalories).text.toString().toIntOrNull() ?: 0
            val fatContent = dialogView.findViewById<EditText>(R.id.editTextFatContent).text.toString().toDoubleOrNull() ?: 0.0
            val sugar = dialogView.findViewById<EditText>(R.id.editTextSugar).text.toString().toDoubleOrNull() ?: 0.0

            val newVariation = CoffeeVariation(
                variationId = 0, // Let Room auto-generate the ID
                name = name,
                description = description,
                caffeineContent = caffeineContent,
                calories = calories,
                fatContent = fatContent,
                sugar = sugar,
                imageUrl = "", // Optionally add image handling
                recipeId = 0 // Set a default or user-specified recipeId
            )

            coffeeVariationViewModel.addCoffeeVariation(newVariation)
            dialog.dismiss()
        }

        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}