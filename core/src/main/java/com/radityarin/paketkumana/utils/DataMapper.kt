package com.radityarin.paketkumana.utils

import com.radityarin.paketkumana.data.source.local.entity.CourierEntity
import com.radityarin.paketkumana.data.source.remote.response.cekresi.CekResi
import com.radityarin.paketkumana.data.source.remote.response.cekresi.CekResiResponse
import com.radityarin.paketkumana.data.source.remote.response.listcourier.ListCourierResponse
import com.radityarin.paketkumana.domain.model.Courier


object DataMapper {

    fun mapListCourierEntityToListCourier(
        input: List<CourierEntity>
    ): List<Courier> {
        val courierList = ArrayList<Courier>()
        input.map {
            val courier = Courier(
                code = it.code,
                description = it.description
            )
            courierList.add(courier)
        }
        return courierList
    }

    fun mapListCourierResponseToListCourier(
        input: ListCourierResponse
    ): List<CourierEntity> {
        val courierList = ArrayList<CourierEntity>()
        input.map {
            val courier = CourierEntity(
                code = it.code,
                description = it.description
            )
            courierList.add(courier)
        }
        return courierList
    }

    fun mapDomainToEntity(input: CekResiResponse) = CekResi(
        detail = input.cekResi?.detail!!,
        history = input.cekResi?.history!!,
        summary = input.cekResi?.summary!!
    )
}