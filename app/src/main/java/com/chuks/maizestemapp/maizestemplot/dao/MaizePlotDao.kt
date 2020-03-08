package com.chuks.maizestemapp.maizestemplot.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chuks.maizestemapp.common.data.MaizePlot

/**
 * This is the Data Access Object (DAO) for Maize plot
 * */
@Dao
interface MaizePlotDao{

    /**
     * This is query [getInsect]
     * */
    @Query("SELECT * from maize_plot")
    fun getInsect() : LiveData<List<MaizePlot>>

    /**
     * This [insertInsect] to the database
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInsect(insect : List<MaizePlot>?)
}