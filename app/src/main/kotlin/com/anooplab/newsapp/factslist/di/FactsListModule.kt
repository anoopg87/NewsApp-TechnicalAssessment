package com.anooplab.newsapp.factslist.di

import androidx.lifecycle.ViewModel
import com.anooplab.newsapp.dagger.ViewModelKey
import com.anooplab.newsapp.factslist.FactsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FactsListModule {
    @Binds
    @IntoMap
    @ViewModelKey(FactsListViewModel::class)
    abstract fun bindFactsLisViewModel(viewModel: FactsListViewModel): ViewModel
}