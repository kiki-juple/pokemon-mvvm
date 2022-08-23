package com.kiki.core.domain.usecase

import com.kiki.core.domain.model.Pokemon
import com.kiki.core.domain.repository.PokemonRepositoryImpl
import javax.inject.Inject

class PokemonInteractor @Inject constructor(private val pokemonRepository: PokemonRepositoryImpl) :
    PokemonUseCase {
    override fun getAllPokemon() = pokemonRepository.getAllPokemon()

    override fun getFavoritePokemon() = pokemonRepository.getFavoritePokemon()

    override fun setFavoritePokemon(pokemon: Pokemon, favorite: Boolean) {
        pokemonRepository.setFavoritePokemon(pokemon, favorite)
    }

    override fun getDetailPokemon(name: String) = pokemonRepository.getDetailPokemon(name)
}