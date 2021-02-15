package com.radityarin.paketkumana.domain.usecase.courier

import com.radityarin.paketkumana.data.Resource
import com.radityarin.paketkumana.domain.model.Courier
import kotlinx.coroutines.flow.Flow

interface GetListCourierUseCase {
    fun getListCourier(): Flow<Resource<List<Courier>>>
}