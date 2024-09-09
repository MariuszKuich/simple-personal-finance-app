package pl.mariuszk

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SimplePersonalFinanceAppApplication

fun main(args: Array<String>) {
	runApplication<SimplePersonalFinanceAppApplication>(*args)
}
