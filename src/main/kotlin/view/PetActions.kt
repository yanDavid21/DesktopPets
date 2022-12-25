package view

import java.net.URL


interface PetActions: ViewObject {

    fun sayQuote(quote: String)

    fun renderSpriteWithEmote()

    fun renderLocationWithEmote()
}