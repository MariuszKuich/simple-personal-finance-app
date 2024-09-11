package pl.mariuszk.budget

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.time.LocalDate

data class NewBudgetDto(
    @field:NotNull(message = "Amount must be specified")
    val amount: BigDecimal,

    @field:NotNull(message = "Month must be specified")
    @field:Min(value = 1, message = "Min value for month is 1")
    @field:Max(value = 12, message = "Max value for month is 12")
    val month: Int,

    val year: Int = LocalDate.now().year)