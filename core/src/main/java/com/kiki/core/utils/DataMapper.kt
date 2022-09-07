package com.kiki.core.utils

import com.kiki.core.data.source.local.entity.PokemonEntity
import com.kiki.core.data.source.remote.response.PokemonResponse
import com.kiki.core.domain.model.Pokemon

object DataMapper {

    fun mapResponseToEntities(input: List<PokemonResponse>): List<PokemonEntity> =
        input.map { PokemonEntity(null, it.name) }

    fun mapEntitiesToDomain(input: List<PokemonEntity>): List<Pokemon> =
        input.map { Pokemon(it.id, it.name, it.isFavorite) }

    fun mapDomainToEntity(input: Pokemon): PokemonEntity =
        PokemonEntity(input.id, input.name, input.isFavorite)
}