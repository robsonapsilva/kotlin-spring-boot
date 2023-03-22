package com.robsonapsilva.poc.service

import com.robsonapsilva.poc.enums.RoleEnum
import com.robsonapsilva.poc.model.UserModel
import com.robsonapsilva.poc.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val encoder: BCryptPasswordEncoder
) {


    fun list(pageable: Pageable): Page<UserModel> {
        return userRepository.findAll(pageable)
    }

    fun create(userModel: UserModel): Long? {
        val userCopy = userModel.copy(password = encoder.encode(userModel.password), roles = setOf(RoleEnum.USER))
        return userRepository.save(userCopy).id
    }

    fun emailAvaliable(email: String): Boolean = !userRepository.existsByEmail(email)

}