package pl.mariuszk.budget

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/budget")
class BudgetController {

    @GetMapping("/test")
    fun test() = ResponseEntity.ok("Only for authenticated users")
}