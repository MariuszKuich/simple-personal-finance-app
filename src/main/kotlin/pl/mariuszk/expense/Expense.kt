package pl.mariuszk.expense

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.Length
import pl.mariuszk.budget.category.Category
import pl.mariuszk.user.User
import java.math.BigDecimal
import java.time.LocalDate

@Entity
@Table(name = "expense")
data class Expense(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "amount", nullable = false)
    @field:NotNull
    val amount: BigDecimal,

    @Column(name = "date", nullable = false)
    @field:NotNull
    val date: LocalDate,

    @Column(name = "description")
    @field:Length(max = 200)
    val description: String,

    @ManyToOne
    @JoinColumn(name = "user_id")
    @field:NotNull
    @JsonIgnore
    val user: User,

    @ManyToOne
    @JoinColumn(name = "category_id")
    @field:NotNull
    @JsonIgnore
    val category: Category
)