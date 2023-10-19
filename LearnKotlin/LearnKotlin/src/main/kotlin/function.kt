
    // function
    fun printHello(name: String): Unit{
        println("Hello $name!!!")
    }

    fun sum(y: Int, z: Int): Int{
        return y + z
    }

    // function with variadic arguments
    fun listSports(vararg sports: String){
        for (sport in sports){
            println("I like playing $sport")
        }
    }

    // infix function
    infix fun Int.plus(x: Int): Int{
        return this + x
    }
    // one-line infix function
    infix fun Int.times(x: Int): Int = this * x
    infix fun String.play(sport: String) = "$this plays $sport"

    // Higher order function
    fun printSum(x: Int, y: Int, completion: (Int) -> Unit) {
        println("Print Sum: ")
        val result = x + y
        completion(result)
    }

    fun area(x: Int): (y: Int) -> Int {
        return fun (y): Int {
            return x * y
        }
    }

    // lambda function
    val getFullName: (String, String) -> String = {
        firstName: String, lastName: String -> run {
            println("Lambda function")
            "$firstName $lastName"
        }
    }
    val url: (Int, Int) -> String = {
        page: Int, limit: Int -> "https://yourServerName:port/users?page=$page&limit=$limit"
    }

