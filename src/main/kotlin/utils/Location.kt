package utils

import java.awt.GraphicsEnvironment
import java.awt.Rectangle

data class Location(val xCoordinate: Double, val yCoordinate: Double) {
    init {
        if (isOutOfBounds()) {
            throw IllegalArgumentException("Out of bounds. Given $xCoordinate and $yCoordinate")
        }
    }

    private fun isOutOfBounds(): Boolean {
        return !(xCoordinate in 0.0..screenSize.width.toDouble() || yCoordinate in 0.0..screenSize.height.toDouble())
    }

    companion object {
        val screenSize: Rectangle = GraphicsEnvironment.getLocalGraphicsEnvironment().maximumWindowBounds

        fun bottomCenterOfScreen(): Location {
            return Location(screenSize.getWidth() / 2, screenSize.getHeight())
        }

        fun getStepLocation(source: Location, dest: Location, stepDistance:Int): Location {
            val (xCoordinate1, yCoordinate1) = source
            val (xCoordinate2, yCoordinate2) = dest
            val newX = getStepDistance(xCoordinate1, xCoordinate2, stepDistance)
            val newY = getStepDistance(yCoordinate1, yCoordinate2, stepDistance)
            return Location(newX, newY)
        }

        fun getRandomTaskbarLocation(lowerBoundWidth: Int = 0, upperBoundWidth:Int = screenSize.width): Location {
            val range = upperBoundWidth - lowerBoundWidth
            return Location(Math.random() * range + lowerBoundWidth, screenSize.getHeight())
        }

        fun getRandomLocationOnScreen(): Location {
            val xCoord = (Math.random() * screenSize.width - 100) + 100
            val yCoord = (Math.random() * screenSize.height - 500) + 500 // padding
            return Location(xCoord, yCoord);
        }

        fun getOrientationFromLocations(source: Location, dest: Location): Orientation {
            val (xCoordinate1, _) = source
            val (xCoordinate2, _) = dest
            if (xCoordinate1 > xCoordinate2) {
                return Orientation.LEFT
            } else {
                return Orientation.RIGHT
            }
        }

        private fun getStepDistance(coordinate1: Double, coordinate2: Double, stepDistance:Int): Double {
            return if (coordinate1 == coordinate2) {
                coordinate1
            } else if (coordinate1 < coordinate2 ) {
                (coordinate1 + stepDistance).coerceAtMost(coordinate2)
            } else {
                (coordinate1 - stepDistance).coerceAtLeast(coordinate2)
            }
        }
    }
}