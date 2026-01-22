package com.mynotes.repo

import com.mynotes.model.RefreshToken
import com.mynotes.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RefreshTokenRepository : JpaRepository<RefreshToken, UUID> {
    fun findByToken(token: String): RefreshToken?
    fun deleteAllByUser(user: User)
}