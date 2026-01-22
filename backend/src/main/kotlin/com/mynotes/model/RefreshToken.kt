package com.mynotes.model

import jakarta.persistence.*
import java.time.Instant
import java.util.*

@Entity
@Table(name = "refresh_tokens")
data class RefreshToken(
    @Id @GeneratedValue val id: UUID? = null,
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id") var user: User? = null,
    @Column(nullable = false) var token: String,
    @Column(name = "expires_at") var expiresAt: Instant
)