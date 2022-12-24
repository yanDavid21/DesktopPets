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
    MOVING,
    EATING
}

enum class Options() {
    PET,
    TALK,
    FEED,
    PLAY
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
            return Location(Location.screenSize.getWidth() / 2, Location.screenSize.getHeight())
        }
    }
}