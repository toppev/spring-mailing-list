package dev.toppe.mailinglist.repository

import dev.toppe.mailinglist.model.MailingList
import dev.toppe.mailinglist.model.Subscription
import org.springframework.data.repository.CrudRepository


interface SubscriptionRepository : CrudRepository<Subscription, String> {

    fun deleteByEmailAndMailingList(email: String, mailingList: MailingList)

}