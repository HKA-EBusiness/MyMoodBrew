package com.example.mymoodbrew_v2.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymoodbrew_v2.dao.CoffeeVariationDao
import com.example.mymoodbrew_v2.dao.RecommendationDao
import com.example.mymoodbrew_v2.dao.WeeklyRecipeDao
import com.example.mymoodbrew_v2.models.CoffeeRecipe
import com.example.mymoodbrew_v2.models.CoffeeVariation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoffeeVariationViewModel @Inject constructor(
    private val coffeeVariationDao: CoffeeVariationDao
) : ViewModel() {
    private val _coffeeVariations = MutableLiveData<List<CoffeeVariation>>()
    val coffeeVariations: LiveData<List<CoffeeVariation>> = _coffeeVariations

    enum class SortBy { NAME, CAFFEINE }

    fun loadCoffeeVariations() {
        viewModelScope.launch(Dispatchers.IO) {
            val variations = coffeeVariationDao.getAll()
            _coffeeVariations.postValue(variations)
        }
    }

    fun addCoffeeVariation(variation: CoffeeVariation) {
        viewModelScope.launch(Dispatchers.IO) {
            coffeeVariationDao.insert(variation)
            loadCoffeeVariations()
        }
    }

    fun sortCoffeeVariations(by: SortBy) {
        viewModelScope.launch(Dispatchers.Default) {
            val currentList = _coffeeVariations.value.orEmpty()
            val sortedList = when (by) {
                SortBy.NAME -> currentList.sortedBy { it.name }
                SortBy.CAFFEINE -> currentList.sortedByDescending { it.caffeineContent }
            }
            _coffeeVariations.postValue(sortedList)
        }
    }
}