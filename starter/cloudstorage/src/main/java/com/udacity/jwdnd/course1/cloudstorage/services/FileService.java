package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {
    private FileMapper fileMapper;
    private UserMapper userMapper;

    public FileService(FileMapper fileMapper, UserMapper userMapper) {
        this.fileMapper = fileMapper;
        this.userMapper = userMapper;
    }

    public List<File> getAllFiles(String username) {
        User user = userMapper.getUser(username);
        return this.fileMapper.getFiles(user.getUserId());
    }

    public Integer uploadFile(MultipartFile mFile, String username) throws IOException {
        User user = userMapper.getUser(username);
        File file = new File(null, mFile.getOriginalFilename(),
                mFile.getContentType(), mFile.getSize(), user.getUserId(), mFile.getBytes());

        return this.fileMapper.uploadFile(file);
    }
}
