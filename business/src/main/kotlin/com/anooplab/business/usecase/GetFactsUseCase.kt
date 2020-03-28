package com.anooplab.business.usecase

import com.anooplab.business.model.FactsBusinessModel
import com.anooplab.business.model.mapToBusinessModel
import com.anooplab.core.repository.IFactsRepository
import io.reactivex.Single

interface IGetFactsUseCase {
    operator fun invoke(): Single<FactsBusinessModel>
}

class GetFactsUseCase(
    private val factsRepository: IFactsRepository
) : IGetFactsUseCase {
    override fun invoke(): Single<FactsBusinessModel> {
        return factsRepository.getFacts().map { it.mapToBusinessModel() }
    }
}