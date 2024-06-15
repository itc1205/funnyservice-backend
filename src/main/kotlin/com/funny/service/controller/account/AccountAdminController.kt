package com.funny.service.controller.account

import com.funny.service.controller.account.schema.AccountResponse
import com.funny.service.controller.account.schema.toAccountResponse
import com.funny.service.service.account.AccountService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/v1/admin/account")
class AccountAdminController (
    private val accountService: AccountService
) {
    @GetMapping("/{id}")
    fun getAccountById(id: UUID) : AccountResponse {
        return accountService.getById(id).toAccountResponse()
    }

    @PatchMapping("/updateStatus/{id}")
    fun updateStatus(id: UUID, status: String) {
        accountService.updateStatus(id, status)
    }

    @PatchMapping("/updateOnline/{id}")
    fun updateIsOnline(id: UUID, isOnline: Boolean) {
        accountService.updateOnline(id, isOnline)
    }

    @PatchMapping("/updatePassword/{id}")
    fun updatePassword(id: UUID, password: String) {
        accountService.updatePassword(id, password)
    }

}