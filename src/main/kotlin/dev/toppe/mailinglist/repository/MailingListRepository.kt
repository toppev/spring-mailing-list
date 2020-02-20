package dev.toppe.mailinglist.repository

import dev.toppe.mailinglist.model.MailingList
import org.springframework.data.repository.CrudRepository


interface MailingListRepository : CrudRepository<MailingList, Long>