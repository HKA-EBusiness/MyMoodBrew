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
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val recommendationDao: RecommendationDao,
    private val weeklyRecipeDao: WeeklyRecipeDao
) : ViewModel() {

    fun getRecommendedCoffee(userId: Int): LiveData<CoffeeRecipe> {
        val result = MutableLiveData<CoffeeRecipe>()
        viewModelScope.launch {
            try {
                val recommendedCoffee = withContext(Dispatchers.IO) {
                    recommendationDao.getRandomRecommendedCoffee(userId)
                }
                result.postValue(recommendedCoffee)
            } catch (e: Exception) {
                // Log the error (consider using Timber or similar)
                e.printStackTrace()
            }
        }
        return result
    }

    fun getWeeklySpecial(): LiveData<CoffeeRecipe?> {
        val result = MutableLiveData<CoffeeRecipe?>()
        viewModelScope.launch {
            try {
                val weeklySpecial = withContext(Dispatchers.IO) {
                    weeklyRecipeDao.getRandomRecommendedCoffee()
                }
                result.postValue(weeklySpecial)
            } catch (e: Exception) {
                // Log the error
                e.printStackTrace()
            }
        }
        return result
    }
}
