package com.anooplab.newsapp.dagger

import android.app.Application
import com.anooplab.business.dagger.BusinessComponent
import com.anooplab.core.dagger.CoreComponent
import com.anooplab.newsapp.factslist.di.FactListBuilder
import com.anooplab.newsapp.main.di.MainBuilder
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule

@AppScope
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        MainBuilder::class,
        FactListBuilder::class,
        AppModule::class
    ],
    dependencies = [BusinessComponent::class, CoreComponent::class]
)
internal interface AppComponent : AndroidInjector<DaggerApplication> {
    fun inject(app: Application)
}