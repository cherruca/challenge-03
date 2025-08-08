package com.example.pokedex.data

import com.squareup.moshi.Json

// TODO: It's hard to read the models in the same file.
//  Organize better your code/files
// TODO: use nulable fields
data class PokemonDetailResponse(
    val id: Int,
    val name: String,
    val height: Double,
    val weight: Double,
    val sprites: Sprite,
    val stats: List<Stat>,
    val types: List<Type>,
    val cries: Cry
)

//TODO: use nulable fields
data class Sprite(
    @Json(name = "back_default")
    val backDefault: String,
    @Json(name = "back_female")
    val backFemale: Any?, //TODO: ANY?
    @Json(name = "back_shiny")
    val backShiny: String,
    @Json(name = "back_shiny_female")
    // TODO: i don't get why are you using "Any?" here
    //  You sure this is the right way?
    val backShinyFemale: Any?,
    @Json(name = "front_default")
    val frontDefault: String,
    @Json(name = "front_female")
    // TODO: i don't get why are you using "Any?" here
    //  You sure this is the right way?
    val frontFemale: Any?,
    @Json(name = "front_shiny")
    val frontShiny: String,
    @Json(name = "front_shiny_female")
    // TODO: i don't get why are you using "Any?" here
    //  You sure this is the right way?
    val frontShinyFemale: Any?
)

data class Stat(
    @Json(name = "base_stat")
    val baseStat: Long,
    val effort: Long,
    val stat: StatDetail
)

data class StatDetail(
    val name: String,
    val url: String
)

data class Type(
    val slot: Long,
    val type: TypeDetail
)

data class TypeDetail(
    val name: String,
    val url: String
)

data class Cry(
    val latest: String,
    val legacy: String
)
