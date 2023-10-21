package model

class Bicycle(name: String, color: String, var year: Int): Vehicle(name, color) {
    override fun toString(): String {
        return "${super.toString()}, year: $year"
    }
}