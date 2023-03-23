package com.robsonapsilva.poc.security

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.robsonapsilva.poc.exception.ErrorResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationEntryPoint : AuthenticationEntryPoint {

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException?
    ) {
        response.contentType = "application/json"
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        val erro = ErrorResponse(HttpStatus.UNAUTHORIZED.value(), "Unauthorized", listOf())
        response.outputStream.print(jacksonObjectMapper().writeValueAsString(erro))
    }
}