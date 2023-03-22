package com.robsonapsilva.poc.exception

data class ErrorResponse(
    var httpCode: Int,
    var message: String,
    var errors: List<FieldErrorResponse>?
)