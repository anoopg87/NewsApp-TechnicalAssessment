package com.anooplab.core.dagger

import com.anooplab.core.BuildConfig
import com.anooplab.core.api.FactsApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class ApiModule {

    @Provides
    @CoreScope
    fun provideFactsApi(): FactsApi {
        return getRetrofitApi(BuildConfig.FACTS_BASE_URL, FactsApi::class.java)
    }

    private fun <T> getRetrofitApi(
        baseUrl: String,
        apiClass: Class<T>,
        callAdapter: CallAdapter.Factory = RxJava2CallAdapterFactory.create()
    ): T {

        val clientBuilder = OkHttpClient.Builder()
        val client = clientBuilder
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        val apiAdapter = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(callAdapter)
            .build()
        return apiAdapter.create(apiClass)
    }
}

