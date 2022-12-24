package view

import Location

interface ViewObject {
    fun updateLocation()

    fun display(shouldDisplay: Boolean)
}