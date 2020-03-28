package com.anooplab.newsapp.factslist.model

import com.anooplab.business.model.FactItemBusinessModel
import com.anooplab.business.model.FactsBusinessModel

fun FactsBusinessModel.mapToUiModel(): FactsUiModel {
    return FactsUiModel(
        title, factItemBusinessModelList.map { it.mapToUiModel() }
    )
}

fun FactItemBusinessModel.mapToUiModel(): FactItemUiModel {
    return FactItemUiModel(
        title, description, imageHref
    )
}