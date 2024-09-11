package pl.mariuszk.budget

import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/budget")
class BudgetController(val budgetService: BudgetService) {

    @PostMapping
    fun addNewEntry(@Valid @RequestBody budgetDto: NewBudgetDto, request: HttpServletRequest): ResponseEntity<Budget> {
        val budget = budgetService.add(budgetDto, request)
        return ResponseEntity.ok(budget)
    }

    @PutMapping("/{id}")
    fun updateEntry(@PathVariable("id") id: Long, @Valid @RequestBody budgetDto: UpdateBudgetDto,
                    request: HttpServletRequest):
            ResponseEntity<Budget> {
        val updatedBudget = budgetService.update(id, budgetDto, request)
        return ResponseEntity.ok(updatedBudget)
    }
}