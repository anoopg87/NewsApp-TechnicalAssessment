package com.anooplab.newsapp.factslist.di

import com.anooplab.newsapp.factslist.FactsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(
    includes = [
        FactsListModule::class
    ]
)
abstract class FactListBuilder {

    @ContributesAndroidInjector
    internal abstract fun bindFactListFragment(): FactsListFragment
}