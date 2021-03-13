package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.security.Principal;

@Controller
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public String postNote(@ModelAttribute Note note, Principal principal, Model model) {
        Integer currentNoteId = noteService.createNote(note, principal.getName());

        if (currentNoteId > 0) {
            model.addAttribute("updateSuccess", true);
        } else {
            model.addAttribute("updateFail", "There was error adding new note!");
        }

        return "result";
    }

    @PutMapping
    public String updateNote(@ModelAttribute Note note, Model model, Principal principal) throws AuthenticationException {
        Integer noteChanged = noteService.updateNote(note, principal.getName());

        if (noteChanged > 0) {
            model.addAttribute("updateSuccess", true);
        } else {
            model.addAttribute("updateFail", "There was error updating the note!");
        }

        return "result";
    }

    @DeleteMapping
    public String deleteNote(@ModelAttribute Note note, Model model, Principal principal) throws AuthenticationException {

        Integer noteDeleted = noteService.deleteNote(note, principal.getName());

        if (noteDeleted > 0) {
            model.addAttribute("updateSuccess", true);
        } else {
            model.addAttribute("updateFail", "There was error deleting the note!");
        }

        return "result";
    }
}
