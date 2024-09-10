package pl.mariuszk.user

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.mariuszk.user.exception.UsernameTakenException

@RestController
@RequestMapping("/api/public")
class UserController(val userService: UserService) {

    @PostMapping("/sign-up")
    fun signUp(@RequestBody userDto: UserCredentialsDto): ResponseEntity<Any> {
        try {
            userService.signUp(userDto)
            return ResponseEntity.ok().build()
        } catch (ex: UsernameTakenException) {
            return ResponseEntity.badRequest().body(ex.message)
        }
    }

    @PostMapping("/login")
    fun login(@RequestBody userDto: UserCredentialsDto, request: HttpServletRequest,
              response: HttpServletResponse): ResponseEntity<String> {
        try {
            userService.signIn(userDto, request, response)
            return ResponseEntity.ok().build()
        } catch (ex: AuthenticationException) {
            return ResponseEntity.badRequest().body(ex.message)
        }
    }
}