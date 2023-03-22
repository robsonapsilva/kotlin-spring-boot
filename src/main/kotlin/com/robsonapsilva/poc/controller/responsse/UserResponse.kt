package com.robsonapsilva.poc.controller.responsse

import com.robsonapsilva.poc.enums.StatusEnum

data class UserResponse(
    val id: Long?,
    val name: String,
    val email: String,
    val status: StatusEnum
)
