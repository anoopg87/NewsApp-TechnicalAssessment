package com.anooplab.core.dagger

import com.anooplab.core.repository.IFactsRepository
import dagger.Component

@CoreScope
@Component(
    modules = [
        ApiModule::class,
        RepositoryModule::class
    ]
)
interface CoreComponent {
    fun getFactsRepository(): IFactsRepository
}