package org.wikiedufoundation.wikiedudashboard.di

import com.chuks.maizestemapp.capturedinsect.viewmodel.CapturedInsectViewModel
import com.chuks.maizestemapp.categoriesofspecies.africanarmyworm.viewmodel.AfricanArmyWormViewModel
import com.chuks.maizestemapp.categoriesofspecies.egyptianarmyworm.viewmodel.EgyptianWormViewModel
import com.chuks.maizestemapp.categoriesofspecies.fallarmyworm.viewmodel.FallArmyWormViewModel
import com.chuks.maizestemapp.maizestemplot.viewmodel.MaizePlotViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * Use the [viewModelModule] to creating viewModel instance
 **/
val viewModelModule = module {
    viewModel { CapturedInsectViewModel(get()) }
    viewModel { AfricanArmyWormViewModel(get()) }
    viewModel { EgyptianWormViewModel(get()) }
    viewModel { FallArmyWormViewModel(get()) }
    viewModel { MaizePlotViewModel(get()) }

}