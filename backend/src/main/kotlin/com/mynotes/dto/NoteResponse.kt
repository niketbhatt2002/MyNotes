package com.mynotes.dto

import java.time.Instant
import java.util.*

data class NoteResponse(
    val id: UUID,
    val title: String,
    val content: String,
    val createdAt: Instant
)