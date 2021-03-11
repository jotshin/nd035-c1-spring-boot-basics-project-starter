package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {
    private CredentialMapper credentialMapper;
    private UserService userService;
    private EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, UserService userService, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.userService = userService;
        this.encryptionService = encryptionService;
    }

    public List<Credential> getAllCredentials(String username) {
        User user = userService.getUser(username);
        return credentialMapper.getCredentials(user.getUserId());
    }

    public Integer createCredential(Credential credential, String username) {
        User user = userService.getUser(username);
        credential.setUserId(user.getUserId());
        credential.setKey(encodedKey());
        credential.setPassword(encryptedPassword(credential.getPassword(), credential.getKey()));
        return credentialMapper.insert(credential);
    }

    private String encryptedPassword(String password, String encodedKey) {
        return encryptionService.encryptValue(password, encodedKey);
    }

    private String encodedKey() {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }
}
