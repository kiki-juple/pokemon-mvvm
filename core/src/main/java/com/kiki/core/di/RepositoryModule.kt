package com.kiki.core.di

import com.kiki.core.data.PokemonRepository
import com.kiki.core.domain.repository.PokemonRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(repository: PokemonRepository): PokemonRepositoryImpl
}