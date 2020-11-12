package com.chuks.maizestemapp.categoriesofspecies.fallarmyworm.repository

import androidx.lifecycle.LiveData
import com.chuks.maizestemapp.common.data.Insect

/**
 * This is EgyptianWormRepository Interface
 * */
interface FallArmyWormRepository {
    /**
     * This [getFallArmyWorm] and return a liveData of Insects
     * */
    fun getFallArmyWorm(): LiveData<List<Insect>>

    /**
     * This [requestFallArmyWorm] and insert into the database
     * */
    suspend fun requestFallArmyWorm()
}