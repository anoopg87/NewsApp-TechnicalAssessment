package com.anooplab.newsapp.main.di

import com.anooplab.newsapp.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainBuilder {
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}