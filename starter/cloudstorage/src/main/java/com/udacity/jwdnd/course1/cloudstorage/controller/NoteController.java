package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/{noteId}")
    @ResponseBody
    public Note getNote(@PathVariable(name = "noteId") String noteId) {
        return noteService.getNote(Integer.parseInt(noteId));
    }

    @PostMapping()
    public String postNote(@ModelAttribute("noteForm") Note note, Model model, Principal principal) {
        noteService.createNote(note, principal.getName());
        model.addAttribute("notes", noteService.getNotes(principal.getName()));
        return "home";
    }

    @DeleteMapping
    public String deleteNote(@ModelAttribute Note note, Model model, Principal principal) {
        // TODO: error handling
        noteService.deleteNote(note, principal.getName());
        return "redirect:/home";
    }
}
