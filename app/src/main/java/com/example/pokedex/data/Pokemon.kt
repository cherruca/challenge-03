package com.example.pokedex.data

import com.example.pokedex.data.list.ListItem

// TODO: Please organize your classes better, use separate files
data class Pokemon(
    val name: String,
    val url: String
)

// TODO: is it really possible to have nullable images based on the API response?
data class PokemonUI(
    val name: String,
    val url: String,
    val isFavorite: Boolean,
    val image: String?
): ListItem {
    // todo fun getImage() = "https:/.../$name.jpeg"
    // TODO: why are you calling this function  in this way? you can simple move this logic to the adapter inside the correct override of the getItemviewType
    override fun getListItemType(): Int =
        // TODO: nope, those magic numbers are not readable at all.
        //  Please use an interface here.
        if (isFavorite) {
            // todo: use the ordinal from the interface
            // TODO: change this num to constant inside a enum class and us a descriptive name-> Ex: IS_FAVORITE = 1,
            1
        } else {
            2
        }
}
