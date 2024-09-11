package pl.mariuszk.user.exception

class UsernameNotFoundException(private val username: String):
    RuntimeException("User with username=$username not found")