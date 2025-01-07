package com.example.mymoodbrew_v2.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymoodbrew_v2.dao.RecommendationDao
import com.example.mymoodbrew_v2.dao.WeeklyRecipeDao
import com.example.mymoodbrew_v2.models.CoffeeRecipe
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val recommendationDao: RecommendationDao,
    private val weeklyRecipeDao: WeeklyRecipeDao
) : ViewModel() {

    // Get recommended coffee for today
    fun getRecommendedCoffee(userId: Int): LiveData<CoffeeRecipe> {
        return recommendationDao.getRandomRecommendedCoffee(userId)
    }
}