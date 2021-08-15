package com.example.pokemonapp.ui.listpage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.data.usecase.GetPokemonListUseCase
import com.example.pokemonapp.model.ApiResult
import com.example.pokemonapp.model.PokemonResponse
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PokemonListViewModel : ViewModel() {

    private val _pokeList = MutableLiveData<List<PokemonViewState>>()
    val pokeList: LiveData<List<PokemonViewState>> = _pokeList

    fun getPokeList() {
        viewModelScope.launch {
            GetPokemonListUseCase.getPokeList().collect {
                when (it) {
                    is ApiResult.Success -> _pokeList.value = it.data.mapToViewState()
                    is ApiResult.Error -> Log.d("Yusuf", it.error)
                }
            }
        }
    }

    fun List<PokemonResponse>.mapToViewState() = ArrayList<PokemonViewState>().apply {
        for (poke in this@mapToViewState) {
            add(PokemonViewState(poke.id, poke.name, poke.description, poke.imageUrl))
        }
    }
}