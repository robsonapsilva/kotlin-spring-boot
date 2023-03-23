package com.robsonapsilva.poc.security

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.robsonapsilva.poc.controller.request.LoginRequest
import com.robsonapsilva.poc.repository.UserRepository
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class AuthenticationFilter(
    private val authenticationManager: AuthenticationManager,
    private val jwtUtil: JwtUtil,
    private val userRepository: UserRepository
) : UsernamePasswordAuthenticationFilter(authenticationManager) {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse?): Authentication {
        val login = jacksonObjectMapper().readValue(request.inputStream, LoginRequest::class.java)
        val id = userRepository.findByEmail(login.email)?.id
        val authToken = UsernamePasswordAuthenticationToken(id, login.password)
        return authenticationManager.authenticate(authToken)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        val userName= (authResult?.principal as UserCustomDetails).username
        val token = jwtUtil.generateToken(userName!!)
        response?.addHeader(HttpHeaders.AUTHORIZATION, "Bearer $token")
    }

}