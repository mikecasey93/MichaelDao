package com.example.android.codelabs.paging.di

import com.example.android.codelabs.paging.api.ApiDetails
import com.example.android.codelabs.paging.api.ApiEndPoints
import com.example.android.codelabs.paging.repository.Repository
import com.example.android.codelabs.paging.repository.RepositoryImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class MainModule {

    @Provides
    fun gson(): Gson {
        return Gson()
    }

    @Provides
    fun loggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun convertor(gson: Gson) = GsonConverterFactory.create()

    @Provides
    fun provideOkHttpClient(
        logging: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(ApiDetails.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(converterFactory)
        .build()

    @Provides
    fun apiInstance(retrofit: Retrofit): ApiEndPoints = retrofit.create(ApiEndPoints::class.java)

    @Provides
    fun providesRepository(apiEndPoints: ApiEndPoints): Repository {
        return RepositoryImpl(apiEndPoints)
    }

}