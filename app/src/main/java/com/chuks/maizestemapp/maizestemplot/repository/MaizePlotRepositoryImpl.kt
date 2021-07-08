package com.chuks.maizestemapp.maizestemplot.repository

import androidx.lifecycle.LiveData
import com.chuks.maizestemapp.capturedinsect.dao.InsectDao
import com.chuks.maizestemapp.capturedinsect.repository.CapturedInsectRepository
import com.chuks.maizestemapp.common.data.Insect
import com.chuks.maizestemapp.common.data.MaizePlot
import com.chuks.maizestemapp.common.data.remote.MaizeInsectApi
import com.chuks.maizestemapp.maizestemplot.dao.MaizePlotDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.lang.Exception

/**
 * This is MaizePlotRepositoryImpl class. This class takes in two params [maizeInsectApi]
 * and [maizePlotDao] and implements [MaizePlotRepository]
 * */
class MaizePlotRepositoryImpl(private val api: MaizeInsectApi,
                              private val maizePlotDao: InsectDao): MaizePlotRepository {

    /**
     * This [getInsect] and return a liveData of Insects
     * */
    override fun getInsect(name:String): LiveData<List<Insect>> {
        return maizePlotDao.getInsectByName(name)
    }

    /**
     * This [requestInsect] is a suspend function which must be called from a coroutine
     * */
    override suspend fun requestInsect()= withContext(Dispatchers.IO) {
//        val request = api.getInsectPlot()
//        val maizelist =request.insect_plot
////        maizePlotDao.insertInsect(maizelist)
//        Timber.d("success ${maizelist.size}")
//        maizelist
        val response = api.getAllCapturedInsect()
        try {
            if (response.isSuccessful) {
                val fallArmyList = response.body()
                maizePlotDao.insertInsects(fallArmyList)
                Timber.i("plot ${fallArmyList?.size}")

            } else {
                Timber.i("request failed due to ${response.errorBody()?.string()}")
            }

        } catch (e: Exception) {
            Timber.i("Throw an exception ${e.message}")
        }

    }
    }
