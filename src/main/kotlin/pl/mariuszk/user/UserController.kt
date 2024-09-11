package pl.mariuszk.user

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/public")
class UserController(val userService: UserService) {

    @PostMapping("/sign-up")
    fun signUp(@Valid @RequestBody userDto: UserCredentialsDto): ResponseEntity<Any> {
        userService.signUp(userDto)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/login")
    fun login(@RequestBody userDto: UserCredentialsDto, request: HttpServletRequest,
              response: HttpServletResponse): ResponseEntity<Nothing> {
        userService.signIn(userDto, request, response)
        return ResponseEntity.ok().build()
    }
}