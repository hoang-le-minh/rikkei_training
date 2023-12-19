package com.rikkei.training.hiltapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class DefaultDispatchers

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class IODispatchers


@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class MainDispatchers

@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule {

    @Provides
    @IODispatchers
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @MainDispatchers
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @DefaultDispatchers
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}