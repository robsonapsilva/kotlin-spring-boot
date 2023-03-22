package com.robsonapsilva.poc.controller.request

import com.robsonapsilva.poc.validation.CheckEmail
import jakarta.validation.constraints.NotBlank

data class PutUserRequest(

    @field:NotBlank
    val name: String,

    @field:CheckEmail
    val email: String,

    )