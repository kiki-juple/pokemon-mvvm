package com.kiki.pokemon.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.kiki.core.data.Resource
import com.kiki.core.ui.PokemonAdapter
import com.kiki.pokemon.databinding.ActivityMainBinding
import com.kiki.pokemon.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pokemonAdapter = PokemonAdapter()
        binding.rvPokemon.adapter = pokemonAdapter
        pokemonAdapter.onItemClick = {
            startActivity(
                Intent(this, DetailActivity::class.java)
                    .putExtra(DetailActivity.POKEMON, it)
            )
        }

        viewModel.pokemon.observe(this) { pokemon ->
            if (pokemon != null) {
                when (pokemon) {
                    is Resource.Loading -> binding.loading.isVisible = true
                    is Resource.Success -> {
                        binding.loading.isVisible = false
                        pokemonAdapter.submitList(pokemon.data)
                    }
                    is Resource.Error -> Unit
                }
            }
        }

    }
}