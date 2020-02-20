package dev.toppe.mailinglist.repository

import dev.toppe.mailinglist.model.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {

    fun findByUsername(userName: String): User?

}