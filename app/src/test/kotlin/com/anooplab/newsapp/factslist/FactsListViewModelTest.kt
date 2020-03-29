package com.anooplab.newsapp.factslist

import com.anooplab.business.model.FactItemBusinessModel
import com.anooplab.business.model.FactsBusinessModel
import com.anooplab.business.usecase.IGetFactsUseCase
import com.anooplab.newsapp.R
import com.anooplab.newsapp.base.BaseTest
import com.anooplab.newsapp.factslist.model.mapToUiModel
import com.anooplab.newsapp.util.IConnectionManager
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class FactsListViewModelTest : BaseTest() {
    @Mock
    lateinit var getFactsUseCase: IGetFactsUseCase

    @Mock
    lateinit var connectionManager: IConnectionManager

    lateinit var viewModel: FactsListViewModel

    @Before
    fun setup() {
        viewModel = FactsListViewModel(getFactsUseCase, connectionManager)
    }

    @Test
    fun `User should see the lists of facts from network for the first showFacts call`() {

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
    fun `User should see the lists of facts from network for all the reloadFacts call`() {

        val factsBusinessModel = FactsBusinessModel().apply {
            title = "Test"
            factItemBusinessModelList =
                listOf(FactItemBusinessModel(), FactItemBusinessModel(), FactItemBusinessModel())
        }

        whenever(connectionManager.isConnectedToInternet()).thenReturn(true)
        whenever(getFactsUseCase()).thenReturn(Single.just(factsBusinessModel))

        viewModel.reloadFacts()
        viewModel.reloadFacts()

        verify(getFactsUseCase, times(2)).invoke()
        assertEquals(viewModel.loadingObserver.value, false)
        assertEquals(viewModel.factsObserver.value, factsBusinessModel.mapToUiModel())
    }

    @Test
    fun `User should see the lists of facts from local storage on second call to showFacts`() {

        val factsBusinessModel = FactsBusinessModel().apply {
            title = "Test"
            factItemBusinessModelList =
                listOf(FactItemBusinessModel(), FactItemBusinessModel(), FactItemBusinessModel())
        }

        whenever(connectionManager.isConnectedToInternet()).thenReturn(true)
        whenever(getFactsUseCase()).thenReturn(Single.just(factsBusinessModel))

        viewModel.showFacts()
        viewModel.showFacts()

        verify(getFactsUseCase, times(1)).invoke()
        assertEquals(viewModel.loadingObserver.value, false)
        assertEquals(viewModel.factsObserver.value, factsBusinessModel.mapToUiModel())
    }

    @Test
    fun `show error message on exception`() {

        whenever(connectionManager.isConnectedToInternet()).thenReturn(true)
        whenever(getFactsUseCase()).thenReturn(Single.error(Exception("Error")))

        viewModel.showFacts()

        verify(getFactsUseCase, times(1)).invoke()
        assertEquals(viewModel.loadingObserver.value, false)
        assertEquals(viewModel.errorObserver.value, R.string.error_in_fetching_data)
    }

    @Test
    fun `Should show error message on no internet connection available`() {

        whenever(connectionManager.isConnectedToInternet()).thenReturn(false)

        viewModel.showFacts()

        verify(getFactsUseCase, never()).invoke()
        assertEquals(viewModel.errorObserver.value, R.string.please_check_the_internet_connection_error)
    }
}