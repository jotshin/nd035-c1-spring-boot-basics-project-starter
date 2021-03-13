package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.List;

@Service
public class NoteService {
    private final NoteMapper noteMapper;
    private final UserService userService;

    public NoteService(NoteMapper noteMapper, UserService userService) {
        this.noteMapper = noteMapper;
        this.userService = userService;
    }

    public Integer createNote(Note note, String username) {
        User user = userService.getUser(username);
        note.setUserId(user.getUserId());
        return noteMapper.insert(note);
    }

    public Integer updateNote(Note note, String username) throws AuthenticationException {
        User user = userService.getUser(username);
        Note existingNote = noteMapper.getNote(note.getNoteId());
        if (user.getUserId() != existingNote.getUserId()) {
            throw new AuthenticationException("You do not have access to this file.");
        }
        return noteMapper.updateNote(note);
    }

    public Integer deleteNote(Note note, String username) throws AuthenticationException {
        User user = userService.getUser(username);
        Note existingNote = noteMapper.getNote(note.getNoteId());
        if (user.getUserId() != existingNote.getUserId()) {
            throw new AuthenticationException("You do not have access to this file.");
        }
        return noteMapper.deleteNote(note.getNoteId());
    }

    public List<Note> getAllNotes(String username) {
        User user = userService.getUser(username);
        return noteMapper.getNotes(user.getUserId());
    }
}
