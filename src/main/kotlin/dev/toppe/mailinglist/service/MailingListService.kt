package dev.toppe.mailinglist.service

import dev.toppe.mailinglist.NotFoundException
import dev.toppe.mailinglist.catchingNotFound
import dev.toppe.mailinglist.model.MailingList
import dev.toppe.mailinglist.model.Subscription
import dev.toppe.mailinglist.repository.MailingListRepository
import dev.toppe.mailinglist.repository.SubscriptionRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class MailingListService(private val mailingListRepository: MailingListRepository, private val subscriptionRepository: SubscriptionRepository) {


    fun subscribeMailingList(email: String, listId: Long) {
        mailingListRepository.findById(listId).map {
            subscriptionRepository.save(Subscription(email, it))
        }.orElseThrow { NotFoundException("mailing list") }
    }

    // Without @Transactional this breaks
    @Transactional
    fun unsubscribeMailingList(email: String, listId: Long) {
        mailingListRepository.findById(listId).map {
            catchingNotFound {
                subscriptionRepository.deleteByEmailAndMailingList(email, it)
            }
        }
    }

    fun listMailingLists(listId: Long): MailingList {
        return mailingListRepository.findById(listId).orElseThrow { NotFoundException("mailing list") }
    }

    fun listMailingLists(): MutableIterable<MailingList> = mailingListRepository.findAll()

    fun createMailingList(mailingList: MailingList) = mailingListRepository.save(mailingList)

    fun deleteMailingList(listId: Long) = catchingNotFound { mailingListRepository.deleteById(listId) }

}