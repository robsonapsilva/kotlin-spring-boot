package com.robsonapsilva.poc.security

import org.springframework.security.access.prepost.PreAuthorize

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@PreAuthorize("hasRole('ROLE_ADMIN') || #id.toString() == authentication.name")
annotation class AdminAndOwnerAuthorized

