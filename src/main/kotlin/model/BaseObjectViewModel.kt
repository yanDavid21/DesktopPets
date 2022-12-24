package model

import Location
import Options
import java.net.URL

interface BaseObjectViewModel {
    val name: String
    val options: List<Options>
    val currentLocation: Location

    fun getSprite(): URL
}