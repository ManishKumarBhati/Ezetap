package com.bmk.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

interface Repository {
    suspend fun getData(): Result<Response>
}

@Parcelize
data class Response(
    @JvmField val uidata: List<ItemList>,
    @JvmField val logoUrl: String,
    @JvmField val headingText: String
): Parcelable


@Parcelize
data class ItemList(
    @JvmField val uitype: String,
    @JvmField val value: String?,
    @JvmField val hint: String?,
    @JvmField val key: String?
):Parcelable
