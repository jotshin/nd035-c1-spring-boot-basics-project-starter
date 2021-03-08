package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/file")
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    public String uploadFile(@ModelAttribute MultipartFile file, Model model, Principal principal) throws IOException {
        String errorMsg = null;

        if (file.isEmpty()) {
            errorMsg = "File should not be empty!";
        }

        if (errorMsg == null) {
            // TODO: handle exception
            Integer currentFileId = this.fileService.uploadFile(file, principal.getName());
            if (currentFileId < 0) {
                errorMsg = "There was error uploading this file!";
            }
        }

        if (errorMsg == null) {
            model.addAttribute("updateSuccess", true);
        } else {
            model.addAttribute("updateFail", errorMsg);
        }

        return "result";
    }
}
