package com.robsonapsilva.poc.extension

import com.robsonapsilva.poc.controller.request.PostUserRequest
import com.robsonapsilva.poc.controller.request.PutUserRequest
import com.robsonapsilva.poc.controller.responsse.UserResponse
import com.robsonapsilva.poc.enums.StatusEnum
import com.robsonapsilva.poc.model.UserModel

fun PostUserRequest.toUserModel(): UserModel {
    return UserModel(name = this.name, email = this.email, password = this.password, status = StatusEnum.ACTIVE)
}

fun PutUserRequest.toUserModel(user: UserModel): UserModel {
    return UserModel(user.id, this.name, this.email ,  user.password,user.status, user.roles)
}

fun UserModel.toResponse(): UserResponse {
    return UserResponse(this.id, this.name, this.email, this.status)
}