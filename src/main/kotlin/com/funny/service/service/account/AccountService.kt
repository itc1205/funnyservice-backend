package com.funny.service.service.account

import java.util.UUID

interface AccountService {
    fun getById(accountId: UUID) : AccountDTO
    fun createAccount(account: AccountDTO): UUID
    fun deleteAccount(accountId: UUID)
    fun updateAccount(account: AccountDTO)
    fun updateStatus(accountId: UUID, status: String)
    fun updateOnline(accountId: UUID, online: Boolean)
}