package pl.mariuszk

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SimplifiedPersonalFinanceAppApplication

fun main(args: Array<String>) {
	runApplication<SimplifiedPersonalFinanceAppApplication>(*args)
}
