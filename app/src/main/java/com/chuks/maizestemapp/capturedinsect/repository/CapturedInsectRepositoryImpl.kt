package com.chuks.maizestemapp.capturedinsect.repository

import androidx.lifecycle.LiveData
import com.chuks.maizestemapp.capturedinsect.dao.CapturedInsectDao
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
    private val capturedInsectDao: CapturedInsectDao
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
        val request = maizeInsectApi.getAllCapturedInsect()
        try {
            if (request.isSuccessful) {
                val capturedList = request.body()?.insect
                capturedList?.let {
                    capturedInsectDao.insertAllCapturedInsect(capturedList)
                    Timber.i("fetched insects success $capturedList")
                }
            } else {
                Timber.i("request failed due to ${request.errorBody()?.string()}")
            }
        } catch (e: Throwable) {
            Timber.i("Throw an exception ${e.message}")
        }
    }
}