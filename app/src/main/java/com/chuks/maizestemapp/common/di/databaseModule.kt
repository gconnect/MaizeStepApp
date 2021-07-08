package org.wikiedufoundation.wikiedudashboard.di

import android.app.Application
import androidx.room.Room.databaseBuilder
import com.chuks.maizestemapp.capturedinsect.dao.InsectDao
import com.chuks.maizestemapp.categoriesofspecies.africanarmyworm.dao.AfricanArmyWormDao
import com.chuks.maizestemapp.categoriesofspecies.egyptianarmyworm.dao.EgyptianArmyWormDao
import com.chuks.maizestemapp.categoriesofspecies.fallarmyworm.dao.FallArmyWormDao
import com.chuks.maizestemapp.common.data.localdatabase.InsectDatabase
import com.chuks.maizestemapp.maizestemplot.dao.MaizePlotDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


/**
 * Use the [databaseModule] to creating database and dao instance
 **/
val databaseModule = module {
    single { provideDatabase(androidApplication()) }
    single { provideCapturedInsectDao(get()) }
    single { provideAfricanWormDao(get()) }
    single { provideEgyptianWormDao(get()) }
    single { provideFallArmyWormDao(get()) }
    single { provideMaizePlotDao(get()) }
}

/**
 * Use the [provideDatabase] to provide a database instance
 * */
fun provideDatabase(application: Application): InsectDatabase {
    return databaseBuilder(application, InsectDatabase::class.java, "insect_database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
}

/**
 * Use the [provideCapturedInsectDao] to provide a provideCampaignListDao instance
 * */
fun provideCapturedInsectDao(database: InsectDatabase): InsectDao = database.capturedInsectDao


/**
* Use the [provideAfricanWormDao] to provide a provideCampaignListDao instance
* */
fun provideAfricanWormDao(database: InsectDatabase): AfricanArmyWormDao = database.africanArmyWormDao

/**
 * Use the [provideEgyptianWormDao] to provide a provideCampaignListDao instance
 * */
fun provideEgyptianWormDao(database: InsectDatabase): EgyptianArmyWormDao = database.egyptianArmyWormDao


/**
 * Use the [provideFallArmyWormDao] to provide a provideCampaignListDao instance
 * */
fun provideFallArmyWormDao(database: InsectDatabase): FallArmyWormDao = database.fallArmyWormDao

/**
 * Use the [provideFallArmyWormDao] to provide a provideCampaignListDao instance
 * */
fun provideMaizePlotDao(database: InsectDatabase): MaizePlotDao = database.maizePlot

