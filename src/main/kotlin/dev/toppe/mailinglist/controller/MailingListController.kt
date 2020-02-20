package dev.toppe.mailinglist.controller

import com.opencsv.CSVWriter
import com.opencsv.bean.StatefulBeanToCsvBuilder
import dev.toppe.mailinglist.model.MailingList
import dev.toppe.mailinglist.model.Subscription
import dev.toppe.mailinglist.service.MailingListService
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import javax.servlet.http.HttpServletResponse


@Controller
class MailingListController(private val mailingListService: MailingListService) {

    @GetMapping("/create")
    fun listForm(model: Model): String {
        model.addAttribute("mailingList", MailingList("example name"))
        return "createList"
    }

    @PostMapping("/create")
    fun submitList(@ModelAttribute(value = "item") mailingList: MailingList): String {
        mailingListService.createMailingList(mailingList)
        return "redirect:/mailinglist/${mailingList.id}"
    }

    // Post works better than delete here
    @PostMapping(path = ["/delete/{listId}"])
    fun deleteList(@PathVariable listId: Long): String {
        mailingListService.deleteMailingList(listId)
        return "redirect:/list"
    }

    @GetMapping(path = ["/mailinglist/{listId}"])
    fun mailingList(model: Model, @PathVariable listId: Long): String {
        model.addAttribute("item", mailingListService.listMailingLists(listId))
        return "mailingList"
    }

    // Also the home page
    @GetMapping(path = ["/list", "/"])
    fun listMailingLists(model: Model): String {
        model.addAttribute("items", mailingListService.listMailingLists())
        return "allLists"
    }

    @GetMapping(path = ["/export/{listId}"])
    fun exportCSV(@PathVariable listId: Long, response: HttpServletResponse) {
        val filename = "subscriptions_$listId.csv"
        response.contentType = "text/csv"
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"$filename\"")
        val writer = StatefulBeanToCsvBuilder<Subscription>(response.writer)
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(false)
                .build()
        writer.write(mailingListService.listMailingLists(listId).subscription)
    }

}
