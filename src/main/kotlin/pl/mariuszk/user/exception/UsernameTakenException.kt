package pl.mariuszk.user.exception

class UsernameTakenException(private val username: String): RuntimeException("Username=$username is already taken")