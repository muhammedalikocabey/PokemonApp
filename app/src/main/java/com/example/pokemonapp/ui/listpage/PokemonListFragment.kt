package com.example.pokemonapp.ui.listpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemonapp.databinding.FragmentPokemonListBinding
import com.example.pokemonapp.util.EventObserver
import com.example.pokemonapp.util.EventType
import com.example.pokemonapp.util.gone
import com.example.pokemonapp.util.visible

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
        initObservers()
        initListeners()

    }

    private fun initAdapter() {
        with(binding.rvPokemonList) {
            adapter = pokemonListAdapter
            layoutManager = LinearLayoutManager(requireContext())

        }

    }

    private fun initObservers() {
        with(viewModel) {
            pokeList.observe(viewLifecycleOwner) {
                binding.rvPokemonList.visible()
                pokemonListAdapter.submitList(it)
            }

            event.observe(viewLifecycleOwner, EventObserver {
                when (it) {
                    is EventType.Error -> {
                        if (pokemonListAdapter.itemCount > 0) {
                            binding.rvPokemonList.visible()
                            showToast(it.error)
                        } else {
                            binding.fetchingErrorText.visible()
                        }
                    }
                    is EventType.HideProgress -> hideProgress()
                    is EventType.ShowProgress -> showProgress()
                }
            })
        }
    }

    private fun initListeners() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            binding.fetchingErrorText.gone()
            binding.rvPokemonList.gone()
            viewModel.getPokeList()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    fun showProgress() = binding.progressView.progressBarContainer.visible()
    fun hideProgress() = binding.progressView.progressBarContainer.gone()

    override fun onItemClick(pokemon: PokemonListItemViewState) {
        val direction = PokemonListFragmentDirections.actionPokemonListToDetail(pokemon)
        navController.navigate(direction)
    }
}