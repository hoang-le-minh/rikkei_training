package com.rikkei.training.hiltapp.di

import com.rikkei.training.hiltapp.constant.Config
import com.rikkei.training.hiltapp.data.apis.PostAPI
import com.rikkei.training.hiltapp.data.apis.QuestionAPI
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor{
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient{
        val builder = OkHttpClient.Builder()
        builder.interceptors().add(httpLoggingInterceptor)
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(): MoshiConverterFactory{
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    @Singleton
    @Named("StackExchangeSite")
    fun provideRetrofitStackExchange(okHttpClient: OkHttpClient, moshiConverterFactory: MoshiConverterFactory): Retrofit{
        return Retrofit.Builder().addConverterFactory(moshiConverterFactory).baseUrl(Config.StackExchangeUrl).client(okHttpClient).build()
    }

    @Provides
    fun provideQuestionAPI(@Named("StackExchangeSite") retrofit: Retrofit): QuestionAPI{
        return retrofit.create(QuestionAPI::class.java)
    }

    @Provides
    @Singleton
    @Named("JsonPlaceHolderSite")
    fun provideRetrofitJsonPlaceHolder(okHttpClient: OkHttpClient, moshiConverterFactory: MoshiConverterFactory): Retrofit{
        return Retrofit.Builder().addConverterFactory(moshiConverterFactory).baseUrl(Config.JsonPlaceHolder).client(okHttpClient).build()
    }

    @Provides
    fun providePostAPI(@Named("JsonPlaceHolderSite") retrofit: Retrofit): PostAPI{
        return retrofit.create(PostAPI::class.java)
    }

}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class StackExchangeSite()

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class JsonPlaceHolderSite()