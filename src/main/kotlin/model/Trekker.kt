package model

import java.net.URL


class Trekker() : BasePet("Trekker") {
    override var quotes: List<String> = super.quotes + listOf("trekker specific")

    override fun getSprite(): URL {
        return when (state) {
            PetState.IDLE -> javaClass.getResource("/trekker/idle_right.gif")!!
            PetState.MOVING -> javaClass.getResource("/trekker/walking_right.gif")!!
            PetState.SLEEPING -> javaClass.getResource("/trekker/sleepy.gif")!!
            PetState.EATING -> javaClass.getResource("/trekker/walking_right.gif")!!
        }
    }
}