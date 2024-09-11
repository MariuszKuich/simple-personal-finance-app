package pl.mariuszk.budget

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import pl.mariuszk.user.User
import java.math.BigDecimal
import java.time.LocalDate

@Entity
@Table(name = "budget")
data class Budget(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "amount", nullable = false)
    @field:NotNull
    var amount: BigDecimal,

    @Column(name = "for_date", nullable = false)
    @field:NotNull
    val forDate: LocalDate,

    @ManyToOne
    @JoinColumn(name = "user_id")
    @field:NotNull
    @JsonIgnore
    val user: User
)