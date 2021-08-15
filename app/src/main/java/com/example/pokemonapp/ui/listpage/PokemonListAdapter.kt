package com.example.pokemonapp.ui.listpage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.databinding.ItemPokemonListItemBinding
import com.example.pokemonapp.util.displayImage

class PokemonListAdapter(private val itemClickListener: PokemonItemClickListener) :
    ListAdapter<PokemonViewState, PokemonViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<PokemonViewState> =
            object : DiffUtil.ItemCallback<PokemonViewState>() {
                override fun areItemsTheSame(
                    oldItem: PokemonViewState,
                    newItem: PokemonViewState,
                ): Boolean {
                    return oldItem.areItemsTheSame(newItem)
                }

                override fun areContentsTheSame(
                    oldItem: PokemonViewState,
                    newItem: PokemonViewState,
                ): Boolean {
                    return oldItem.areContentsTheSame(newItem)
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding =
            ItemPokemonListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(item)
        }
    }

}

class PokemonViewHolder(
    private val binding: ItemPokemonListItemBinding,
    private val itemClickListener: PokemonItemClickListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: PokemonViewState) {
        binding.apply {
            ivPokemon.displayImage(item.pictureUrl)
            tvPokemonName.text = item.name
            tvPokemonDesc.text = item.description
            root.setOnClickListener { itemClickListener.onItemClick(item) }
        }
    }
}

interface PokemonItemClickListener {
    fun onItemClick(pokemon: PokemonViewState)
}
