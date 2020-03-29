package com.anooplab.business.usecase

import com.anooplab.business.RxTest
import com.anooplab.business.model.FactsBusinessModel
import com.anooplab.business.model.mapToBusinessModel
import com.anooplab.core.model.FactItem
import com.anooplab.core.model.Facts
import com.anooplab.core.repository.IFactsRepository
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class GetFactsUseCaseTest : RxTest() {

    @Mock
    private lateinit var factsRepository: IFactsRepository

    private lateinit var getFactsUseCase: IGetFactsUseCase

    @Before
    fun setup() {
        getFactsUseCase = GetFactsUseCase(factsRepository)
    }

    @Test
    fun `Invoking getFactsUseCase should return FactsBusinessModel without any error`() {
        val facts = Facts().apply {
            title = "Test"
            factItemList = listOf(FactItem(), FactItem(), FactItem())
        }
        val testObserver = TestObserver<FactsBusinessModel>()
        whenever(factsRepository.getFacts()).thenReturn(Single.just(facts))

        getFactsUseCase().subscribe(testObserver)

        testObserver.awaitTerminalEvent()
        testObserver.assertCompleteNormally()
        testObserver.assertValue(facts.mapToBusinessModel())
    }

    @Test
    fun `Empty FactItem values should be filter out`() {
        val facts = Facts().apply {
            title = "Test"
            factItemList = listOf(FactItem(title = "Test1"), FactItem(), FactItem())
        }

        val testObserver = TestObserver<FactsBusinessModel>()
        whenever(factsRepository.getFacts()).thenReturn(Single.just(facts))

        getFactsUseCase().subscribe(testObserver)

        testObserver.awaitTerminalEvent()
        testObserver.assertCompleteNormally()
        testObserver.assertValue(facts.mapToBusinessModel())
        assert(testObserver.values()[0].factItemBusinessModelList.size == 1)
    }
}