package com.kiki.core.data

import com.kiki.core.data.source.local.LocalDataSource
import com.kiki.core.data.source.remote.RemoteDataSource
import com.kiki.core.data.source.remote.network.ApiResponse
import com.kiki.core.data.source.remote.response.PokemonDetailResponse
import com.kiki.core.data.source.remote.response.PokemonResponse
import com.kiki.core.domain.model.Pokemon
import com.kiki.core.domain.repository.PokemonRepositoryImpl
import com.kiki.core.utils.DataMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val coroutinesScope: CoroutineScope
) : PokemonRepositoryImpl {
    override fun getAllPokemon(): Flow<Resource<List<Pokemon>>> {
        return object :
            NetworkBoundResource<List<Pokemon>, List<PokemonResponse>>(coroutinesScope) {

            override suspend fun saveCallResult(data: List<PokemonResponse>) {
                return localDataSource.insertPokemon(DataMapper.mapResponseToEntities(data))
            }

            override fun shouldFetch(data: List<Pokemon>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): Flow<List<Pokemon>> {
                return localDataSource.getAllPokemon().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<PokemonResponse>>> {
                return remoteDataSource.getAllPokemon()
            }
        }.asFlow()
    }

    override fun getDetailPokemon(name: String): Flow<ApiResponse<PokemonDetailResponse>> {
        return remoteDataSource.getDetailPokemon(name)
    }

    override fun getFavoritePokemon(): Flow<List<Pokemon>> {
        return localDataSource.getFavoritePokemon().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoritePokemon(pokemon: Pokemon, favorite: Boolean) {
        coroutinesScope.launch {
            localDataSource.setFavoritePokemon(DataMapper.mapDomainToEntity(pokemon), favorite)
        }
    }
}