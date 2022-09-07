package com.kiki.pokemon.favorite.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kiki.core.domain.usecase.PokemonUseCase
import com.kiki.pokemon.favorite.ui.FavoriteViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(private val useCase: PokemonUseCase) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            FavoriteViewModel(useCase) as T
        } else {
            throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}