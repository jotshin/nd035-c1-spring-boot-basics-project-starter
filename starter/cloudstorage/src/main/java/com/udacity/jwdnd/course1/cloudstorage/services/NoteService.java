package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteMapper noteMapper;
    private final UserMapper userMapper;

    public NoteService(NoteMapper noteMapper, UserMapper userMapper) {
        this.noteMapper = noteMapper;
        this.userMapper = userMapper;
    }

    public int createNote(Note note, String username) {
        User user = userMapper.getUser(username);
        note.setUserId(user.getUserId());
        return noteMapper.insert(note);
    }

    public List<Note> getNotes(String username) {
        User user = userMapper.getUser(username);
        return noteMapper.getNotes(user.getUserId());
    }
}
