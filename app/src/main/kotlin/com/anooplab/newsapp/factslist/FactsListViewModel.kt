package com.anooplab.newsapp.factslist

import androidx.lifecycle.LiveData
import com.anooplab.business.usecase.IGetFactsUseCase
import com.anooplab.newsapp.base.BaseViewModel
import com.anooplab.newsapp.base.SingleLiveEvent
import com.anooplab.newsapp.factslist.model.FactsUiModel
import com.anooplab.newsapp.factslist.model.mapToUiModel
import com.anooplab.newsapp.util.IConnectionManager
import timber.log.Timber
import javax.inject.Inject

class FactsListViewModel @Inject constructor(
    private val getFactsUseCase: IGetFactsUseCase,
    private val connectionManager: IConnectionManager
) : BaseViewModel() {
    private var facts: FactsUiModel? = null

    private val factsLiveData = SingleLiveEvent<FactsUiModel>()
    val factsObserver: LiveData<FactsUiModel> = factsLiveData // This is make the SingleLiveEvent immutable

    private val loadingLiveData = SingleLiveEvent<Boolean>()
    val loadingObserver: LiveData<Boolean> = loadingLiveData

    private val errorLiveData = SingleLiveEvent<String>()
    val errorObserver: LiveData<String> = errorLiveData

    fun showFacts() {
        // Check we already have data stored locally. If yes we just show that information
        // This will avoid re fetching values on rotations
        facts?.let {
            factsLiveData.postValue(it)
        } ?: reloadFacts()
    }

    fun reloadFacts() {
        // This is called when the user swipe to refresh
        // No matter if there is already persisted data we fetching values using use case
        loadingLiveData.value = true
        if (connectionManager.isConnectedToInternet()) {
            getFactsUseCase().subscribe({
                factsLiveData.postValue(it.mapToUiModel())
                facts = it.mapToUiModel()
                loadingLiveData.value = false
            }, {
                Timber.d("Error in fetching facts", it)
                loadingLiveData.value = false
                errorLiveData.value = "Error in fetching data"
            }).addSubscription()
        } else {
            loadingLiveData.value = false
            errorLiveData.value = "Please check the internet connection"
        }
    }
}