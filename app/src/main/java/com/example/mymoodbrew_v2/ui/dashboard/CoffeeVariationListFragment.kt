package com.example.mymoodbrew_v2.ui.dashboard

import CoffeeVariationAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymoodbrew_v2.databinding.FragmentCoffeeVariationListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoffeeVariationListFragment : Fragment() {

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

        coffeeVariationViewModel.coffeeVariations.observe(viewLifecycleOwner) { variations ->
            adapter.submitList(variations)
        }

        coffeeVariationViewModel.loadCoffeeVariations()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}