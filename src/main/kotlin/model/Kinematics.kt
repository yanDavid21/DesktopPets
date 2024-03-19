package model

import utils.Location
import kotlin.math.abs

abstract class KinematicsModel: BaseObjectModel {
    var xSpeed: Double = Math.random() * 20 - 10
    var ySpeed: Double = 0.0
    override var currentLocation: Location = Location.getRandomLocationOnScreen()

    companion object {
        val GRAVITY_VEL = 5.5
    }

    fun getNextLocation () {
        val shouldBounce = ySpeed > 10
        val isBallInAir = this.currentLocation.yCoordinate.toInt() < Location.screenSize.height;
        val initialSpeed = ySpeed


        if (isBallInAir) {
            ySpeed += GRAVITY_VEL
        } else if (shouldBounce){
            xSpeed += if (xSpeed > 0.5) -0.05 else if (xSpeed < -0.5) 0.05 else xSpeed * -1
            ySpeed = (initialSpeed / -2)
        } else {
            xSpeed +=  if (xSpeed > 0.5) -0.15 else if (xSpeed < -0.5) 0.15 else xSpeed * -1
            ySpeed = 0.0
        }
        val newYLocation = (this.currentLocation.yCoordinate + ySpeed).coerceAtMost(Location.screenSize.height.toDouble())
        val newXLocation = (this.currentLocation.xCoordinate + xSpeed).coerceAtMost(Location.screenSize.width.toDouble()).coerceAtLeast(0.0)
        this.currentLocation = Location(newXLocation, newYLocation)

        if (!isBallInAir) {
            ySpeed = if (shouldBounce) (initialSpeed / -2) else 0.0
        }
    }
}