package pl.mariuszk.user

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "user")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "username", nullable = false)
    val name: String,

    @Column(name = "password", nullable = false)
    val encodedPassword: String) : UserDetails {

    override fun getAuthorities(): List<GrantedAuthority> = emptyList()

    override fun getUsername() = name

    override fun getPassword() = encodedPassword
}