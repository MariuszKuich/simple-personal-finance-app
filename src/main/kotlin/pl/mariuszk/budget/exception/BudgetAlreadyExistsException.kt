package pl.mariuszk.budget.exception

import java.time.LocalDate

class BudgetAlreadyExistsException(forDate: LocalDate):
        RuntimeException("Budget for date=$forDate for current user already exists; send PUT request to update it")