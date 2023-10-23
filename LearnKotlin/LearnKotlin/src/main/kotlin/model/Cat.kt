package model

class Cat: Animal() {
    init {
        println("Cat primary constructor")
    }
    override fun greed() {
        println("Cat")
    }


}