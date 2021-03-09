package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {
    private FileMapper fileMapper;
    private UserService userService;

    public FileService(FileMapper fileMapper, UserService userService) {
        this.fileMapper = fileMapper;
        this.userService = userService;
    }

    public boolean isFileNameAvailable(String username, String fileName) {
        User user = userService.getUser(username);
        return fileMapper.getDuplicatedFiles(user.getUserId(), fileName).isEmpty();
    }

    public File getFile(Integer fileId) {
        return fileMapper.getFile(fileId);
    }

    public List<File> getAllFiles(String username) {
        User user = userService.getUser(username);
        return this.fileMapper.getFiles(user.getUserId());
    }

    public Integer uploadFile(MultipartFile mFile, String username) throws IOException {
        User user = userService.getUser(username);
        File file = new File(null, mFile.getOriginalFilename(),
                mFile.getContentType(), mFile.getSize(), user.getUserId(), mFile.getBytes());

        return this.fileMapper.uploadFile(file);
    }

    public Integer deleteFile(File file, String username) {
        User user = userService.getUser(username);
        file.setUserId(user.getUserId());
        return fileMapper.deleteFile(file.getFileId());
    }
}
