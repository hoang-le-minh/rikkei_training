package storage

class MySQLRepository(val connectionString: String): IStorageRepository {
    override fun connectToDb(connectionString: String) {
        println("connect with $connectionString")
    }
}