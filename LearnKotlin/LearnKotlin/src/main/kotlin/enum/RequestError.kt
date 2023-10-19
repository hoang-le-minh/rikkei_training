package enum

enum class RequestError(val message: String) {
    BAD_REQUEST("Invalid request"),
    INTERNAL_ERROR("Internal server error"),
    SUCCESS("Server processes request successfully");

    fun lengthMessage() = message.length
}