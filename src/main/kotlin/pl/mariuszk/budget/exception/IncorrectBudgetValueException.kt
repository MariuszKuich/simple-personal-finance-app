package pl.mariuszk.budget.exception

import java.math.BigDecimal

class IncorrectBudgetValueException(id: Long, expenses: BigDecimal):
    RuntimeException("Incorrect value for budget with id=$id - expenses [$expenses PLN] exceed the new budget value")