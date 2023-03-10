package model

import utils.Emotion
import utils.Orientation
import java.net.URL
import utils.PetState


class Trekker() : BasePet("Trekker") {
    override var quotes: List<String> = super.quotes + listOf("trekker specific")
    override var emotion: Emotion? = Emotion.HUNGRY

    override fun getSprite(): URL {
        return if (orientation == Orientation.LEFT) {
            when (state) {
                PetState.IDLE -> javaClass.getResource("/trekker/idle_left.gif")!!
                PetState.MOVING -> javaClass.getResource("/trekker/walking_left.gif")!!
                PetState.SLEEPING -> javaClass.getResource("/trekker/sleepy_left.gif")!!
            }
        } else {
            when (state) {
                PetState.IDLE -> javaClass.getResource("/trekker/idle_right.gif")!!
                PetState.MOVING -> javaClass.getResource("/trekker/walking_right.gif")!!
                PetState.SLEEPING -> javaClass.getResource("/trekker/sleepy_right.gif")!!
            }
        }
    }

    override fun getEmote(): URL? {
        return when (emotion) {
            Emotion.PLAYFUL -> javaClass.getResource("/emotes/teddy.png")
            else -> super.getEmote()
        }
    }
}