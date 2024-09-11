package pl.mariuszk.budget

import org.springframework.data.jpa.repository.JpaRepository
import pl.mariuszk.user.User
import java.time.LocalDate

interface BudgetRepository: JpaRepository<Budget, Long> {

    fun existsByForDateAndUser(forDate: LocalDate, user: User): Boolean
}