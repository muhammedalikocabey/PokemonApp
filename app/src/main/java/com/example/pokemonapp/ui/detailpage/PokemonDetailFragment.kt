package com.example.pokemonapp.ui.detailpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.pokemonapp.databinding.FragmentPokemonDetailBinding
import com.example.pokemonapp.util.displayImage

class PokemonDetailFragment : Fragment() {
    private lateinit var binding: FragmentPokemonDetailBinding

    val args: PokemonDetailFragmentArgs? by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPokemonDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            it.findNavController().navigateUp()
        }

        initUi()

    }

    private fun initUi() {
        with(binding) {
            args?.let {
                toolbar.title = it.pokemon.name
                ivPokemon.displayImage(it.pokemon.pictureUrl)
                tvPokemonDesc.text = it.pokemon.description
            }
        }
    }
}