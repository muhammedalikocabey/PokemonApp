package com.example.pokemonapp.ui.listpage

import java.io.Serializable

class PokemonListItemViewState(
    val id: Int,
    val name: String,
    val description: String,
    val pictureUrl: String,
) : Serializable {
    fun areItemsTheSame(newItem: PokemonListItemViewState): Boolean {
        return id == newItem.id
    }

    fun areContentsTheSame(newItem: PokemonListItemViewState): Boolean {
        return name.equals(newItem.name) &&
                description.equals(newItem.description) &&
                pictureUrl.equals(newItem.pictureUrl)
    }
}