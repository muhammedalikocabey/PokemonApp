package com.example.pokemonapp.ui.listpage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemonapp.databinding.FragmentPokemonListBinding

class PokemonListFragment : Fragment(), PokemonItemClickListener {

    lateinit var binding: FragmentPokemonListBinding
    private val viewModel: PokemonListViewModel by viewModels()

    private val adapter by lazy { PokemonListAdapter(this) }


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

        initAdapter()
        initObserver()

        viewModel.getPokeList()
    }

    private fun initAdapter() {
        binding.rvPokemonList.adapter = adapter
        binding.rvPokemonList.layoutManager = LinearLayoutManager(requireContext())

    }

    private fun initObserver() {
        viewModel.pokeList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onItemClick(pokemon: PokemonViewState) {
        Log.d("YUSUF", "Item Clicked -> ${pokemon.id} - ${pokemon.name}")


    }
}