package org.wikiedufoundation.wikiedudashboard.di

import com.chuks.maizestemapp.capturedinsect.repository.CapturedInsectRepository
import com.chuks.maizestemapp.capturedinsect.repository.CapturedInsectRepositoryImpl
import com.chuks.maizestemapp.categoriesofspecies.africanarmyworm.repository.AfricanArmmyWormRepositoryImpl
import com.chuks.maizestemapp.categoriesofspecies.africanarmyworm.repository.AfricanArmyWormRepository
import com.chuks.maizestemapp.categoriesofspecies.egyptianarmyworm.repository.EgyptianWormRepository
import com.chuks.maizestemapp.categoriesofspecies.egyptianarmyworm.repository.EgyptianWormRepositoryImpl
import com.chuks.maizestemapp.categoriesofspecies.fallarmyworm.repository.FallArmyWormRepository
import com.chuks.maizestemapp.categoriesofspecies.fallarmyworm.repository.FallArmyWormRepositoryImpl
import com.chuks.maizestemapp.maizestemplot.repository.MaizePlotRepository
import com.chuks.maizestemapp.maizestemplot.repository.MaizePlotRepositoryImpl
import org.koin.dsl.module


/**
 * Use the [repositoryModule] to creating repository instance
 **/
val repositoryModule = module {
    single <CapturedInsectRepository> { CapturedInsectRepositoryImpl(get(), get()) }
    single <AfricanArmyWormRepository> { AfricanArmmyWormRepositoryImpl(get(), get()) }
    single <EgyptianWormRepository> { EgyptianWormRepositoryImpl(get(), get()) }
    single <FallArmyWormRepository> { FallArmyWormRepositoryImpl(get(), get()) }
    single <MaizePlotRepository> { MaizePlotRepositoryImpl(get(), get()) }

}