import java.awt.GraphicsEnvironment
import java.awt.Rectangle

fun <T> List<T>.getNextInList(): Pair<T?, List<T>> {
    return if (this.isEmpty()) {
        Pair(null, this)
    } else {
        val first = this.first()
        Pair(first, this.drop(1) + first)
    }
}

enum class PetState() {
    IDLE,
    SLEEPING,
    MOVING
    //EATING
}

enum class Options() {
    PET,
    TALK,
    FEED,
    PLAY,
    SIT
}

enum class Orientation {
    LEFT,
    RIGHT
}

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