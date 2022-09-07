package com.kiki.pokemon.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kiki.core.domain.usecase.PokemonUseCase
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val useCase: PokemonUseCase) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(Class.forName("com.kiki.pokemon.favorite.FavoriteViewModel")) -> {
                modelClass(tourismUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}