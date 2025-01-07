package com.example.mymoodbrew_v2.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mymoodbrew_v2.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        // Set up TabLayout (e.g., add tabs dynamically)
        val tabLayout = binding.tabLayout
        tabLayout.addTab(tabLayout.newTab().setText("Recommendation"))
        tabLayout.addTab(tabLayout.newTab().setText("Weekly special"))

        // Default: Show recommendation tab
        showRecommendationView()

        // Handle Tab selection
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> showRecommendationView()
                    1 -> showWeeklySpecialView()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        return root
    }

    private fun showRecommendationView() {
        binding.recommendationView.visibility = View.VISIBLE
        binding.weeklySpecialPlaceholder.visibility = View.GONE

        homeViewModel.getRecommendedCoffee(1).observe(viewLifecycleOwner, Observer { coffeeRecipe ->
            coffeeRecipe?.let {
                binding.coffeeTitle.text = it.title
            }
        })
    }

    private fun showWeeklySpecialView() {
        binding.recommendationView.visibility = View.GONE
        binding.weeklySpecialPlaceholder.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}