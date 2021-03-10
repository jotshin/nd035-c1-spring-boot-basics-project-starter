package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/credential")
public class CredentialController {
    private CredentialService credentialService;

    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping
    public String postCredential(@ModelAttribute Credential credential, Principal principal, Model model) {
        String errorMsg = null;

        if (credential.getUrl().isEmpty()) {
            errorMsg = "URL shouldn't be empty!";
        }

        if (credential.getUsername().isEmpty()) {
            errorMsg = "Username shouldn't be empty!";
        }

        if (credential.getPassword().isEmpty()) {
            errorMsg = "Password shouldn't be empty!";
        }

        if (errorMsg == null) {
            Integer currentCredentialId = credentialService.createCredential(credential, principal.getName());

            if (currentCredentialId < 0) {
                errorMsg = "There was error adding new credential!";
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
