package model

import utils.Location
import utils.Options
import utils.Orientation
import java.net.URL

interface BaseObjectViewModel {
    val name: String
    val options: List<Options>
    val currentLocation: Location
    val orientation: Orientation

    fun getSprite(): URL

}