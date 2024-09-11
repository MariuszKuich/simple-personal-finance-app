package pl.mariuszk.budget

import jakarta.servlet.http.HttpServletRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import pl.mariuszk.budget.exception.BudgetAlreadyExistsException
import pl.mariuszk.budget.exception.BudgetNotFoundException
import pl.mariuszk.budget.exception.IncorrectBudgetValueException
import pl.mariuszk.expense.ExpenseService
import pl.mariuszk.user.UserService
import java.time.LocalDate

@Service
class BudgetService(val budgetRepository: BudgetRepository,
                    val userService: UserService,
                    val expenseService: ExpenseService) {

    fun add(budgetDto: NewBudgetDto, request: HttpServletRequest): Budget {
        val user = userService.getAuthenticatedUser(request)
        val forDate = LocalDate.of(budgetDto.year, budgetDto.month, 1)
        if (budgetRepository.existsByForDateAndUser(forDate, user)) {
            throw BudgetAlreadyExistsException(forDate)
        }
        val budget = Budget(amount = budgetDto.amount, forDate = forDate, user = user)
        return budgetRepository.save(budget)
    }

    fun update(id: Long, budgetDto: UpdateBudgetDto, request: HttpServletRequest): Budget {
        val user = userService.getAuthenticatedUser(request)
        val budget = budgetRepository.findByIdOrNull(id) ?: throw BudgetNotFoundException(id)
        val totalExpenses = expenseService.getTotalExpensesForMonthAndYear(user,
            budget.forDate.monthValue, budget.forDate.year)
        if (totalExpenses > budgetDto.amount) {
            throw IncorrectBudgetValueException(id, totalExpenses)
        }
        budget.amount = budgetDto.amount
        return budgetRepository.save(budget)
    }
}