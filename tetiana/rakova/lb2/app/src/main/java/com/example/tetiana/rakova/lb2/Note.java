package com.example.tetiana.rakova.lb2;

import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;

import java.time.LocalDateTime;

public class Note {
    public String name;
    public String description;
    public Level level;
    public LocalDateTime dateTime;
    public Bitmap icon;
    public String text;
    public Uri uri;


    public Note (String newName, String newDescription, Level newLevel, LocalDateTime newDateTime,
                 Bitmap newIcon, String newText, Uri urix) {
        name = newName;
        description = newDescription;
        level = newLevel;
        dateTime = newDateTime;
        icon = newIcon;
        text = newText;
        uri = urix;
    }


}
