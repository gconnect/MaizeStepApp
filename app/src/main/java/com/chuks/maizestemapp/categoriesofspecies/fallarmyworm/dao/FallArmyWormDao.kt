package com.chuks.maizestemapp.categoriesofspecies.fallarmyworm.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chuks.maizestemapp.common.data.Insect
/**
 * This is the Data Access Object (DAO) for FallArmy Armyworm
 * */
@Dao
interface FallArmyWormDao {
    /**
     * This is query [getFallArmyWorm]
     * */
    @Query("select * from insect")
    fun getFallArmyWorm() : LiveData<List<Insect>>

    /**
     * This [insertFallArmyWorm] to the database
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFallArmyWorm(insect : List<Insect>)
}