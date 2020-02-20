package dev.toppe.mailinglist.model

import javax.persistence.*

@Entity
data class User(
        @Column
        val username: String,
        @Column
        var password: String,
        @Column
        val role: Role = Role.ADMIN
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}