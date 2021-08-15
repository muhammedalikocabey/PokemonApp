package com.example.pokemonapp.data.repository

import com.example.pokemonapp.model.ApiResult
import com.example.pokemonapp.model.PokemonResponse
import com.example.pokemonapp.network.NetworkManager

object PokemonRepository {
    suspend fun getPokeList(): ApiResult<List<PokemonResponse>> {
        NetworkManager.apply {
            return callApi { apiService.getPokemonList() }
        }
    }
}