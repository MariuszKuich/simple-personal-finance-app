package pl.mariuszk.user

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.context.SecurityContextHolderStrategy
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.context.SecurityContextRepository
import org.springframework.stereotype.Service
import pl.mariuszk.user.exception.UserNotAuthenticatedException
import pl.mariuszk.user.exception.UsernameNotFoundException
import pl.mariuszk.user.exception.UsernameTakenException

@Service
class UserService(val userRepository: UserRepository,
                  val passwordEncoder: PasswordEncoder,
                  val authManager: AuthenticationManager,
                  val securityContextRepository: SecurityContextRepository) {

    val securityContextHolderStrategy: SecurityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy()

    fun signUp(userDto: UserCredentialsDto) {
        val user = userRepository.findByName(userDto.username)
        if (user != null) {
            throw UsernameTakenException(userDto.username)
        }
        val password = passwordEncoder.encode(userDto.password)
        userRepository.save(User(name = userDto.username, encodedPassword = password))
    }

//    https://docs.spring.io/spring-security/reference/servlet/authentication/session-management.html#store-authentication-manually
    fun signIn(userDto: UserCredentialsDto, request: HttpServletRequest, response: HttpServletResponse) {
        val authentication = authManager.authenticate(
            UsernamePasswordAuthenticationToken(userDto.username, userDto.password))
        val context: SecurityContext = securityContextHolderStrategy.createEmptyContext()
        context.authentication = authentication
        securityContextHolderStrategy.context = context
        securityContextRepository.saveContext(context, request, response)
    }

    fun getAuthenticatedUser(request: HttpServletRequest): User {
        val username = securityContextHolderStrategy.context?.authentication?.name
            ?: throw UserNotAuthenticatedException()
        return userRepository.findByName(username) ?: throw UsernameNotFoundException(username)
    }
}