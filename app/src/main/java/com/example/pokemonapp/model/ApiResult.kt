package com.example.pokemonapp.model

sealed class ApiResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : ApiResult<T>()
    data class Error(val error: String) : ApiResult<Nothing>()
}