package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Integer fileId) {
        File file = fileService.getFile(fileId);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(file.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getFileName())
                .body(new ByteArrayResource(file.getFileData()));
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
