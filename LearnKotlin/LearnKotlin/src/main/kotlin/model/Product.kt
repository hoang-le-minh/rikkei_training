package model

import kotlin.properties.Delegates

class Product(val map: Map<String, Any>) {
    val name: String by map
    val year: Int by map
    override fun toString(): String {
        return "name: $name, year: $year"
    }

    // observable
    var infoProduct: String by Delegates.observable("Initial value"){
        property, oldValue, newValue ->
        println("${property.name}: $oldValue -> $newValue")
    }
    // vetoable
    var limit: Int by Delegates.vetoable(20){
        property, oldValue, newValue ->
        println("${property.name}: $oldValue -> $newValue")
        newValue > 10// validation
    }
}