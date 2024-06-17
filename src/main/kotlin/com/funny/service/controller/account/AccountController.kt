package com.funny.service.controller.account

import com.funny.service.controller.account.schema.*
import com.funny.service.persistence.entity.Role
import com.funny.service.service.account.AccountService
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/api/v1/account")
class AccountController (
    private val accountService: AccountService
) {

    @PatchMapping("/updateStatus")
    fun updateStatus(principal: Principal, @RequestBody request: UpdateStatusRequest) {
        val accountId = accountService.getByLogin(principal.name).id!!
        accountService.updateStatus(accountId, request.status)
    }

    @PatchMapping("/updateOnline")
    fun updateIsOnline(principal: Principal, @RequestBody request: UpdateIsOnlineRequest) {
        val accountId = accountService.getByLogin(principal.name).id!!
        accountService.updateOnline(accountId, request.isOnline)
    }

    @PatchMapping("/updatePassword")
    fun updatePassword(principal: Principal, @RequestBody request: UpdatePasswordRequest) {
        val accountId = accountService.getByLogin(principal.name).id!!
        accountService.updatePassword(accountId, request.password)
    }

    @GetMapping("/me")
    fun getMe(principal: Principal) : AccountResponse {
        return accountService.getByLogin(principal.name).toAccountResponse()
    }

    @PatchMapping("/becomeAdmin")
    fun becomeAdmin(principal: Principal) {
        val accountId = accountService.getByLogin(principal.name).id!!
        return accountService.updateRole(accountId, Role.ROLE_ADMIN)
    }
}