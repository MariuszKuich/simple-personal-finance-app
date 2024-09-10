package pl.mariuszk.user

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.mariuszk.user.exception.UsernameTakenException

@RestController
@RequestMapping("/api/public")
class UserController(val userService: UserService) {

    @PostMapping("/sign-up")
    fun signUp(@RequestBody userDto: UserSignUpDto): ResponseEntity<Any> {
        try {
            userService.signUp(userDto)
            return ResponseEntity.ok().build()
        } catch (ex: UsernameTakenException) {
            return ResponseEntity.badRequest().body(ex.message)
        }
    }
}