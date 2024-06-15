package com.funny.service.persistence.entity

import jakarta.persistence.*
import org.hibernate.annotations.BatchSize
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "article")
class ArticleEntity(
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID?,

    @Column(name = "title")
    var title: String,

    @Column(name = "body")
    var body: String,

    @Column(name = "slug")
    var slug: String,

    @ManyToMany(fetch = FetchType.LAZY)
    @BatchSize(size = 50)
    var tags: MutableSet<TagEntity>,

    @OneToMany(fetch = FetchType.LAZY)
    @BatchSize(size = 50)
    var commentaries: MutableSet<CommentaryEntity>,

    @ManyToOne(fetch = FetchType.LAZY)
    var createdBy: AccountEntity,

    @Column(name = "created_at")
    var createdAt: LocalDateTime,

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime,
)
