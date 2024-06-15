package com.funny.service.service.account

import com.funny.service.persistence.entity.AccountEntity
import com.funny.service.persistence.entity.Role
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

data class AccountDTO (
    val id: UUID?,
    val login: String,
    var hashedPassword: String,
    val status: String,
    val isOnline: Boolean,
    val role: Role
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return arrayListOf(SimpleGrantedAuthority(role.name))
    }

    override fun getPassword(): String {
        return this.hashedPassword
    }

    override fun getUsername(): String {
        return this.login
    }

}

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