package com.funny.service.controller.account

import com.funny.service.controller.account.schema.*
import com.funny.service.service.account.AccountService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/v1/admin/account")
class AccountAdminController (
    private val accountService: AccountService
) {
    @GetMapping("/{id}")
    fun getAccountById(@PathVariable id: UUID) : AccountResponse {
        return accountService.getById(id).toAccountResponse()
    }

    @PatchMapping("/updateStatus/{id}")
    fun updateStatus(@PathVariable id: UUID, @RequestBody request: UpdateStatusRequest) {
        accountService.updateStatus(id, request.status)
    }

    @PatchMapping("/updateOnline/{id}")
    fun updateIsOnline(@PathVariable id: UUID, @RequestBody request: UpdateIsOnlineRequest) {
        accountService.updateOnline(id, request.isOnline)
    }

    @PatchMapping("/updatePassword/{id}")
    fun updatePassword(@PathVariable id: UUID, @RequestBody request: UpdatePasswordRequest) {
        accountService.updatePassword(id, request.password)
    }
}