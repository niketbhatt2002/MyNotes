package com.mynotes.service

import com.mynotes.dto.NoteRequest
import com.mynotes.dto.NoteResponse
import com.mynotes.model.Note
import com.mynotes.model.User
import com.mynotes.repo.NoteRepository
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*

@Service
class NoteService(private val noteRepository: NoteRepository) {

    fun listForUser(user: User): List<NoteResponse> =
        noteRepository.findAllByUser(user).map { toResponse(it) }

    fun create(user: User, req: NoteRequest): NoteResponse {
        val note = Note(
            title = req.title,
            content = req.content,
            user = user
        )
        val saved = noteRepository.save(note)
        return toResponse(saved)
    }

    fun update(user: User, id: UUID, req: NoteRequest): NoteResponse {
        val note = noteRepository.findById(id).orElseThrow { RuntimeException("note_not_found") }
        if (note.user?.id != user.id) throw RuntimeException("forbidden")
        note.title = req.title
        note.content = req.content
        val saved = noteRepository.save(note)
        return toResponse(saved)
    }

    fun delete(user: User, id: UUID) {
        val note = noteRepository.findById(id).orElseThrow { RuntimeException("note_not_found") }
        if (note.user?.id != user.id) throw RuntimeException("forbidden")
        noteRepository.delete(note)
    }

    private fun toResponse(n: Note) = NoteResponse(id = n.id!!, title = n.title, content = n.content, createdAt = n.createdAt)
}