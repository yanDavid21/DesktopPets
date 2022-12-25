package model

import Location
import Options
import PetState
import java.awt.GraphicsEnvironment
import java.awt.Rectangle
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

    fun nextQuote(): String?

    fun getUpdatedLocation(moveSpeed: Int): Location

    fun getNextState(): PetState
}

