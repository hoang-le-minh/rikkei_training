package model

class Car(name:String, color: String, var engineSize: Float): Vehicle(name, color) {
    override fun toString(): String {
        return "${super.toString()}, engineSize: $engineSize"
    }
}