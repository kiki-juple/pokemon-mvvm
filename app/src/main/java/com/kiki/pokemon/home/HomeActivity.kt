package com.kiki.pokemon.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.kiki.core.data.Resource
import com.kiki.core.ui.PokemonAdapter
import com.kiki.pokemon.databinding.ActivityHomeBinding
import com.kiki.pokemon.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener {
            try {
                startActivity(
                    Intent(
                        this,
                        Class.forName("com.kiki.pokemon.favorite.ui.FavoriteActivity")
                    )
                )
            } catch (e: Exception) {
                Snackbar.make(binding.root, "Module not found", Snackbar.LENGTH_SHORT).show()
            }
        }

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
                        binding.fab.isVisible = true
                        pokemonAdapter.submitList(pokemon.data)
                    }
                    is Resource.Error -> {
                        binding.error.isVisible = true
                        binding.tryAgain.setOnClickListener {
                            finish()
                            overridePendingTransition(0, 0)
                            startActivity(intent)
                            overridePendingTransition(0, 0)
                        }
                    }
                }
            }
        }
    }
}