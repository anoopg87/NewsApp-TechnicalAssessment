package com.anooplab.core.repository

import com.anooplab.core.RxTest
import com.anooplab.core.api.FactsApi
import com.anooplab.core.model.FactItem
import com.anooplab.core.model.Facts
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class FactsRepositoryTest : RxTest() {

    @Mock
    private lateinit var factsApi: FactsApi

    private lateinit var factsRepository: IFactsRepository

    @Before
    fun setup() {
        factsRepository = FactsRepository(factsApi)
    }

    @Test
    fun `getFacts in factsRepository should return Facts without any error`() {
        // Assign
        val facts = Facts().apply {
            title = "Test"
            factItemList = listOf(FactItem(), FactItem(), FactItem())
        }
        whenever(factsApi.getFacts()).thenReturn(Single.just(facts))
        val testObserver = TestObserver<Facts>()

        // Act
        factsRepository.getFacts().subscribe(testObserver)

        // Assert
        testObserver.awaitTerminalEvent()
        testObserver.assertCompleteNormally()
        testObserver.assertValue(facts)
    }
}
