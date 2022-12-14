package com.kiki.pokemon.detail

import android.os.Build.VERSION
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import coil.load
import com.google.android.material.snackbar.Snackbar
import com.kiki.core.data.source.remote.network.ApiResponse
import com.kiki.core.domain.model.Pokemon
import com.kiki.pokemon.R
import com.kiki.pokemon.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val pokemon = if (VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(POKEMON, Pokemon::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(POKEMON)
        }
        if (pokemon != null) {
            showDetailPokemon(pokemon.name)
            setFavorite(pokemon.isFavorite)
            var favorite = pokemon.isFavorite
            val message = if (!favorite) {
                getString(R.string.message_add_favorite,
                    pokemon.name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()
                    })
            } else {
                getString(R.string.message_remove_favorite,
                    pokemon.name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()
                    })
            }
            binding.fab.setOnClickListener {
                Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
                favorite = !favorite
                detailViewModel.setFavoritePokemon(pokemon, favorite)
                setFavorite(favorite)
            }
        }
    }

    private fun showDetailPokemon(name: String) {
        detailViewModel.getPokemonDetail(name).observe(this@DetailActivity) { response ->
            when (response) {
                is ApiResponse.Success -> {
                    response.data.apply {
                        binding.apply {
                            pokemonImage.load(sprites.other.officialArtwork.frontDefault)
                            pokemonName.text = name.uppercase()
                            tvExp.text = getString(R.string.exp, baseExperience)
                            tvHeight.text = getString(R.string.weight, weight)
                            tvWeight.text = getString(R.string.height, height)
                            abilities.forEach {
                                val tv = TextView(this@DetailActivity)
                                tv.text = it.ability.name
                                layoutAbility.addView(tv)
                            }
                            stats.forEach {
                                val tv = TextView(this@DetailActivity)
                                tv.text = getString(R.string.stat_item, it.stat.name, it.baseStat)
                                layoutStats.addView(tv)
                            }
                            loading.isVisible = false
                            error.isVisible = false
                            fab.isVisible = true
                        }
                    }
                }
                is ApiResponse.Error -> {
                    binding.apply {
                        error.isVisible = true
                        loading.isVisible = false
                        tryAgain.setOnClickListener {
                            finish()
                            overridePendingTransition(0, 0)
                            startActivity(intent)
                            overridePendingTransition(0, 0)
                        }
                    }
                }
                is ApiResponse.Empty -> Unit
            }
        }
    }

    private fun setFavorite(isFavorite: Boolean) {
        if (isFavorite) {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_red
                )
            )
        } else {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_border
                )
            )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    companion object {
        const val POKEMON = "pokemon"
    }
}