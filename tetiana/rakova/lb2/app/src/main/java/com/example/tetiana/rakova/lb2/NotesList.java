package com.example.tetiana.rakova.lb2;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class NotesList extends Application{
    public ArrayList<Note> notes = new ArrayList<Note>();
    // if newName is null, then the current note is new
    private String currentNoteName;

    private Uri imageUri;

    public String lang = "eng";

    public String getCurrentNoteName() {
        return currentNoteName;
    }

    public void setCurrentNoteName(String newName) {
        currentNoteName = newName;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Note getNote(String noteName) {
        // case if the note is new
        if (noteName == null) {
            Bitmap bitmap1 = BitmapFactory.decodeFile("dragon.png");
            Note emptyNote = new Note("", "", new Level("low", bitmap1),
                    LocalDateTime.now(), bitmap1, "", imageUri);
            return emptyNote;
        }
        for (int i = 0; i < notes.size(); ++i) {
            if (notes.get(i).name == noteName) {
                return notes.get(i);
            }
        }
        return null;
    }

    public void deleteNote(String noteName) {
        // case if the note is new
        if (noteName == null) {
            return;
        }
        for (int i = 0; i < notes.size(); ++i) {
            if (notes.get(i).name == noteName) {
                notes.remove(i);
                return;
            }
        }
        return;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean addNote(Note newNote) {
        if (getNote(newNote.name) == null) {
            notes.add(newNote);
        }
        // Note with such name already exists
        return false;
    }

    public void updateNote(Note newNote) {
        for (int i = 0; i < notes.size(); ++i) {
            if (notes.get(i).name == currentNoteName) {
                notes.remove(i);
                notes.add(newNote);
                return;
            }
        }
        notes.add(newNote);
    }
}
