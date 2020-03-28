package com.anooplab.core

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.observers.TestObserver
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.mockito.MockitoAnnotations

abstract class RxTest {
    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        val scheduler = Schedulers.trampoline()
        RxJavaPlugins.setInitIoSchedulerHandler { scheduler }
        RxJavaPlugins.setInitComputationSchedulerHandler { scheduler }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler }
        RxJavaPlugins.setInitSingleSchedulerHandler { scheduler }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler }
    }

    @After
    fun tearDown() {
        RxAndroidPlugins.reset()
    }

    fun <T> TestObserver<T>.assertCompleteNormally() {
        this.awaitTerminalEvent()
        this.assertNoErrors()
        this.assertComplete()
    }
}