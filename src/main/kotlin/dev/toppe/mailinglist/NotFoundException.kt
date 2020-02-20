package dev.toppe.mailinglist

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException(message: String?) : RuntimeException(message)

fun catchingNotFound(run: () -> Unit) {
    try {
        run()
    } catch (e: EmptyResultDataAccessException) {
        throw NotFoundException("the item was not found")
    }
}
