package com.funny.service.persistence.repo

import com.funny.service.persistence.entity.AccountEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AccountRepository : JpaRepository<AccountEntity, UUID> {
    fun findByLogin(login: String): Optional<AccountEntity>
}