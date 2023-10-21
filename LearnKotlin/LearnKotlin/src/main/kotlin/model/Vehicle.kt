package model

// like "abstract class": Cannot create object from it, Can be inherited
sealed class Vehicle(var name: String, var color: String) {
    override fun toString(): String = "name: $name, color: $color"
}