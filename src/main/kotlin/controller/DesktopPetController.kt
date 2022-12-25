package controller

import model.BasePet
import model.PetModel
import utils.Emotion
import view.PetActions
import view.PetView
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import utils.Location.Companion.getOrientationFromLocations
import utils.PetState
import java.util.Timer

const val MOVE_SPEED_PX_PER_30_MS = 1
const val TICK_DURATION_IN_MS = 30L
const val STATE_PERIOD_IN_MS = 10000L
const val EMOTION_PERIOD_IN_MS = 25000L

class DesktopPetController(petModels: List<BasePet>) {
    private val pets: Map<String, Pair<PetModel, PetActions>> = petModels.associate { basePet ->
        Pair(basePet.name, Pair(basePet, PetView(basePet))) }

    fun start() {
        pets.values.forEach { (petModel, petView) ->
            petView.display(true)
            updateLocationOnTickWhenMoving(petModel, petView)
            updateStatePeriodically(petModel, petView)
            updateEmotePeriodically(petModel, petView)
        }
    }

    private fun updateLocationOnTickWhenMoving(petModel: PetModel, petView: PetActions) {
        val updateLocationRunnable = { if (petModel.state == PetState.MOVING ) updateSpriteLocation(petModel, petView) }
        scheduleTaskPeriodically(updateLocationRunnable, TICK_DURATION_IN_MS)
    }

    private fun updateStatePeriodically(petModel: PetModel, petView: PetActions) {
        val updateStateRunnable = {
            petModel.state = petModel.getNextState()
            if (petModel.state == PetState.MOVING) {
                petModel.orientation = getOrientationFromLocations(petModel.currentLocation, petModel.destination)
            }
            petModel.emotion = if (petModel.state == PetState.SLEEPING) Emotion.SLEEPY else null
            petView.renderSpriteWithEmote()
        }
        scheduleTaskPeriodically(updateStateRunnable, STATE_PERIOD_IN_MS, 10000)
    }

    private fun updateEmotePeriodically(petModel: PetModel, petView: PetActions) {
        val updateEmoteRunnable = {
            petModel.emotion ?: run {
                petModel.emotion = petModel.getNextEmotion()
                petView.renderSpriteWithEmote()
            }
            Unit
        }
        scheduleTaskPeriodically(updateEmoteRunnable, EMOTION_PERIOD_IN_MS, 15000)
    }

    private fun scheduleTaskPeriodically(runnable: Runnable, periodDurationInMS: Long, delayInMS: Long = 0L) {
        val execService = Executors.newScheduledThreadPool(1)
        execService.scheduleAtFixedRate(runnable, delayInMS, periodDurationInMS, TimeUnit.MILLISECONDS)
    }

    private fun updateSpriteLocation(petModel: PetModel, petView: PetActions) {
        val newLocation = petModel.getUpdatedLocation(MOVE_SPEED_PX_PER_30_MS)
        if (newLocation != petModel.currentLocation) {
            petModel.currentLocation = newLocation
            petView.renderLocationWithEmote()
        } else {
            petModel.state = PetState.IDLE
            petView.renderSpriteWithEmote()
        }
    }
}