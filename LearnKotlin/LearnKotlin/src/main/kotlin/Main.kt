import javax.annotation.processing.Completion

fun main(args: Array<String>) {
    println("Hello World!")

    println("========= variable ============")
    var x = 10 // this is a variable
    // string template
    println("x = $x")
    val a: Int = 125
    println("x = $x, a = $a")
    x = 15
    println("now, x = $x")

    printHello("Hoang")

    println("sum 2 and 3 is: ${sum(2,3)}")

    // function with variadic arguments
    println("========= vararg function =============")
    listSports("Football")
    listSports("Volleyball", "Basketball")
    listSports("Football", "Volleyball", "Basketball")

    // infix function
    println("======= infix function ============")
    val z = 2 plus 3
    println("z = $z") // z = 5
    println("2 * 3 = ${2 times 3}") // 2 * 3 = 6
    println("Hoang" play "Football") // Hoang plays Football

    // nullable
    println("=====nullable=========")
    val message: String? = null
    println("message is $message")
    println("message is ${message ?: "default message"}")
    println("message's length is ${message?.length ?: 0}")

    printSum(4, 5){ result ->
        run {
            println("result = $result")
        }
    }   // Print Sum
        // result = 9

    println("Area = ${area(3)(5)}") // 15

    println(getFullName("Hoang", "Le"))
    println("url = ${url(2, 3)}")

    val map: Map<String, Int> = hashMapOf("Hoang" to 1, "Nam" to 2)
    map["key"]?.let {
        // Do something if map["key"] not null
        println("Value = $it")
    }

    val user = hashMapOf("name" to "Hoang", "gender" to "male")
    user.apply {
        this["name"] = "Quynh"
        this["gender"] = "female"
    }
    println("name: ${user["name"]}; gender: ${user["gender"]}")


}