package model

import Location
import Location.Companion.getRandomTaskbarLocation
import Location.Companion.getStepLocation
import Location.Companion.screenSize
import Options
import PetState
import getNextInList
import java.net.URL
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter.ofLocalizedDateTime
import java.time.format.FormatStyle

abstract class BasePet(override val name: String): PetModel, BaseObjectViewModel {
    override var currentLocation = Location.bottomCenterOfScreen()
    override var destination = Location.bottomCenterOfScreen()
    override var state = PetState.SLEEPING
    override var quotes = getStandardQuotes()
    override val toys = getStandardToys()
    override val food = getStandardFoods()
    override val options = getStandardOptions()

    override fun nextQuote(): String? {
        val (first, newList) = quotes.getNextInList()
        quotes = newList
        println(quotes)
        return first
    }

    override fun getUpdatedLocation(moveSpeed: Int): Location {
        if (destination == currentLocation) {
            return currentLocation
        }
        return getStepLocation(currentLocation, destination, moveSpeed)
    }

    override fun getNextState(): PetState {
        val nextPossibleStates = PetState.values().toSet() - setOf(state)
        val nextState = nextPossibleStates.random()
        if (nextState == PetState.MOVING_LEFT) {
            destination = getRandomTaskbarLocation(0, currentLocation.xCoordinate.toInt())
        } else if (nextState == PetState.MOVING_RIGHT) {
            destination = getRandomTaskbarLocation(currentLocation.xCoordinate.toInt(), screenSize.width)
        }
        return nextState
    }


    private fun getStandardQuotes(): List<String> {
        val currentTime = ZonedDateTime.now(ZoneId.systemDefault())
        return listOf(
            "Hi, my name is $name!",
            "It is ${currentTime.format(ofLocalizedDateTime(FormatStyle.MEDIUM))} right now.",
            "Whatever you're working on, you got this!",
            "Remember, always practice your civic duties and to not blindly trust any governme" +
                    "nt!")
    }

    private fun getStandardFoods(): List<URL> {
        return listOf()
    }

    private fun getStandardToys(): List<URL> {
        return listOf()
    }

    private fun getStandardOptions(): List<Options> {
        return listOf(Options.PET, Options.FEED, Options.PLAY, Options.TALK, Options.SIT)
    }
}