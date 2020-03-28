package com.anooplab.business.dagger

import com.anooplab.business.usecase.GetFactsUseCase
import com.anooplab.business.usecase.IGetFactsUseCase
import com.anooplab.core.repository.IFactsRepository
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    @BusinessScope
    fun providesGetFactsUseCase(factsRepository: IFactsRepository): IGetFactsUseCase {
        return GetFactsUseCase(factsRepository)
    }
}