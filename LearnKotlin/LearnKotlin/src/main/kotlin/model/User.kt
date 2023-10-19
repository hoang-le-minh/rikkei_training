package model

data class User(val id: Int, var name: String, var email: String) {

    override fun equals(other: Any?): Boolean {
        return other is User && this.id == other.id && this.email == other.email
    }

}