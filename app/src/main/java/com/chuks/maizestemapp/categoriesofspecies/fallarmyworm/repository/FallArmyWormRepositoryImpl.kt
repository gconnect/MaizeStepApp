package com.chuks.maizestemapp.categoriesofspecies.fallarmyworm.repository

import androidx.lifecycle.LiveData
import com.chuks.maizestemapp.categoriesofspecies.africanarmyworm.repository.AfricanArmyWormRepository
import com.chuks.maizestemapp.categoriesofspecies.fallarmyworm.dao.FallArmyWormDao
import com.chuks.maizestemapp.common.data.Insect
import com.chuks.maizestemapp.common.data.remote.MaizeInsectApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * This is FallArmyWormRepositoryImpl class. This class takes in two params [maizeInsectApi]
 * and [fallArmyWormDao] and implements [AfricanArmyWormRepository]
 * */
class FallArmyWormRepositoryImpl(private val insectApi: MaizeInsectApi,
                                 private val fallArmyWormDao: FallArmyWormDao) : FallArmyWormRepository{

    /**
     * This [getFallArmyWorm] and return a liveData of Insects
     * */
    override fun getFallArmyWorm(): LiveData<List<Insect>> {

        return fallArmyWormDao.getFallArmyWorm()
    }

    /**
     * This [requestFallArmyWorm] is a suspend function which must be called from a coroutine
     * */
    override suspend fun requestFallArmyWorm() = withContext(Dispatchers.IO) {
        val request = insectApi.getFallArmyArmyWorm()
        val fallArmyList = request.insect
        fallArmyWormDao.insertFallArmyWorm(fallArmyList)
    }
}