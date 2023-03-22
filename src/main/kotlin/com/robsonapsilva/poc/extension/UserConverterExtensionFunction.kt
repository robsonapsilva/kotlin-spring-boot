package com.robsonapsilva.poc.extension

import com.robsonapsilva.poc.controller.request.PostUserRequest
import com.robsonapsilva.poc.controller.responsse.UserResponse
import com.robsonapsilva.poc.enums.StatusEnum
import com.robsonapsilva.poc.model.UserModel

fun PostUserRequest.toUserModel(): UserModel {
    return UserModel(name = this.name, email = this.email, password = this.password, status = StatusEnum.ACTIVE)
}

fun UserModel.toResponse(): UserResponse {
    return UserResponse(this.id, this.name, this.email, this.status)
}