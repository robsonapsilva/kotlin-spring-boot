package com.robsonapsilva.poc.service

import com.robsonapsilva.poc.exception.AuthenticationException
import com.robsonapsilva.poc.repository.UserRepository
import com.robsonapsilva.poc.security.UserCustomDetails
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsCustomService(private val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(id: String): UserDetails {
        val user = userRepository.findById(id.toLong()).orElseThrow {
            AuthenticationException("Usuário não encontrado!")
        }
        return UserCustomDetails(user)
    }


}