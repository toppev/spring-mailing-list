package dev.toppe.mailinglist

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.TransactionSystemException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import javax.validation.ConstraintViolationException


@ControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {

    // Handles validation errors
    // e.g email format
    @ExceptionHandler(TransactionSystemException::class)
    fun handleConstraintViolation(ex: Exception, request: WebRequest?): ResponseEntity<Any>? {
        val cause = (ex as TransactionSystemException).rootCause
        if (cause is ConstraintViolationException) {
            val errors: List<String> = cause.constraintViolations.map { "Error with ${it.propertyPath}: ${it.message}" }
            return ResponseEntity(errors, HttpHeaders(), HttpStatus.BAD_REQUEST)
        }
        return null
    }
}