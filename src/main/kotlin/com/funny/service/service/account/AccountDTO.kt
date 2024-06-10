package com.funny.service.service.account

import com.funny.service.persistence.entity.AccountEntity
import com.funny.service.persistence.entity.Role
import java.util.*

data class AccountDTO (
    val id: UUID?,
    val login: String,
    val hashedPassword: String,
    val status: String,
    val isOnline: Boolean,
    val role: Role
)

fun AccountEntity.toAccountDTO() = AccountDTO(
    id = id,
    login = login,
    hashedPassword = hashedPassword,
    status = status,
    isOnline = isOnline,
    role = role
)

fun AccountDTO.toAccountEntity() = AccountEntity(
    id = id,
    login = login,
    hashedPassword = hashedPassword,
    status = status,
    isOnline = isOnline,
    role = role
)