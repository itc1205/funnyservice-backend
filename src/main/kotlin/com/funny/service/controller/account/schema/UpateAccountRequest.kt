package com.funny.service.controller.account.schema

data class UpateAccountRequest(
    val login: String?,
    var status: String?,
    var password: String?,
    var isOnline: Boolean?,
)
