package com.anooplab.business.model

data class FactsBusinessModel(
    var title: String? = null,
    var factItemBusinessModelList: List<FactItemBusinessModel> = listOf()
)

data class FactItemBusinessModel(
    var title: String? = null,
    var description: String? = null,
    var imageHref: String? = null
)