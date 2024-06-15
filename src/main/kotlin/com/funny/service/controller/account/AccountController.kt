package com.funny.service.controller.account

import com.funny.service.controller.account.schema.AccountResponse
import com.funny.service.controller.account.schema.toAccountResponse
import com.funny.service.persistence.entity.Role
import com.funny.service.service.account.AccountService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.security.Principal
import java.util.UUID

@RestController
@RequestMapping("/api/v1/account")
class AccountController (
    private val accountService: AccountService
) {
    @GetMapping("/{id}")
    fun getAccountById(id: UUID) : AccountResponse {
        return accountService.getById(id).toAccountResponse()
    }

    @PatchMapping("/updateStatus/{id}")
    @PreAuthorize("#id == authentication.id")
    fun updateStatus(id: UUID, @RequestBody status: String) {
        accountService.updateStatus(id, status)
    }

    @PatchMapping("/updateOnline/{id}")
    @PreAuthorize("#id == authentication.id")
    fun updateIsOnline(id: UUID, @RequestBody isOnline: Boolean) {
        accountService.updateOnline(id, isOnline)
    }

    @PatchMapping("/updatePassword/{id}")
    @PreAuthorize("#id == authentication.id")
    fun updatePassword(id: UUID, @RequestBody password: String) {
        accountService.updatePassword(id, password)
    }

    @GetMapping("/")
    fun getMe(principal: Principal) : AccountResponse {
        return accountService.getByLogin(principal.name).toAccountResponse()
    }

    @PatchMapping("/becomeAdmin/{id}")
    fun becomeAdmin(id: UUID) {
        return accountService.updateRole(id, Role.ADMIN)
    }
}