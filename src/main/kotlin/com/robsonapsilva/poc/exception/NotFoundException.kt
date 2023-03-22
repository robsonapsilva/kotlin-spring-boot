package com.robsonapsilva.poc.exception

class NotFoundException(override val message: String, val errorCode: String) : Exception() {
}