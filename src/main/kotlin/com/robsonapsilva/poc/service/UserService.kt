package com.robsonapsilva.poc.service

import com.robsonapsilva.poc.enums.RoleEnum
import com.robsonapsilva.poc.enums.StatusEnum
import com.robsonapsilva.poc.exception.NotFoundException
import com.robsonapsilva.poc.exception.PreCondintionFailedException
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

    fun findById(id: Long): UserModel {
        return userRepository.findById(id).orElseThrow { NotFoundException("Usuário não encontrado") }
    }

    fun update(user: UserModel) {
        user.id.let {
            val previous = findById(it!!)
            val current = previous.copy(name = user.name, email = user.email)
            userRepository.save(current)
        }
    }

    fun delete(id: Long) {
        val user = findById(id)
        if (user.roles.contains(RoleEnum.ADMIN)) {
            throw PreCondintionFailedException("Usuário com perfil ${RoleEnum.ADMIN.description}, não pode ser removido")
        }
        user.status = StatusEnum.INACTIVE
        userRepository.save(user)
    }

    fun emailAvaliable(email: String): Boolean = !userRepository.existsByEmail(email)

}