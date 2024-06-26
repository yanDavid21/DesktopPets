package model.pets

import utils.*
import utils.Location.Companion.getRandomTaskbarLocation
import utils.Location.Companion.getStepLocation
import java.net.URL
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter.ofLocalizedDateTime
import java.time.format.FormatStyle

abstract class BasePet(override val name: String): PetModel, PetModelViewModel {
    override var currentLocation = Location.bottomCenterOfScreen()
    override var destination = Location.bottomCenterOfScreen()
    override var state = PetState.SLEEPING
    override var quotes = getStandardQuotes()
    override val toys = getStandardToys()
    override val food = getStandardFoods()
    override val options = getStandardOptions()
    override var orientation = Orientation.LEFT
    override var emotion: Emotion? = null

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
        if (nextState == PetState.MOVING) {
            destination = getRandomTaskbarLocation()
        }
        return nextState
    }

    override fun getNextEmotion(): Emotion {
        return (Emotion.values().toSet() - setOf(Emotion.SLEEPY)).random()
    }

    override fun getEmote(): URL? {
        return when (emotion) {
            Emotion.CATFACE -> javaClass.getResource("/emotes/catface.png")
            Emotion.LOVED -> javaClass.getResource("/emotes/heart.png")
            Emotion.SLEEPY -> javaClass.getResource("/emotes/sleeping.png")
            Emotion.HUNGRY -> javaClass.getResource("/emotes/beef.png")
            Emotion.PLAYFUL -> javaClass.getResource("/emotes/game.png")
            Emotion.HAPPY -> javaClass.getResource("/emotes/happy.png")
            Emotion.HAPPY2 -> javaClass.getResource("/emotes/happy_2.png")
            null -> null
        }
    }

    protected fun getSpriteByFolderName(folderName: String): URL {
        return if (orientation == Orientation.LEFT) {
            when (state) {
                PetState.IDLE -> javaClass.getResource("/$folderName/idle_left.gif")!!
                PetState.MOVING -> javaClass.getResource("/$folderName/walking_left.gif")!!
                PetState.SLEEPING -> javaClass.getResource("/$folderName/sleepy_left.gif")!!
            }
        } else {
            when (state) {
                PetState.IDLE -> javaClass.getResource("/$folderName/idle_right.gif")!!
                PetState.MOVING -> javaClass.getResource("/$folderName/walking_right.gif")!!
                PetState.SLEEPING -> javaClass.getResource("/$folderName/sleepy_right.gif")!!
            }
        }
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