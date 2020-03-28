package com.anooplab.core.dagger

import com.anooplab.core.api.FactsApi
import com.anooplab.core.repository.FactsRepository
import com.anooplab.core.repository.IFactsRepository
import dagger.Module
import dagger.Provides

@Module
internal class RepositoryModule {
    @Provides
    @CoreScope
    fun providesFactsRepository(factsApi: FactsApi): IFactsRepository {
        return FactsRepository(factsApi)
    }
}