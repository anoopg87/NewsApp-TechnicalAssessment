package com.anooplab.newsapp.factslist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.anooplab.business.model.FactItemBusinessModel
import com.anooplab.business.model.FactsBusinessModel
import com.anooplab.business.usecase.IGetFactsUseCase
import com.anooplab.newsapp.factslist.model.mapToUiModel
import com.anooplab.newsapp.util.IConnectionManager
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class FactsListViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var getFactsUseCase: IGetFactsUseCase

    @Mock
    lateinit var connectionManager: IConnectionManager

    lateinit var viewModel: FactsListViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = FactsListViewModel(getFactsUseCase, connectionManager)
    }

    @Test
    fun `User should see the lists of facts on successful fetch of data`() {
        val observer: Observer<Boolean> = com.anooplab.newsapp.mock()
        viewModel.loadingObserver.observeForever(observer)

        val factsBusinessModel = FactsBusinessModel().apply {
            title = "Test"
            factItemBusinessModelList =
                listOf(FactItemBusinessModel(), FactItemBusinessModel(), FactItemBusinessModel())
        }

        whenever(connectionManager.isConnectedToInternet()).thenReturn(true)
        whenever(getFactsUseCase()).thenReturn(Single.just(factsBusinessModel))

        viewModel.showFacts()

        assertEquals(viewModel.loadingObserver.value, false)
        assertEquals(viewModel.factsObserver.value, factsBusinessModel.mapToUiModel())
    }

    @Test
    fun `Should show error message on no internet connection available`() {
        whenever(connectionManager.isConnectedToInternet()).thenReturn(false)

        viewModel.showFacts()

        assertEquals(viewModel.errorObserver.value, "Please check the internet connection")
    }
}