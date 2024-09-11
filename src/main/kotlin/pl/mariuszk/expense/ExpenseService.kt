package pl.mariuszk.expense

import org.springframework.stereotype.Service
import pl.mariuszk.user.User
import java.math.BigDecimal
import java.time.LocalDate

@Service
class ExpenseService(val expenseRepository: ExpenseRepository) {

    fun getTotalExpensesForMonthAndYear(user: User, month: Int, year: Int): BigDecimal {
        val startDate = LocalDate.of(year, month, 1)
        val endDate = startDate.plusMonths(1L)
        return expenseRepository.findByUserAndDateBetween(user, startDate, endDate).sumOf { it.amount }
    }
}