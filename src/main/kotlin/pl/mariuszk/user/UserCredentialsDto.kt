package pl.mariuszk.user

import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length

data class UserCredentialsDto(
//    applying annotation to the underlying field, not the property (getter/setter) itself
    @field:NotBlank(message = "Username not specified")
    @field:Length(max = 50, message = "Username's max length is 50 characters")
    val username: String,
    @field:NotBlank(message = "Password not specified")
    @field:Length(min = 8, message = "Password must be at least 8 characters long")
    val password: String)