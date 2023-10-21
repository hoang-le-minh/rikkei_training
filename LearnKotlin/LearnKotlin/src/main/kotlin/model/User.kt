package model

import kotlin.reflect.KProperty

data class User(val id: Int, var name: String, var email: String) {

    override fun equals(other: Any?): Boolean {
        return other is User && this.id == other.id && this.email == other.email
    }

    var age: Int by UserDelegate()

    // lazy
    val infoUser: String by lazy {
        "id: $id, name: $name, email: $email, age: $age"
    }
}

class UserDelegate(){
    private var value: Int = 0

    operator fun getValue(user: User, property: KProperty<*>): Int {
        println("${property.name} called getValue")
        return value
    }

    operator fun setValue(user: User, property: KProperty<*>, i: Int) {
        println("${property.name} called setValue")
        value = i
    }
}