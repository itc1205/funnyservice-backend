package com.funny.service.service.account

import com.funny.service.persistence.entity.Role
import com.funny.service.persistence.repo.AccountRepository
import com.funny.service.service.account.exception.AccountAlreadyExistsException
import com.funny.service.service.account.exception.AccountNotFoundException
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*

@Service
class AccountServiceImpl(
    val accountRepository: AccountRepository
) : AccountService
{
    override fun getById(accountId: UUID): AccountDTO {
        val account = accountRepository.findById(accountId).orElseThrow { AccountNotFoundException() }
        return account.toAccountDTO()
    }

    override fun getByLogin(login: String): AccountDTO {
        val account = accountRepository.findByLogin(login).orElseThrow { AccountNotFoundException() }
        return account.toAccountDTO()
    }

    override fun createAccount(account: AccountDTO): UUID {
        accountRepository.findByLogin(account.login).ifPresent { throw AccountAlreadyExistsException() }
        return accountRepository.save(account.toAccountEntity()).id!!
    }

    override fun deleteAccount(accountId: UUID) {
        val accountEntity = accountRepository.findById(accountId).orElseThrow { AccountNotFoundException() }
        accountRepository.delete(accountEntity)
    }

    override fun updateAccount(account: AccountDTO) {
        accountRepository.findById(account.id!!).orElseThrow { AccountNotFoundException() }
        val accountEntity = account.toAccountEntity()
        accountRepository.save(accountEntity)
    }

    override fun updateStatus(accountId: UUID, status: String) {
        val accountEntity = accountRepository.findById(accountId).orElseThrow { AccountNotFoundException() }
        accountEntity.status = status
        accountRepository.save(accountEntity)
    }

    override fun updateOnline(accountId: UUID, online: Boolean) {
        val accountEntity = accountRepository.findById(accountId).orElseThrow { AccountNotFoundException() }
        accountEntity.isOnline = online
        accountRepository.save(accountEntity)
    }

    override fun updateRole(accountId: UUID, role: Role) {
        val accountEntity = accountRepository.findById(accountId).orElseThrow { AccountNotFoundException() }
        accountEntity.role = role
        accountRepository.save(accountEntity)
    }

    override fun updatePassword(accountId: UUID, password: String) {
        val accountEntity = accountRepository.findById(accountId).orElseThrow { AccountNotFoundException() }
        accountEntity.hashedPassword = password
        accountRepository.save(accountEntity)
    }

    override fun userDetailsService(): UserDetailsService {
        return UserDetailsService { username -> getByLogin(username) }
    }
}