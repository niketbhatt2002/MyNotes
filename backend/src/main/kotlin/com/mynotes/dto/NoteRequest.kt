package com.mynotes.dto

import jakarta.validation.constraints.NotBlank

data class NoteRequest(
    @field:NotBlank
    val title: String,

    val content: String = ""
)