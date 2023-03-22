package com.robsonapsilva.poc.controller

import com.robsonapsilva.poc.controller.request.PostUserRequest
import com.robsonapsilva.poc.controller.responsse.UserResponse
import com.robsonapsilva.poc.extension.toResponse
import com.robsonapsilva.poc.extension.toUserModel
import com.robsonapsilva.poc.service.UserService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {


    @GetMapping
    fun list(@PageableDefault(page = 0, size = 10) pageable: Pageable): ResponseEntity<Page<UserResponse>> {
        return ResponseEntity.ok().body(userService.list(pageable).map { it.toResponse() })
    }

    @PostMapping
    fun create(@RequestBody @Valid userRequest: PostUserRequest): ResponseEntity<Unit> {
        val id = userService.create(userRequest.toUserModel())
        return ResponseEntity.created(URI.create("/users/${id}")).build()
    }

}