package com.chuks.maizestemapp.categoriesofspecies.africanarmyworm.repository

import androidx.lifecycle.LiveData
import com.chuks.maizestemapp.capturedinsect.repository.CapturedInsectRepository
import com.chuks.maizestemapp.categoriesofspecies.africanarmyworm.dao.AfricanArmyWormDao
import com.chuks.maizestemapp.common.data.Insect
import com.chuks.maizestemapp.common.data.remote.MaizeInsectApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * This is AfricanArmmyWormRepositoryImpl class. This class takes in two params [maizeInsectApi]
 * and [africanArmyWormDao] and implements [AfricanArmyWormRepository]
 * */
class AfricanArmmyWormRepositoryImpl(private val insectApi: MaizeInsectApi,
                                     private val africanArmyWormDao: AfricanArmyWormDao)
    : AfricanArmyWormRepository{

    /**
     * This [getAfricanArmyWorm] and return a liveData of Insects
     * */
    override fun getAfricanArmyWorm(): LiveData<List<Insect>> {
        return africanArmyWormDao.getAllAfricanWorm()
    }

    /**
     * This [requestAfricanArmyWorm] is a suspend function which must be called from a coroutine
     * */
    override suspend fun requestAfricanArmyWorm() = withContext(Dispatchers.IO){
        val request = insectApi.getAfricanArmyWorm()
        val africanList = request.insect
        africanArmyWormDao.insertAfricanWorm(africanList)
    }
}