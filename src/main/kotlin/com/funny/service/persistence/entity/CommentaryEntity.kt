package com.funny.service.persistence.entity

import jakarta.persistence.*
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "commentary")
class CommentaryEntity (
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID?,

    @ManyToOne(fetch = FetchType.LAZY)
    var article: ArticleEntity,

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    var createdBy: AccountEntity,

    @Column(name = "body", nullable = false)
    var body: String,

    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime,

    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime
)