package dev.toppe.mailinglist.model

import javax.persistence.*

@Entity
data class MailingList(@Column val name: String) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "mailingList")
    val subscription = mutableListOf<Subscription>()

}