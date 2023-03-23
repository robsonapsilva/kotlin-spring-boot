package com.robsonapsilva.poc.config

import com.robsonapsilva.poc.repository.UserRepository
import com.robsonapsilva.poc.security.AuthenticationFilter
import com.robsonapsilva.poc.security.AuthorizationFilter
import com.robsonapsilva.poc.security.CustomAuthenticationEntryPoint
import com.robsonapsilva.poc.security.JwtUtil
import com.robsonapsilva.poc.service.UserDetailsCustomService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableMethodSecurity
class SecurityConfig(
    private val customAuthenticationEntryPoint: CustomAuthenticationEntryPoint,
    private val jwtUtil: JwtUtil,
    private val userDetailsCustomService: UserDetailsCustomService,
    private val userRepository: UserRepository
) {

    @Bean
    fun encoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        val authenticationManager = authManager(http)
        http.cors().and().csrf().disable()
            .authorizeHttpRequests().requestMatchers(HttpMethod.POST, "/users").permitAll()
            .anyRequest().authenticated().and()
            .authenticationManager(authenticationManager)
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .addFilter(AuthenticationFilter(authenticationManager, jwtUtil, userRepository))
            .addFilter(AuthorizationFilter(authenticationManager, jwtUtil, userDetailsCustomService))
            .exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint)
        return http.build()
    }

    private fun authManager(http: HttpSecurity): AuthenticationManager {
        val authManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder::class.java)
        authManagerBuilder.userDetailsService(userDetailsCustomService)
        return authManagerBuilder.build()
    }


}
