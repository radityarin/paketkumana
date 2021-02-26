package com.radityarin.paketkumana.domain.usecase.courier

import com.radityarin.paketkumana.data.Resource
import com.radityarin.paketkumana.domain.model.Courier
import com.radityarin.paketkumana.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow

class GetListCourierInteractor(private val repository: AppRepository) :
    GetListCourierUseCase {
    override fun getListCourier(): Flow<Resource<List<Courier>>> =
        repository.getAllListCourier()
}