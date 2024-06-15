package com.funny.service.persistence.entity

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

@Entity
@Table(name = "account")
class AccountEntity (
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID?,

    @Column(name = "login", unique = true)
    var login: String,

    @Column(name = "hashed_password")
    var hashedPassword: String,

    @Column(name = "status")
    var status: String,

    @Column(name = "online")
    var isOnline: Boolean,

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    var role: Role

) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return arrayListOf(SimpleGrantedAuthority(role.name))
    }

    override fun getPassword(): String {
        return hashedPassword
    }

    override fun getUsername(): String {
        return login
    }
}