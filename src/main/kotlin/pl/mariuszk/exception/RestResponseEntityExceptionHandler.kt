package pl.mariuszk.exception

import org.slf4j.Logger
import org.springframework.http.ResponseEntity
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import pl.mariuszk.config.logger
import pl.mariuszk.user.exception.UsernameTakenException

@ControllerAdvice
class RestResponseEntityExceptionHandler(
    val log: Logger = logger<RestResponseEntityExceptionHandler>()) {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException,
                                              request: WebRequest): ResponseEntity<String> {
        val errorMessage = StringBuilder("Provided input has the following errors:\n")
        ex.bindingResult.fieldErrors.forEach { errorMessage.append("${it.defaultMessage}\n") }
        return ResponseEntity.badRequest().body(errorMessage.toString())
    }

    @ExceptionHandler(UsernameTakenException::class, AuthenticationException::class)
    fun handleBadRequestException(ex: Exception, request: WebRequest): ResponseEntity<String> {
        log.error("${ex.message} [sessionId=${request.sessionId}]")
        return ResponseEntity.badRequest().body(ex.message)
    }
}