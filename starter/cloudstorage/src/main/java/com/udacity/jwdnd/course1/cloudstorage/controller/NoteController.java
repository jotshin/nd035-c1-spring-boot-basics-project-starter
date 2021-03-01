package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping()
    public String getNote(@ModelAttribute("noteForm") NoteForm noteForm, Model model, Principal principal) {
        model.addAttribute("notes", noteService.getNotes(principal.getName()));
        return "home";
    }

    @PostMapping()
    public String postNote(@ModelAttribute("noteForm") NoteForm noteForm, Model model, Principal principal) {
        noteService.createNote(noteForm, principal.getName());
        noteForm.setNoteTitle("");
        noteForm.setNoteDescription("");
        model.addAttribute("notes", noteService.getNotes(principal.getName()));
        return "home";
    }
}
