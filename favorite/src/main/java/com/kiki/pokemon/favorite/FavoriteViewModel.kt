package com.kiki.pokemon.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kiki.core.domain.usecase.PokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(useCase: PokemonUseCase) : ViewModel() {
    val favoritePokemon = useCase.getFavoritePokemon().asLiveData()
}