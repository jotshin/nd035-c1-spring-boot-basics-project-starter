package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.StorageFileNotFoundException;
import com.udacity.jwdnd.course1.cloudstorage.services.StorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.security.Principal;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final NoteService noteService;
    private final StorageService storageService;

    public HomeController(NoteService noteService, StorageService storageService) {
        this.noteService = noteService;
        this.storageService = storageService;
    }

    @GetMapping()
    public String getHomeView(Model model, Principal principal) throws IOException {
        model.addAttribute("notes", noteService.getNotes(principal.getName()));
        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileController.class,
                        "serveFile", path.getFileName().toString()).build().toUri().toString())
                .collect(Collectors.toList()));
        return "home";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
