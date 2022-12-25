package view

import PetState

interface PetActions: ViewObject {

    fun sayQuote(quote: String)
}