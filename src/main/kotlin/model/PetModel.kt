package model

import utils.*
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
    var emotion: Emotion?

    fun nextQuote(): String?

    fun getUpdatedLocation(moveSpeed: Int): Location

    fun getNextState(): PetState

    fun getNextEmotion(): Emotion
}

interface PetModelViewModel: BaseObjectViewModel {
    val emotion: Emotion?
    fun getEmote(): URL?
}

