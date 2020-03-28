package com.anooplab.newsapp

import com.anooplab.business.dagger.BusinessComponent
import com.anooplab.business.dagger.DaggerBusinessComponent
import com.anooplab.core.dagger.CoreComponent
import com.anooplab.core.dagger.DaggerCoreComponent
import com.anooplab.newsapp.dagger.AppModule
import com.anooplab.newsapp.dagger.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {
    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.builder().build()
    }
    private val businessComponent: BusinessComponent by lazy {
        DaggerBusinessComponent.builder()
            .coreComponent(coreComponent)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .businessComponent(businessComponent)
            .coreComponent(coreComponent)
            .build()
}