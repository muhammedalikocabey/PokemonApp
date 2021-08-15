package com.example.pokemonapp.network

import com.example.pokemonapp.model.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET

interface PokeApiService {
    @GET(".")
    suspend fun getPokemonList(): Response<List<PokemonResponse>>
}