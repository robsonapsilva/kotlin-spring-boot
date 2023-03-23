package com.robsonapsilva.poc.security

import com.robsonapsilva.poc.enums.StatusEnum
import com.robsonapsilva.poc.model.UserModel
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserCustomDetails(private val userModel: UserModel) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = userModel.roles
        .map { SimpleGrantedAuthority(it.description) }
        .toMutableList()

    override fun getPassword(): String = userModel.password

    override fun getUsername(): String = userModel.id.toString()
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = userModel.status == StatusEnum.ACTIVE
}