package com.chuks.maizestemapp.maizestemplot.repository

import androidx.lifecycle.LiveData
import com.chuks.maizestemapp.capturedinsect.repository.CapturedInsectRepository
import com.chuks.maizestemapp.common.data.MaizePlot
import com.chuks.maizestemapp.common.data.remote.MaizeInsectApi
import com.chuks.maizestemapp.maizestemplot.dao.MaizePlotDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

/**
 * This is MaizePlotRepositoryImpl class. This class takes in two params [maizeInsectApi]
 * and [maizePlotDao] and implements [MaizePlotRepository]
 * */
class MaizePlotRepositoryImpl(private val api: MaizeInsectApi,
                              private val maizePlotDao: MaizePlotDao): MaizePlotRepository {

    /**
     * This [getInsect] and return a liveData of Insects
     * */
    override fun getInsect(): LiveData<List<MaizePlot>> {
        return maizePlotDao.getInsect()
    }

    /**
     * This [requestInsect] is a suspend function which must be called from a coroutine
     * */
    override suspend fun requestInsect(): List<MaizePlot> = withContext(Dispatchers.IO) {
        val request = api.getInsectPlot()
        val maizelist =request.insect_plot
//        maizePlotDao.insertInsect(maizelist)
        Timber.d("success ${maizelist.size}")
        maizelist
    }
}