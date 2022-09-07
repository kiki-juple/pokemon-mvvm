package com.kiki.pokemon.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kiki.core.domain.usecase.PokemonUseCase

class FavoriteViewModel(useCase: PokemonUseCase) : ViewModel() {
    val favoritePokemon = useCase.getFavoritePokemon().asLiveData()
}