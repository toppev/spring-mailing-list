package dev.toppe.mailinglist.service

import dev.toppe.mailinglist.model.User
import dev.toppe.mailinglist.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder) {

    // Currently not used as there's no registration page
    fun saveUser(user: User): User {
        user.password = passwordEncoder.encode(user.password)
        return userRepository.save(user)
    }

    // THIS IS FOR TESTING ONLY
    /*
    @PostConstruct
    fun init() {
        //saveUser(User("username", "example@email.com", "password"))
    }
     */

}