package com.chuks.maizestemapp.categoriesofspecies.africanarmyworm.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chuks.maizestemapp.common.data.Insect

/**
 * This is the Data Access Object (DAO) for African Armyworm
 * */
@Dao
interface AfricanArmyWormDao {
    /**
     * This is query [getAllAfricanWorm]
     * */
    @Query("select * from insect")
    fun getAllAfricanWorm() : LiveData<List<Insect>>

    /**
     * This [insertAfricanWorm] to the database
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAfricanWorm(insect : List<Insect>)

}