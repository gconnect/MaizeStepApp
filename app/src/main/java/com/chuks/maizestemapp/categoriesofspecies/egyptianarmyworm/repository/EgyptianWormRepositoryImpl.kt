package com.chuks.maizestemapp.categoriesofspecies.egyptianarmyworm.repository

import androidx.lifecycle.LiveData
import com.chuks.maizestemapp.categoriesofspecies.africanarmyworm.repository.AfricanArmyWormRepository
import com.chuks.maizestemapp.categoriesofspecies.egyptianarmyworm.dao.EgyptianArmyWormDao
import com.chuks.maizestemapp.common.data.Insect
import com.chuks.maizestemapp.common.data.remote.MaizeInsectApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
/**
 * This is EgyptianWormRepositoryImpl class. This class takes in two params [maizeInsectApi]
 * and [egyptianArmyWormDao] and implements [AfricanArmyWormRepository]
 * */
class EgyptianWormRepositoryImpl(private val insectApi: MaizeInsectApi,
                                 private val egyptianArmyWormDao: EgyptianArmyWormDao) : EgyptianWormRepository {

    /**
     * This [getEgyptianArmyWorm] and return a liveData of Insects
     * */
    override fun getEgyptianArmyWorm(): LiveData<List<Insect>> {
        return egyptianArmyWormDao.getEgyptianWorm()
    }

    /**
     * This [requestEgyptianArmyWorm] is a suspend function which must be called from a coroutine
     * */
    override suspend fun requestEgyptianArmyWorm() = withContext(Dispatchers.IO) {
        val request = insectApi.getEgyptianArmyWorm()
        val egyptainList = request.insect
        egyptianArmyWormDao.insertEqyptianWorm(egyptainList)
    }
}