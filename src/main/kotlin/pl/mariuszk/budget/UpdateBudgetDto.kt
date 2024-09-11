package pl.mariuszk.budget

import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class UpdateBudgetDto(
    @field:NotNull(message = "Amount must be specified")
    val amount: BigDecimal
)