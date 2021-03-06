package com.anooplab.business.model

import com.anooplab.core.model.FactItem
import com.anooplab.core.model.Facts

fun Facts.mapToBusinessModel(): FactsBusinessModel {
    return FactsBusinessModel(
        title, factItemList.filter {
            // Remove empty factsItems from the list
            it.title != null || it.description != null || it.imageHref != null
        }.map { it.mapToBusinessModel() }
    )
}

fun FactItem.mapToBusinessModel(): FactItemBusinessModel {
    return FactItemBusinessModel(
        title, description, imageHref
    )
}