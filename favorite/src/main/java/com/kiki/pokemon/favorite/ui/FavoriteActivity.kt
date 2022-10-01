package com.kiki.pokemon.favorite.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.kiki.core.ui.PokemonAdapter
import com.kiki.pokemon.detail.DetailActivity
import com.kiki.pokemon.di.FavoriteModule
import com.kiki.pokemon.favorite.databinding.ActivityFavoriteBinding
import com.kiki.pokemon.favorite.di.DaggerFavoriteComponent
import com.kiki.pokemon.favorite.di.ViewModelFactory
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel by viewModels<FavoriteViewModel> { factory }

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(this)
            .dependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavoriteModule::class.java
                )
            )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}