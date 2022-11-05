package com.bmk.data.data

import com.squareup.moshi.Json

data class DataResponse(


    @field:Json(name = "uidata")
    val uidata: List<UidataItem>,
    
    @field:Json(name = "logo-url")
    val logoUrl: String,

    
    @field:Json(name = "heading-text")
    val headingText: String
)

data class UidataItem(

    @field:Json(name = "uitype")
    val uitype: String,

    @field:Json(name = "value")
    val value: String?,

    @field:Json(name = "hint")
    val hint: String?,

    @field:Json(name = "key")
    val key: String?
)
