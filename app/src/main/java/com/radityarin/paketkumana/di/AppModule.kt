package com.radityarin.paketkumana.di

import com.radityarin.paketkumana.domain.usecase.cekresi.GetCekResiInteractor
import com.radityarin.paketkumana.domain.usecase.cekresi.GetCekResiUseCase
import com.radityarin.paketkumana.domain.usecase.courier.GetListCourierInteractor
import com.radityarin.paketkumana.domain.usecase.courier.GetListCourierUseCase
import com.radityarin.paketkumana.presentation.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    single<GetListCourierUseCase> { GetListCourierInteractor(get()) }
    single<GetCekResiUseCase> { GetCekResiInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get()) }
}