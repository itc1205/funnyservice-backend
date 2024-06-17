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

    @Lob
    @Column(name = "body")
    var body: String,

    @Column(name = "slug")
    var slug: String,

    @ManyToMany(fetch = FetchType.LAZY)
    @BatchSize(size = 50)
    @JoinTable(name = "article_tag", joinColumns = [JoinColumn(name="article_id")], inverseJoinColumns = [JoinColumn(name="tag_id")])
    var tags: MutableList<TagEntity>,

    @OneToMany(fetch = FetchType.LAZY)
    @BatchSize(size = 50)
    @JoinTable(name = "article_commentary", joinColumns = [JoinColumn(name="article_id")], inverseJoinColumns = [JoinColumn(name="commentary_id")])
    var commentaries: MutableList<CommentaryEntity>,

    @ManyToOne(fetch = FetchType.LAZY)
    var createdBy: AccountEntity,

    @Column(name = "created_at")
    var createdAt: LocalDateTime,

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime,
)
