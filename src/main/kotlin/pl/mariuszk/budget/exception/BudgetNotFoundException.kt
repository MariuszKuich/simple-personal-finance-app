package pl.mariuszk.budget.exception

class BudgetNotFoundException(id: Long): RuntimeException("Budget with id=$id not found")