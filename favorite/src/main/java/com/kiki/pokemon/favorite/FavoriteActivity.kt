package com.kiki.pokemon.favorite

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.kiki.core.ui.PokemonAdapter
import com.kiki.pokemon.detail.DetailActivity
import com.kiki.pokemon.favorite.databinding.ActivityFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private val viewModel by viewModels<FavoriteViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pokemonAdapter = PokemonAdapter()
        binding.rvFavorite.adapter = pokemonAdapter
        pokemonAdapter.onItemClick = {
            startActivity(
                Intent(this, DetailActivity::class.java)
                    .putExtra(DetailActivity.POKEMON, it)
            )
        }

        viewModel.favoritePokemon.observe(this) {
            pokemonAdapter.submitList(it)
        }
    }
}