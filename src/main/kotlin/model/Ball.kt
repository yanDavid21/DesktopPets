package model

import utils.Location
import utils.Options
import utils.Orientation
import java.net.URL

class Ball: KinematicsModel() {
    override val name = "Ball"
    override val options = listOf(Options.REMOVE)
    override val orientation = Orientation.LEFT

    override fun getSprite(): URL {
        return javaClass.getResource("/ball.png")!!
    }

}