package storage

interface IStorageRepository {
    fun connectToDb(connectionString: String)
}