package com.example.pokemonapp.network

import com.example.pokemonapp.constants.CloudConfig
import com.example.pokemonapp.model.ApiResult
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object NetworkManager {

    val apiService = createApiService()

    private fun createApiService(): PokeApiService {
        return getRetrofit().create()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .client(getOkHttpClient())
            .baseUrl(CloudConfig.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder().apply {
            addInterceptor(getLoggingInterceptor())
            connectTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
        }.build()
    }

    private fun getLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    suspend fun <T : Any> callApi(call: suspend () -> Response<T>): ApiResult<T> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful) {
                ApiResult.Success(response.body()!!)
            } else {
                ApiResult.Error(response.errorBody().toString())
            }
        } catch (e: Exception) {
            ApiResult.Error("Error Occurred!")
        }
    }
}