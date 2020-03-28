package com.anooplab.core.model

import com.google.gson.annotations.SerializedName

data class Facts(
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("rows")
    var factItemList: List<FactItem> = listOf()
)

data class FactItem(
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("imageHref")
    var imageHref: String? = null
)