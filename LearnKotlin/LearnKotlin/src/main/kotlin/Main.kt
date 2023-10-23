import enum.Quality
import enum.RequestError
import model.*
import storage.MyDBRepository
import storage.MySQLRepository
import util.Calculation
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

    // let
    val map: Map<String, Int> = hashMapOf("Hoang" to 1, "Nam" to 2)
    map["key"]?.let {
        // Do something if map["key"] not null
        println("Value = $it")
    }

    // apply
    val user = hashMapOf("name" to "Hoang", "gender" to "male")
    user.apply {
        this["name"] = "Quynh"
        this["gender"] = "female"
    }
    println("name: ${user["name"]}; gender: ${user["gender"]}")

    // with
    val user1 = User(1, "Hoang", "hoang@gmail.com")
    with(user1){
        name = "Minh"
        email = "minh@gmail.com"
    }
    println(user1)

    print("Nhap n: ")
    val n: Int = readlnOrNull()?.toInt() ?: 0
    val ketqua = when(n){
        in 1..5 -> "1 <= n <= 5"
        6, 8, 10 -> "n = 6 or 8 or 10"
        else -> "Error"
    }
    println(ketqua)

    // enum classes
    val quality = Quality.GOOD
    val qualityResult: String = when(quality){
        Quality.BAD -> "Quality is Bad"
        Quality.GOOD -> "Quality is Good"
        Quality.NOMAL -> "Quality is Normal"
        Quality.EXCELLENT -> "Quality is Excellent"
    }
    println(qualityResult)

    val requestError = RequestError.BAD_REQUEST
    println(requestError)
    println(requestError.message)
    println(requestError.lengthMessage())

    // define key-value object
    val person = object {
        var name = "Hoang"
        var age = 22
        var gender = "male"

        override fun toString(): String {
            return "name: $name, age: $age, gender: $gender"
        }
    }
    println(person)

    // List
    val listSports = listOf<String>("Football", "Volleyball") // read only
    println(listSports)
    // MutableList
    val listNumber = mutableListOf<Int>(1, 2, 4, 5)
    listNumber.add(2, 3)
    listNumber.remove(2)
    listNumber.add(6)
    println(listNumber)
    listNumber.forEach{
        print("$it ")
    }
    if(listNumber.any{it < 3}){
        println("\nAt least one number is lower than 3")
    }
    if(listNumber.all { it < 10 }){
        println("All number of list is lower than 10")
    }

    // mutable map
    var person1 = mutableMapOf<String, Any>(
        "name" to "Hoang",
        "age" to 22,
        "email" to "hoang@gmail.com"
    )
    println(person1)
    person1["name"] = "minh"
    println(person1)

    // companion object <=> static
    println("PI = ${Calculation.PI}; 3 * 4 = ${Calculation.multiply(3, 4)}")

    // sealed class
    // cannot init
//    val vehicle: Vehicle = Vehicle("bicycle", "red")
    val bicycle: Bicycle = Bicycle("xe dap", "yellow", 2023)
    println(bicycle)
    val car = Car("o to", "red", 300.0f)
    bicycle.hasBasket(true)

    // filter
    var numbers = listNumber.filter { it in 1..4 }
    println(numbers)
    var sortNumber = numbers.sortedBy { it }
    // split list
    var (lowerNumber, higherNumber) = listNumber.partition { it <= 4 }
    println("$lowerNumber \n $higherNumber")
    // min max
    println("${listNumber.minOf { it }} \n ${listNumber.maxOf { it }}")

    // interface, delegate
    val connectionString = "myServer;myDb;myUsername;myPassword"
    val repository = MySQLRepository(connectionString)
    println(repository.connectionString)
    // delegate
    val myDBRepository = MyDBRepository(connectionString)
    myDBRepository.connectToDb(connectionString)
    // delegate property
    val user2 = User(2, "Linh", "linh@gmail.com")
    println(user2.age)
    user2.age = 23
    println(user2.age)
    //delegate: lazy, observable
    println("user information: ${user2.infoUser}")
    // observable: run when property's value changes
    var product = Product(mapOf(
        "name" to "Bicycle",
        "year" to 2023
    ))
    println(product.toString())
    product.infoProduct = "new value"
    // vetoable
    product.limit = 20
    product.limit = 0 // 0 < 10 -> still old value
    println(product.limit)

    // is operator
    fun typeObj(obj: Any){
        when(obj){
            is String -> println("is String")
            is Bicycle -> println("is Bicycle")
            is Product -> println("is Product")
            is User -> println("is User")
            else -> println("Unknown")
        }
    }
    typeObj(product)
    typeObj("This is String")

    // as? operator -> right-hand side is nullable
    val y = null
    val t: String? = y as? String
    println("t = $t")

    // open class
    val cat = Cat()
    cat.describe()
    cat.greed()
    val animal = Animal("Dog", 2)

}