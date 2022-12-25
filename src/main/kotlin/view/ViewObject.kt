package view

import Location

interface ViewObject {

    fun display(shouldDisplay: Boolean)

    fun renderSprite()

    fun renderLocation()
}