package com.chuks.maizestemapp.capturedinsect.repository

import androidx.lifecycle.LiveData
import com.chuks.maizestemapp.capturedinsect.dao.InsectDao
import com.chuks.maizestemapp.common.data.Insect
import com.chuks.maizestemapp.common.data.remote.MaizeInsectApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

/**
 * This is CapturedInsectRepositoryImpl class. This class takes in two params [maizeInsectApi]
 * and [capturedInsectDao] and implements [CapturedInsectRepository]
 * */
class CapturedInsectRepositoryImpl(
    private val maizeInsectApi: MaizeInsectApi,
    private val capturedInsectDao: InsectDao
) : CapturedInsectRepository {

    /**
     * This [getAllCapturedInsect] and return a liveData of Insects
     * */
    override fun getAllCapturedInsect(): LiveData<List<Insect>> {
        return capturedInsectDao.getAllCapturedInsect()
    }

    /**
     * This [requestCapturedInsect] is a suspend function which must be called from a coroutine
     * */
    override suspend fun requestCapturedInsect() = withContext(Dispatchers.Main) {
        val response = maizeInsectApi.getAllCapturedInsect()
        try {
            if (response.isSuccessful) {
                val capturedList = response.body()

                capturedList?.let {
                    capturedInsectDao.insertInsects(capturedList)
                    Timber.i("fetched insects success $capturedList")
                }
            } else {
                Timber.i("request failed due to ${response.errorBody()?.string()}")
            }
        } catch (e: Throwable) {
            Timber.i("Throw an exception ${e.message}")
        }
    }
}