package com.example.mymoodbrew_v2.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymoodbrew_v2.dao.RecommendationDao
import com.example.mymoodbrew_v2.dao.WeeklyRecipeDao
import com.example.mymoodbrew_v2.models.CoffeeRecipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val recommendationDao: RecommendationDao,
    private val weeklyRecipeDao: WeeklyRecipeDao
) : ViewModel() {

    // Get recommended coffee asynchronously
    fun getRecommendedCoffee(userId: Int): LiveData<CoffeeRecipe> {
        val result = MutableLiveData<CoffeeRecipe>()
        viewModelScope.launch(Dispatchers.IO) {
            val recommendedCoffee = recommendationDao.getRandomRecommendedCoffee(userId)
            result.postValue(recommendedCoffee) // Use postValue to update LiveData from a background thread
        }
        return result
    }

    // Get weekly special asynchronously
    fun getWeeklySpecial(): LiveData<CoffeeRecipe> {
        val result = MutableLiveData<CoffeeRecipe>()
        viewModelScope.launch(Dispatchers.IO) {
            val weeklySpecial = weeklyRecipeDao.getRandomRecommendedCoffee()
            result.postValue(weeklySpecial)
        }
        return result
    }
}
