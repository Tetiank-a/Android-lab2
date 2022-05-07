package com.example.tetiana.rakova.lb2;

import android.graphics.Bitmap;
import android.media.Image;

public class Level {
    public String name;
    public Bitmap icon;
    public Level(String newName, Bitmap newIcon) {
        name = newName;
        icon = newIcon;
    }
}
