package com.kiki.pokemon.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kiki.core.data.source.remote.network.ApiResponse
import com.kiki.core.data.source.remote.response.PokemonDetailResponse
import com.kiki.core.domain.model.Pokemon
import com.kiki.core.domain.usecase.PokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private var useCase: PokemonUseCase
) : ViewModel() {

    fun getPokemonDetail(name: String): LiveData<ApiResponse<PokemonDetailResponse>> {
        return useCase.getDetailPokemon(name).asLiveData()
    }

    fun setFavoritePokemon(pokemon: Pokemon, isFavorite: Boolean) {
        useCase.setFavoritePokemon(pokemon, isFavorite)
    }
}