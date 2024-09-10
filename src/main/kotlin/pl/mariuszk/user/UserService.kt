package pl.mariuszk.user

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import pl.mariuszk.user.exception.UsernameTakenException

@Service
class UserService(val userRepository: UserRepository, val passwordEncoder: PasswordEncoder) {

    fun signUp(userDto: UserSignUpDto) {
        val user = userRepository.findByName(userDto.username)
        if (user != null) {
            throw UsernameTakenException(userDto.username)
        }
        val password = passwordEncoder.encode(userDto.password)
        userRepository.save(User(name = userDto.username, encodedPassword = password))
    }
}