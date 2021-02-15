package com.radityarin.paketkumana.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Courier(
    val code: String,
    val description: String
) : Parcelable