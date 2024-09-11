package pl.mariuszk.budget

import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Service
import pl.mariuszk.budget.exception.BudgetAlreadyExistsException
import pl.mariuszk.user.UserService
import java.time.LocalDate

@Service
class BudgetService(val budgetRepository: BudgetRepository, val userService: UserService) {

    fun add(budgetDto: NewBudgetDto, request: HttpServletRequest): Budget {
        val user = userService.getAuthenticatedUser(request)
        val forDate = LocalDate.of(budgetDto.year, budgetDto.month, 1)
        if (budgetRepository.existsByForDateAndUser(forDate, user)) {
            throw BudgetAlreadyExistsException(forDate)
        }
        val budget = Budget(amount = budgetDto.amount, forDate = forDate, user = user)
        return budgetRepository.save(budget)
    }
}