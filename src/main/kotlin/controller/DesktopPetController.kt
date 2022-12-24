package controller

import model.BasePet
import model.PetModel
import view.PetActions
import view.PetView
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class DesktopPetController(petModels: List<BasePet>) {
    private val pets: Map<String, Pair<PetModel, PetActions>> = petModels.associate { Pair(it.name, Pair(it, PetView(it)))
    }

    init {

    }

    fun start() {
        pets.values.forEach { (petModel, petAction) ->

            val (state, duration) = petModel.generateNextStateWithDuration()
            Executors.newScheduledThreadPool(1).schedule({
                petModel.state = PetState.IDLE
                petAction.display(true)
            }, 10000L, TimeUnit.MILLISECONDS)
        }
    }
}