package com.funny.service.service.account

import com.funny.service.persistence.entity.Role
import org.springframework.security.core.userdetails.UserDetailsService
import java.util.UUID

interface AccountService {
    fun getById(accountId: UUID) : AccountDTO
    fun getByLogin(login: String) : AccountDTO
    fun createAccount(account: AccountDTO): UUID
    fun deleteAccount(accountId: UUID)
    fun updateAccount(account: AccountDTO)
    fun updateStatus(accountId: UUID, status: String)
    fun updateOnline(accountId: UUID, online: Boolean)
    fun updateRole(accountId: UUID, role: Role)
    fun updatePassword(accountId: UUID, password: String)
    fun userDetailsService() : UserDetailsService
}