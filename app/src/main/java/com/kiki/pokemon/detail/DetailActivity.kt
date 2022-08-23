package com.kiki.pokemon.detail

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import coil.load
import com.kiki.core.data.source.remote.network.ApiResponse
import com.kiki.core.domain.model.Pokemon
import com.kiki.pokemon.R
import com.kiki.pokemon.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pokemonName = intent.getParcelableExtra<Pokemon>(POKEMON)
        pokemonName?.name?.let { showDetailPokemon(it) }
    }

    private fun showDetailPokemon(name: String) {
        viewModel.getPokemonDetail(name).observe(this@DetailActivity) { response ->
            when (response) {
                is ApiResponse.Success -> {
                    response.data.apply {
                        binding.apply {
                            pokemonImage.load(sprites?.other?.officialArtwork?.frontDefault)
                            pokemonName.text = name
                            tvExp.text = getString(R.string.exp, baseExperience)
                            tvHeight.text = getString(R.string.weight, weight)
                            tvWeight.text = getString(R.string.height, height)
                            abilities?.forEach {
                                val tv = TextView(this@DetailActivity)
                                tv.text = it.ability.name
                                layoutAbility.addView(tv)
                            }
                            stats?.forEach {
                                val tv = TextView(this@DetailActivity)
                                tv.text = getString(R.string.stat_item, it.stat.name, it.baseStat)
                                layoutStats.addView(tv)
                            }
                            loading.isVisible = false
                            error.isVisible = false
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

    companion object {
        const val POKEMON = "pokemon"
    }
}