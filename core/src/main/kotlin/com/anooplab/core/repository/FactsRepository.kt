package com.anooplab.core.repository

import com.anooplab.core.api.FactsApi
import com.anooplab.core.extentions.RxRepository
import com.anooplab.core.model.Facts
import io.reactivex.Single

internal class FactsRepository(
    private var factsApi: FactsApi
) : RxRepository, IFactsRepository {
    override fun getFacts(): Single<Facts> {
        return factsApi.getFacts().applySchedulers()
    }
}