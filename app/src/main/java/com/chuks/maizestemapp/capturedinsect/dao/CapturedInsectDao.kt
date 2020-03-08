package com.chuks.maizestemapp.capturedinsect.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chuks.maizestemapp.common.data.Insect

/**
 * This is the Data Access Object (DAO) for captured insect
* */
@Dao
interface CapturedInsectDao {

    /**
     * This is query [getAllCapturedInsect]
     * */
    @Query("SELECT * from insect")
    fun getAllCapturedInsect() : LiveData<List<Insect>>

    /**
     * This [insertAllCapturedInsect] to the database
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCapturedInsect(insect : List<Insect>)
}