package com.robsonapsilva.poc.validation

import com.robsonapsilva.poc.service.UserService
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class CheckEmailValidator(private val userService: UserService) : ConstraintValidator<CheckEmail, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if (value.isNullOrBlank()) {
            return false
        }
        return userService.emailAvaliable(value)
    }

}
