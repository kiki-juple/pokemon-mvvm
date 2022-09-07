package com.kiki.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(
    val id: Int?,
    val name: String,
    var isFavorite: Boolean
) : Parcelable