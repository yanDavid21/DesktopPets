package utils

fun <T> List<T>.getNextInList(): Pair<T?, List<T>> {
    return if (this.isEmpty()) {
        Pair(null, this)
    } else {
        val first = this.first()
        Pair(first, this.drop(1) + first)
    }
}
