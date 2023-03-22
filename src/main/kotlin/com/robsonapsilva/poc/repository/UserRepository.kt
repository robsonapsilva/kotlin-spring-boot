package com.robsonapsilva.poc.repository

import com.robsonapsilva.poc.model.UserModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface UserRepository : JpaRepository<UserModel, Long> {

    fun existsByEmail(email: String): Boolean
}