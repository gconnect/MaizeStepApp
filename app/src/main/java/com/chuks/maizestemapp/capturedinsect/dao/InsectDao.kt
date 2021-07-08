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
interface InsectDao {

    /**
     * This is query [getAllCapturedInsect]
     * */
    @Query("SELECT * from insect ORDER BY date DESC")
    fun getAllCapturedInsect() : LiveData<List<Insect>>

    /**
     * This [insertAllInsect] to the database
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInsects(insect : List<Insect>?)


    /**
     * Get all captured insect by their name [getAllCapturedInsect]
     * */
    @Query("SELECT * FROM Insect WHERE name = :name")
    fun getInsectByName(name: String): LiveData<List<Insect>>
}