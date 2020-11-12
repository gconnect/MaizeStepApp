package com.chuks.maizestemapp.categoriesofspecies.fallarmyworm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chuks.maizestemapp.categoriesofspecies.fallarmyworm.repository.FallArmyWormRepository
import kotlinx.coroutines.launch

/**
 * This is FallArmyWormViewModel. This has a param [fallArmyWormRepository]
 * and extends [ViewModel]
 */
class FallArmyWormViewModel(private val fallArmyWormRepository: FallArmyWormRepository)
    : ViewModel() {

    private val _showMessage : MutableLiveData<String> = MutableLiveData()
    val showMessage : LiveData<String> get() = _showMessage

    private val _showProgress : MutableLiveData<Boolean> = MutableLiveData()
    val showProgress : LiveData<Boolean> get() = _showProgress

    val fallArmyList = fallArmyWormRepository.getFallArmyWorm()

    init {

        viewModelScope.launch {
            try {
                _showProgress.postValue(true)
                fallArmyWormRepository.requestFallArmyWorm()
                _showProgress.postValue(false)

            }catch (e : Throwable){
                _showMessage.postValue("Unable to connect to server!")
                _showProgress.postValue(false)
            }

        }
    }
}