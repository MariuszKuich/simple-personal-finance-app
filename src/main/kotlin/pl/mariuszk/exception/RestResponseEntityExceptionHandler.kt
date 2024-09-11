package pl.mariuszk.exception

import org.slf4j.Logger
import org.springframework.http.ResponseEntity
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import pl.mariuszk.budget.exception.BudgetAlreadyExistsException
import pl.mariuszk.config.logger
import pl.mariuszk.user.exception.UserNotAuthenticatedException
import pl.mariuszk.user.exception.UsernameNotFoundException
import pl.mariuszk.user.exception.UsernameTakenException

@ControllerAdvice
class RestResponseEntityExceptionHandler(
    val log: Logger = logger<RestResponseEntityExceptionHandler>()) {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException,
                                              request: WebRequest): ResponseEntity<String> {
        val errorMessageBuilder = StringBuilder("Provided input has the following errors:\n")
        ex.bindingResult.fieldErrors.forEach { errorMessageBuilder.append("${it.defaultMessage}\n") }
        val errorMessage = errorMessageBuilder.toString()
        logError(errorMessage, request.sessionId)
        return ResponseEntity.badRequest().body(errorMessage)
    }

    @ExceptionHandler(UsernameTakenException::class, AuthenticationException::class,
        BudgetAlreadyExistsException::class)
    fun handleBadRequestException(ex: Exception, request: WebRequest): ResponseEntity<String> {
        logError(ex.message, request.sessionId)
        return ResponseEntity.badRequest().body(ex.message)
    }

    @ExceptionHandler(UserNotAuthenticatedException::class, UsernameNotFoundException::class)
    fun handleInternalServerErrorException(ex: Exception, request: WebRequest): ResponseEntity<String> {
        logError(ex.message, request.sessionId)
        return ResponseEntity.internalServerError().body(ex.message)
    }

    private fun logError(message: String?, sessionId: String) {
        log.error("$message [sessionId=$sessionId]")
    }
}