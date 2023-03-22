package com.robsonapsilva.poc.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest

@RestControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(NotFoundException::class)
    fun notFoundException(ex: NotFoundException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val erro = ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.message, null)
        return ResponseEntity(erro, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(PreCondintionFailedException::class)
    fun notFoundException(ex: PreCondintionFailedException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val erro = ErrorResponse(HttpStatus.PRECONDITION_FAILED.value(), ex.message, null)
        return ResponseEntity(erro, HttpStatus.PRECONDITION_FAILED)
    }


    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException, request: WebRequest)
            : ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.localizedMessage, buildBindingResult(ex))
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }


    private fun buildBindingResult(bindException: BindException): List<FieldErrorResponse> {
        return bindException.bindingResult.fieldErrors.map {
            FieldErrorResponse(
                it.defaultMessage ?: "invalid", it.field
            )
        }
    }

}