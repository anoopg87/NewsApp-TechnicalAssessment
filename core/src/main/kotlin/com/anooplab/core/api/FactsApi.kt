package com.anooplab.core.api

import com.anooplab.core.model.Facts
import io.reactivex.Single
import retrofit2.http.GET

interface FactsApi {
    @GET("s/2iodh4vg0eortkl/facts.json")
    fun getFacts(): Single<Facts>
}