package com.chuks.maizestemapp.capturedinsect.repository

import androidx.lifecycle.LiveData
import com.chuks.maizestemapp.common.data.Insect

/**
 * This is CapturedInsectRepository Interface
 * */
interface CapturedInsectRepository {

    /**
     * This [getAllCapturedInsect] and return a liveData of Insects
     * */
    fun getAllCapturedInsect() : LiveData<List<Insect>>

    /**
     * This [requestCapturedInsect] and insert into the database
     * */
    suspend fun requestCapturedInsect(): Unit?
}