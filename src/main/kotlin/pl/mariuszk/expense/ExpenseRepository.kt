package pl.mariuszk.expense

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import pl.mariuszk.user.User
import java.time.LocalDate

interface ExpenseRepository: JpaRepository<Expense, Long> {

    @Query("SELECT e FROM Expense e WHERE e.user = :user " +
            "AND date >= :startDate " +
            "AND date < :endDate")
    fun findByUserAndDateBetween(@Param("user") user: User,
                                 @Param("startDate") startDateInclusive: LocalDate,
                                 @Param("endDate") endDateExclusive: LocalDate): List<Expense>
}