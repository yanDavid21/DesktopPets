package model

import java.net.URL


class Trekker() : BasePet("Trekker") {
    override var quotes: List<String> = super.quotes + listOf("trekker specific")

    override fun getSprite(): URL {
        return when (state) {
            PetState.IDLE -> javaClass.getResource("/trekker/idle_left.gif")!!
            PetState.MOVING_LEFT -> javaClass.getResource("/trekker/walking_left.gif")!!
            PetState.MOVING_RIGHT -> javaClass.getResource("/trekker/walking_right.gif")!!
            PetState.SLEEPING -> javaClass.getResource("/trekker/sleepy.gif")!!
        }
    }
}