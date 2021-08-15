package com.example.pokemonapp.ui.listpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.FragmentPokemonListBinding

class PokemonListFragment : Fragment(), PokemonItemClickListener {

    private lateinit var navController: NavController

    lateinit var binding: FragmentPokemonListBinding
    private val viewModel: PokemonListViewModel by viewModels()

    private val pokemonListAdapter by lazy { PokemonListAdapter(this) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPokemonListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        initAdapter()
        initObserver()

        viewModel.getPokeList()

    }

    private fun initAdapter() {
        with(binding.rvPokemonList) {
            adapter = pokemonListAdapter
            layoutManager = LinearLayoutManager(requireContext())

        }

    }

    private fun initObserver() {
        viewModel.pokeList.observe(viewLifecycleOwner) {
            pokemonListAdapter.submitList(it)
        }
    }

    override fun onItemClick(pokemon: PokemonViewState) {
        navController.navigate(R.id.action_navigation_pokemon_list_to_navigation_pokemon_detail)
    }
}