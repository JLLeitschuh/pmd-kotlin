enum class Message(val str: String) {
    ERROR : Message("This is an error"),
    WARNING : Message("This is a friendly warning"),
    DEBUG : Message("Ignore this")
}