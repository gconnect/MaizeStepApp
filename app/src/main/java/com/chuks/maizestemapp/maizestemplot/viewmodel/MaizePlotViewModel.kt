package com.chuks.maizestemapp.maizestemplot.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chuks.maizestemapp.common.data.Insect
import com.chuks.maizestemapp.common.data.MaizePlot
import com.chuks.maizestemapp.maizestemplot.repository.MaizePlotRepository
import kotlinx.coroutines.launch
import timber.log.Timber
/**
 * This is MaizePlotViewModel. This has a param [maizePlotRepository]
 * and extends [ViewModel]
 */
class MaizePlotViewModel(private val maizePlotRepository: MaizePlotRepository) : ViewModel() {

    private val _showMessage : MutableLiveData<String> = MutableLiveData()
    val showMessage : LiveData<String> get() = _showMessage

    private val _showProgress : MutableLiveData<Boolean> = MutableLiveData()
    val showProgress : LiveData<Boolean> get() = _showProgress

    private val _maize : MutableLiveData<List<MaizePlot>> = MutableLiveData()
    val maize : LiveData<List<MaizePlot>> get() = _maize

    val maizePlot = maizePlotRepository.getInsect()
    init {

        viewModelScope.launch {
            try {
                _showProgress.postValue(true)
                _maize.postValue(maizePlotRepository.requestInsect())
                _showProgress.postValue(false)

            }catch (e : Throwable){
                _showMessage.postValue("Unable to connect to server!")
                _showProgress.postValue(false)
            }
        }
    }
}