package dev.toppe.mailinglist.controller

import dev.toppe.mailinglist.service.MailingListService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["/api/v1"])
class SubscriptionController(private val mailingListService: MailingListService) {

    @PostMapping(path = ["/subscribe/{listId}"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun subscribe(@PathVariable listId: Long, @RequestBody body: MailBody) = mailingListService.subscribeMailingList(body.email, listId)

    // Could and probably should be @DeleteMapping ¯\_(ツ)_/¯
    @PostMapping(path = ["/unsubscribe/{listId}"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.OK)
    fun unsubscribe(@PathVariable listId: Long, @RequestBody body: MailBody) = mailingListService.unsubscribeMailingList(body.email, listId)

}

data class MailBody(val email: String)
