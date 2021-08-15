package com.example.pokemonapp.data.usecase

import kotlinx.coroutines.Dispatchers

open class BaseUseCase {
    protected val dispatcher = Dispatchers.Default
}