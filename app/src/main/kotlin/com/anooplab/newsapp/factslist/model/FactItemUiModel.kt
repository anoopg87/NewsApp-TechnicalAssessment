package com.anooplab.newsapp.factslist.model

data class FactsUiModel(
    var title: String?,
    var factsItemUiModels: List<FactItemUiModel> = listOf()
)

data class FactItemUiModel(
    var title: String? = null,
    var description: String? = null,
    var imageHref: String? = null
)