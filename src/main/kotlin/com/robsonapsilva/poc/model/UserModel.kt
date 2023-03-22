package com.robsonapsilva.poc.model

import com.robsonapsilva.poc.enums.RoleEnum
import com.robsonapsilva.poc.enums.StatusEnum
import jakarta.persistence.*
import java.util.*

@Entity(name = "user")
data class UserModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column
    var name: String,


    @Column
    var email: String,

    @Column
    var password: String,


    @Column
    @Enumerated(EnumType.STRING)
    var status: StatusEnum = StatusEnum.ACTIVE,

    @CollectionTable(name = "user_role", joinColumns = [JoinColumn(name = "user_id")])
    @ElementCollection(targetClass = RoleEnum::class, fetch = FetchType.EAGER)
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    var roles: Set<RoleEnum> = setOf()

)

