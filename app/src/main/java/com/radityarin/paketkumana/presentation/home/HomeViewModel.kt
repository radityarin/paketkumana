package com.radityarin.paketkumana.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.radityarin.paketkumana.data.Resource
import com.radityarin.paketkumana.data.source.remote.network.ApiResponse
import com.radityarin.paketkumana.data.source.remote.response.cekresi.CekResi
import com.radityarin.paketkumana.data.source.remote.response.cekresi.CekResiResponse
import com.radityarin.paketkumana.domain.model.Courier
import com.radityarin.paketkumana.domain.usecase.cekresi.GetCekResiUseCase
import com.radityarin.paketkumana.domain.usecase.courier.GetListCourierUseCase

class HomeViewModel(
    private val getListCourierUseCase: GetListCourierUseCase,
    private val getCekResiUseCase: GetCekResiUseCase
) : ViewModel() {

    fun getListCourier(): LiveData<Resource<List<Courier>>> =
        getListCourierUseCase.getListCourier().asLiveData()

    suspend fun getCekResi(courierName : String,
                   awb : String): LiveData<CekResiResponse> =
        getCekResiUseCase.getCekResi(courierName,awb).asLiveData()
}