package com.kiki.pokemon.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kiki.core.domain.usecase.PokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(useCase: PokemonUseCase) : ViewModel() {
    val pokemon = useCase.getAllPokemon().asLiveData()
}