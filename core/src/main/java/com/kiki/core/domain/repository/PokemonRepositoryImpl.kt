package com.kiki.core.domain.repository

import com.kiki.core.data.Resource
import com.kiki.core.data.source.remote.network.ApiResponse
import com.kiki.core.data.source.remote.response.PokemonDetailResponse
import com.kiki.core.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepositoryImpl {
    fun getAllPokemon(): Flow<Resource<List<Pokemon>>>
    fun getDetailPokemon(name: String): Flow<ApiResponse<PokemonDetailResponse>>
    fun getFavoritePokemon(): Flow<List<Pokemon>>
    fun setFavoritePokemon(pokemon: Pokemon, favorite: Boolean)
}