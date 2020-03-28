package com.anooplab.business.dagger

import com.anooplab.business.usecase.IGetFactsUseCase
import com.anooplab.core.dagger.CoreComponent
import dagger.Component

@Component(
    modules = [
        UseCaseModule::class
    ],
    dependencies = [
        CoreComponent::class
    ]
)
@BusinessScope
interface BusinessComponent {
    fun getFactsUserCase(): IGetFactsUseCase
}