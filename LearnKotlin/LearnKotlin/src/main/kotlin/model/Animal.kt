package model

open class Animal() {
    init {
        println("Primary constructor")
    }
    open fun greed(){
        println("Animal")
    }
    fun describe(){
        println("Describe!!")
    }

    constructor(name: String, age: Int) : this() {
        println("name: $name, age: $age")
    }

}