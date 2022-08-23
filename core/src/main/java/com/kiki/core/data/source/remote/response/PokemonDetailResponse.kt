package com.kiki.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PokemonDetailResponse(

    @SerializedName("abilities") val abilities: List<Abilities>? = null,
    @SerializedName("base_experience") val baseExperience: Int? = null,
    @SerializedName("height") val height: Int? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("sprites") val sprites: Sprites? = null,
    @SerializedName("stats") val stats: List<Stats>? = null,
    @SerializedName("weight") val weight: Int? = null
)

data class Abilities(@SerializedName("ability") val ability: Ability)

data class Ability(@SerializedName("name") val name: String)

data class Stats(
    @SerializedName("base_stat") val baseStat: Int,
    @SerializedName("stat") val stat: Stat
)

data class Stat(@SerializedName("name") val name: String)

data class Sprites(@SerializedName("other") val other: Other)

data class Other(@SerializedName("official-artwork") val officialArtwork: OfficialArtwork)

data class OfficialArtwork(@SerializedName("front_default") var frontDefault: String)