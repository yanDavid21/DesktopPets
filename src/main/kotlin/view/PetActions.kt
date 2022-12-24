package view

import PetState

interface PetActions: ViewObject {

    fun updateAnimation(newState: PetState)

    fun sayQuote(quote: String)
}