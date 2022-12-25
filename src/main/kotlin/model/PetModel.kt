package model

import utils.Location
import utils.Options
import utils.Orientation
import utils.PetState
import java.net.URL

interface PetModel {
    var currentLocation : Location
    var destination: Location
    var state: PetState
    var quotes: List<String>
    val name: String
    val toys: List<URL>
    val food: List<URL>
    val options: List<Options>
    var orientation: Orientation

    fun nextQuote(): String?

    fun getUpdatedLocation(moveSpeed: Int): Location

    fun getNextState(): PetState
}

