package model

// add methods to an existing class
fun Bicycle.hasBasket(hasBasket: Boolean){
    if (hasBasket){
        println("$name has Basket")
    } else {
        println("$name has not Basket")
    }
}