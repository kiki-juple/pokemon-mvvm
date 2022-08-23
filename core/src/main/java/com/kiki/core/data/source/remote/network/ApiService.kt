package com.kiki.core.data.source.remote.network

import com.kiki.core.data.source.remote.response.ListPokemonResponse
import com.kiki.core.data.source.remote.response.PokemonDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("pokemon")
    suspend fun getListPokemon(): ListPokemonResponse

    @GET("pokemon/{name}")
    suspend fun getPokemonDetails(
        @Path("name") name: String
    ): PokemonDetailResponse
}