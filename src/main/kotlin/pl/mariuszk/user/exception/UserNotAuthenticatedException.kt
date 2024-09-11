package pl.mariuszk.user.exception

class UserNotAuthenticatedException: RuntimeException("User not found in the security context")