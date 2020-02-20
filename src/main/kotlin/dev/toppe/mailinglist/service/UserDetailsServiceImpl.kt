package dev.toppe.mailinglist.service

import dev.toppe.mailinglist.model.User
import dev.toppe.mailinglist.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import javax.transaction.Transactional


@Service
class UserDetailsServiceImpl(private val userRepository: UserRepository) : UserDetailsService {

    @Transactional
    override fun loadUserByUsername(username: String): UserDetails? {
        val user: User = userRepository.findByUsername(username) ?: throw UsernameNotFoundException(username)
        // Currently only one role
        val authorities = arrayListOf(SimpleGrantedAuthority(user.role.name))
        return org.springframework.security.core.userdetails.User(user.username, user.password, authorities)
    }
}