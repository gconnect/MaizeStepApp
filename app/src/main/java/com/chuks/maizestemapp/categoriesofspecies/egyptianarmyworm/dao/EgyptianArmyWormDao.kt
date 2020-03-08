package com.chuks.maizestemapp.categoriesofspecies.egyptianarmyworm.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chuks.maizestemapp.common.data.Insect
/**
 * This is the Data Access Object (DAO) for Egyptian Armyworm
 * */
@Dao
interface EgyptianArmyWormDao {
    /**
     * This is query [getEgyptianWorm]
     * */
    @Query("select * from insect")
    fun getEgyptianWorm() : LiveData<List<Insect>>

    /**
     * This [insertEqyptianWorm] to the database
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEqyptianWorm(insect : List<Insect>)
}