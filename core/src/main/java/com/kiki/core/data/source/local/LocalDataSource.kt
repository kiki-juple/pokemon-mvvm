package com.kiki.core.data.source.local

import com.kiki.core.data.source.local.entity.PokemonEntity
import com.kiki.core.data.source.local.room.PokemonDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val pokemonDao: PokemonDao) {

    fun getAllPokemon(): Flow<List<PokemonEntity>> = pokemonDao.getAllPokemon()

    fun getFavoritePokemon(): Flow<List<PokemonEntity>> = pokemonDao.getFavoritePokemon()

    suspend fun insertPokemon(pokemonList: List<PokemonEntity>) =
        pokemonDao.insertPokemon(pokemonList)

    fun setFavoritePokemon(pokemon: PokemonEntity, favorite: Boolean) {
        pokemon.isFavorite = favorite
        pokemonDao.updateFavoritePokemon(pokemon)
    }
}