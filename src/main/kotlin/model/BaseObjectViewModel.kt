package model

import Location
import Options
import Orientation
import java.net.URL

interface BaseObjectViewModel {
    val name: String
    val options: List<Options>
    val currentLocation: Location
    val orientation: Orientation

    fun getSprite(): URL

}