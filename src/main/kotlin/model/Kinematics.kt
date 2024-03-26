package model

import utils.Location
import utils.getNextInList

abstract class KinematicsModel: BaseObjectModel {
    var xSpeed: Double = Math.random() * 20 - 10
    var ySpeed: Double = 0.0
    override var currentLocation: Location = Location.getRandomLocationOnScreen()
        set(location)  {
            field = location
            previousLocations.add(location)
            if (previousLocations.size > 5) {
                previousLocations.removeFirst()
            }
        }
    var isHeld = false;
    private val previousLocations = mutableListOf<Location>()

    companion object {
        val GRAVITY_VEL = 4
    }

    fun getNextLocation () {
        val ballYCoord = this.currentLocation.yCoordinate.toInt()
        val ballxCoord = this.currentLocation.xCoordinate.toInt()

        val initialYSpeed = ySpeed
        val initialXSpeed = xSpeed

        val isBallInAir = ballYCoord < Location.screenSize.height
        val shouldBounce = ySpeed > 10
        val isBallTouchingWall = ballxCoord == Location.screenSize.width || ballxCoord == 0

        if (xSpeed.equals(0.0) && ySpeed.equals(0.0) && !isBallInAir || isHeld) {
            return
        }

        if (isBallInAir) {
            ySpeed += GRAVITY_VEL
        } else {
            xSpeed +=  if (xSpeed > 0.5) -0.15 else if (xSpeed < -0.5) 0.15 else xSpeed * -1
            ySpeed = if (shouldBounce) (initialYSpeed * -.70) else 0.0
        }

        if (isBallTouchingWall) {
            xSpeed = initialXSpeed * - .70
        }

        val newYLocation = (this.currentLocation.yCoordinate + ySpeed).coerceAtMost(Location.screenSize.height.toDouble())
        val newXLocation = (this.currentLocation.xCoordinate + xSpeed).coerceAtMost(Location.screenSize.width.toDouble()).coerceAtLeast(0.0)
        this.currentLocation = Location(newXLocation, newYLocation)
    }

    fun calculateVelocity() {
        xSpeed= previousLocations.last().xCoordinate - previousLocations[0].xCoordinate
        ySpeed = previousLocations.last().yCoordinate - previousLocations[0].yCoordinate
    }
}