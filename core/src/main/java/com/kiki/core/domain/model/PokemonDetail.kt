package com.kiki.core.domain.model

import com.kiki.core.data.source.remote.response.Abilities
import com.kiki.core.data.source.remote.response.Sprites
import com.kiki.core.data.source.remote.response.Stats

data class PokemonDetail(
    val abilities: List<Abilities>,
    val baseExperience: Int,
    val height: Int,
    val id: Int,
    val sprites: Sprites,
    val stats: List<Stats>,
    val weight: Int
)

data class Abilities(val ability: Ability)

data class Ability(val name: String)

data class Stats(val baseStat: Int, val stat: Stat)

data class Stat(val name: String)

data class Sprites(val other: Other)

data class Other(val officialArtwork: OfficialArtwork)

data class OfficialArtwork(var frontDefault: String)