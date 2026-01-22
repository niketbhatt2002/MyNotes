package com.mynotes.controller

import com.mynotes.dto.NoteRequest
import com.mynotes.dto.NoteResponse
import com.mynotes.repo.UserRepository
import com.mynotes.service.NoteService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/notes")
class NoteController(
    private val noteService: NoteService,
    private val userRepository: UserRepository
) {

    @GetMapping
    fun list(@AuthenticationPrincipal principal: UserDetails): List<NoteResponse> {
        val user = userRepository.findByUsername(principal.username)!!
        return noteService.listForUser(user)
    }

    @PostMapping
    fun create(@AuthenticationPrincipal principal: UserDetails, @Valid @RequestBody req: NoteRequest): ResponseEntity<NoteResponse> {
        val user = userRepository.findByUsername(principal.username)!!
        val created = noteService.create(user, req)
        return ResponseEntity.ok(created)
    }

    @PutMapping("/{id}")
    fun update(@AuthenticationPrincipal principal: UserDetails, @PathVariable id: UUID, @Valid @RequestBody req: NoteRequest): ResponseEntity<NoteResponse> {
        val user = userRepository.findByUsername(principal.username)!!
        val updated = noteService.update(user, id, req)
        return ResponseEntity.ok(updated)
    }

    @DeleteMapping("/{id}")
    fun delete(@AuthenticationPrincipal principal: UserDetails, @PathVariable id: UUID): ResponseEntity<Any> {
        val user = userRepository.findByUsername(principal.username)!!
        noteService.delete(user, id)
        return ResponseEntity.noContent().build()
    }
}