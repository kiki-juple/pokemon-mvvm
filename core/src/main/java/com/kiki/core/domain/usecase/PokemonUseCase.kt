package com.kiki.core.domain.usecase

import com.kiki.core.data.Resource
import com.kiki.core.data.source.remote.network.ApiResponse
import com.kiki.core.data.source.remote.response.PokemonDetailResponse
import com.kiki.core.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonUseCase {
    fun getAllPokemon(): Flow<Resource<List<Pokemon>>>
    fun getFavoritePokemon(): Flow<List<Pokemon>>
    fun setFavoritePokemon(pokemon: Pokemon, favorite: Boolean)
    fun getDetailPokemon(name: String): Flow<ApiResponse<PokemonDetailResponse>>
}