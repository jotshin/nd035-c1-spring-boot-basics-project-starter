package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.security.Principal;

@Controller
@RequestMapping("/credential")
public class CredentialController {
    private CredentialService credentialService;

    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @GetMapping("{credentialId}")
    @ResponseBody
    public ResponseEntity<Credential> getCredential(@PathVariable(name = "credentialId") Integer credentialId, Principal principal) throws AuthenticationException {
        try {
            return new ResponseEntity<>(credentialService.getCredential(credentialId, principal.getName()), HttpStatus.OK);
        } catch (Exception e) {
            if (e.getClass().isAssignableFrom(AuthenticationException.class)) {
                throw e;
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
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

    @PutMapping
    public String updateCredential(@ModelAttribute Credential credential, Model model, Principal principal) throws AuthenticationException {
        Integer credentialChanged = credentialService.updateCredential(credential, principal.getName());

        if (credentialChanged > 0) {
            model.addAttribute("updateSuccess", true);
        } else {
            model.addAttribute("updateFail", "There was error updating the credential!");
        }

        return "result";
    }

    @DeleteMapping
    public String deleteCredential(@ModelAttribute Credential credential, Model model, Principal principal) throws AuthenticationException {

        Integer noteDeleted = credentialService.deleteCredential(credential, principal.getName());

        if (noteDeleted > 0) {
            model.addAttribute("updateSuccess", true);
        } else {
            model.addAttribute("updateFail", "There was error deleting the credential!");
        }

        return "result";
    }
}
