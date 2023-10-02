package com.artman.compose_example.di

import com.artman.compose_example.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {

    private const val baseUrl = Constants.BASE_URL

    private val releaseHttpClientBuilder: OkHttpClient.Builder
        get() = OkHttpClient.Builder()
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val request = chain.request()
                    .newBuilder().build()
                try {
                    chain.proceed(request)
                } catch (e: Throwable) {

                    Response.Builder()
                        .request(request)
                        .protocol(Protocol.HTTP_2)
                        .message(e.toString())
                        .code(500)
                        .body(e.toString().toResponseBody())
                        .build()
                }
            }

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(releaseHttpClientBuilder.build())
            .build()
    }

}