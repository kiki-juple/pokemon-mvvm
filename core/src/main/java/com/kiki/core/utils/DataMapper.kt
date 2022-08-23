package com.kiki.core.utils

import com.kiki.core.data.source.local.entity.PokemonEntity
import com.kiki.core.data.source.remote.response.PokemonResponse
import com.kiki.core.domain.model.Pokemon

object DataMapper {

    fun mapResponseToEntities(input: List<PokemonResponse>): List<PokemonEntity> =
        input.map { PokemonEntity(null, it.name) }

    fun mapEntitiesToDomain(input: List<PokemonEntity>): List<Pokemon> =
        input.map { Pokemon(it.name) }

    fun mapDomainToEntity(input: Pokemon): PokemonEntity =
        PokemonEntity(null, input.name)

//    fun mapResponseToDomain(input: PokemonDetailResponse): PokemonDetail {
//        return PokemonDetail(
//            input.abilities,
//            input.baseExperience,
//            input.height,
//            input.id,
//            input.sprites,
//            input.stats,
//            input.weight
//        )
//    }
}