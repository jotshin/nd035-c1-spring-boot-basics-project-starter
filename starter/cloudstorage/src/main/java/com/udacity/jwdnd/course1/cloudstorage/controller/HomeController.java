package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.dto.FileDTO;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final NoteService noteService;
    private final FileService fileService;
    private final CredentialService credentialService;

    public HomeController(NoteService noteService, FileService fileService, CredentialService credentialService) {
        this.noteService = noteService;
        this.fileService = fileService;
        this.credentialService = credentialService;
    }

    @GetMapping()
    public String getHomeView(Model model, Principal principal) throws IOException {
        String username = principal.getName();
        model.addAttribute("notes", noteService.getAllNotes(username));
        model.addAttribute("files", fileService.getAllFiles(username));
        model.addAttribute("credentials", credentialService.getAllCredentials(username));
        return "home";
    }

    @ModelAttribute("fileDTO")
    public FileDTO getFileDTO() {
        return new FileDTO();
    }
}
