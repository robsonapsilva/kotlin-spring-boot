package com.robsonapsilva.poc.validation

import jakarta.validation.Constraint
import jakarta.validation.constraints.Email
import kotlin.reflect.KClass

@Email
@Constraint(validatedBy = [CheckEmailValidator::class])
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class CheckEmail(
    val message: String = "e-mail already exists",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<*>> = []
)
