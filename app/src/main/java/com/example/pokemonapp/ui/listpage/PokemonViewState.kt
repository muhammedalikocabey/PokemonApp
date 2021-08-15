package com.example.pokemonapp.ui.listpage

class PokemonViewState(
    val id: Int,
    val name: String,
    val description: String,
    val pictureUrl: String,
) {
    fun areItemsTheSame(newItem: PokemonViewState): Boolean {
        return id == newItem.id
    }

    fun areContentsTheSame(newItem: PokemonViewState): Boolean {
        return name.equals(newItem.name) &&
                description.equals(newItem.description) &&
                pictureUrl.equals(newItem.pictureUrl)
    }
}