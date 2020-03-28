package com.anooplab.newsapp.dagger

import android.content.Context
import com.anooplab.newsapp.util.ConnectionManager
import com.anooplab.newsapp.util.IConnectionManager
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val context: Context) {

    @Provides
    @AppScope
    fun providesApplicationContext(): Context {
        return context
    }

    @Provides
    @AppScope
    fun providesConnectionManager(): IConnectionManager {
        return ConnectionManager(context)
    }
}