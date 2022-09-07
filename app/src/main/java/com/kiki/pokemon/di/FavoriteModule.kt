package com.kiki.pokemon.di

import com.kiki.core.domain.usecase.PokemonUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModule {

    fun pokemonUseCase(): PokemonUseCase
}