package com.funny.service.controller.account.schema

import com.funny.service.persistence.entity.Role
import com.funny.service.service.account.AccountDTO
import java.util.UUID

data class AccountResponse(
    val id: UUID,
    val login: String,
    var status: String,
    var isOnline: Boolean,
    var role: Role
)

fun AccountDTO.toAccountResponse() : AccountResponse = AccountResponse(
    id!!, login, status, isOnline, role
)