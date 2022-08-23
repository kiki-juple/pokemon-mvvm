package com.kiki.core.data.source.remote

import android.util.Log
import com.kiki.core.data.source.remote.network.ApiResponse
import com.kiki.core.data.source.remote.network.ApiService
import com.kiki.core.data.source.remote.response.PokemonDetailResponse
import com.kiki.core.data.source.remote.response.PokemonResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    private val coroutineDispatcher: CoroutineDispatcher
) {

    fun getAllPokemon(): Flow<ApiResponse<List<PokemonResponse>>> {
        return flow {
            try {
                val response = apiService.getListPokemon()
                val result = response.results
                if (result.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("getAllPokemon", e.toString())
            }
        }.flowOn(coroutineDispatcher)
    }

    fun getDetailPokemon(name: String): Flow<ApiResponse<PokemonDetailResponse>> {
        return flow {
            try {
                val response = apiService.getPokemonDetails(name)
                if (response.id != null) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("getDetailPokemon", e.toString())
            }
        }.flowOn(coroutineDispatcher)
    }
}