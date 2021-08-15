package com.example.pokemonapp.data.usecase

import com.example.pokemonapp.data.repository.PokemonRepository
import com.example.pokemonapp.model.ApiResult
import com.example.pokemonapp.model.PokemonResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

object GetPokemonListUseCase : BaseUseCase() {

    suspend fun getPokeList(): Flow<ApiResult<List<PokemonResponse>>> {
        return flow {
            emit(PokemonRepository.getPokeList())
        }.flowOn(dispatcher)
    }
}