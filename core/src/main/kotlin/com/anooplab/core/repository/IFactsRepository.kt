package com.anooplab.core.repository

import com.anooplab.core.model.Facts
import io.reactivex.Single

interface IFactsRepository {
    fun getFacts(): Single<Facts>
}