package com.chuks.maizestemapp.maizestemplot.repository

import androidx.lifecycle.LiveData
import com.chuks.maizestemapp.common.data.Insect
import com.chuks.maizestemapp.common.data.MaizePlot

/**
* This is MaizePlotRepository Interface
* */
interface MaizePlotRepository {

    /**
     * This [getInsect] and return a liveData of MaizePlot
     * */
    fun getInsect(name : String) : LiveData<List<Insect>>
    /**
     * This [requestInsect] and insert into the database
     * */
    suspend fun requestInsect()
}