package com.chuks.maizestemapp.categoriesofspecies.egyptianarmyworm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chuks.maizestemapp.categoriesofspecies.egyptianarmyworm.repository.EgyptianWormRepository
import kotlinx.coroutines.launch

/**
 * This is EgyptianWormViewModel. This has a param [egyptianWormRepository]
 * and extends [ViewModel]
 */
class EgyptianWormViewModel(private val egyptianWormRepository: EgyptianWormRepository) : ViewModel(){

    private val _showMessage : MutableLiveData<String> = MutableLiveData()
    val showMessage : LiveData<String> get() = _showMessage

    private val _showProgress : MutableLiveData<Boolean> = MutableLiveData()
    val showProgress : LiveData<Boolean> get() = _showProgress

    val egyptianList = egyptianWormRepository.getEgyptianArmyWorm()

    init {

        viewModelScope.launch {
            try {
                _showProgress.postValue(true)
                egyptianWormRepository.requestEgyptianArmyWorm()
                _showProgress.postValue(false)

            }catch (e : Throwable){
                _showMessage.postValue("Unable to connect to server!")
                _showProgress.postValue(false)
            }

        }
    }

}