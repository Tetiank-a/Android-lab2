package com.example.tetiana.rakova.lb2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class NewNoteActivity extends AppCompatActivity {

    //Level[] levels;
    String[] levels = {"low", "middle", "high"};
    ImageView imgField;
    TextView editText;
    EditText nameId;
    EditText editTextTextPersonName;
    Spinner levelId;
    EditText textD;
    ArrayList<Note> notes;
    String currentNote;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    @Override
    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        if (((NotesList) this.getApplication()).lang == "eng") {
            TextView t1 = findViewById(R.id.name);
            t1.setText("Name");

            TextView t2 = findViewById(R.id.addNote);
            t2.setText("Description");

            TextView t3 = findViewById(R.id.level);
            t3.setText("Importance");

            TextView t4 = findViewById(R.id.text);
            t4.setText("Text");

            TextView t5 = findViewById(R.id.button);
            t5.setText("SAVE NOTE");
        } else {
            TextView t1 = findViewById(R.id.name);
            t1.setText("Назва");

            TextView t2 = findViewById(R.id.addNote);
            t2.setText("Опис");

            TextView t3 = findViewById(R.id.level);
            t3.setText("Важливість");

            TextView t4 = findViewById(R.id.text);
            t4.setText("Текст");

            TextView t5 = findViewById(R.id.button);
            t5.setText("ЗБЕРЕГТИ");
        }

        notes = ((NotesList) this.getApplication()).getNotes();
        currentNote = ((NotesList) this.getApplication()).getCurrentNoteName();


        Bitmap bitmap1 = BitmapFactory.decodeFile("i.png");

        editText = findViewById(R.id.textId);
        editText.setImeOptions(EditorInfo.IME_ACTION_DONE);
        editText.setRawInputType(InputType.TYPE_CLASS_TEXT);

        //levels[1] = new Level("low", bitmap1);
        //levels[2] = new Level("middle", bitmap1);
        //levels[3] = new Level("high", bitmap1);

        Note noteX = ((NotesList) this.getApplication()).getNote(currentNote);

        if (noteX.name != "") {
            imgField = findViewById(R.id.addIcon);
            imgField.setImageURI(noteX.uri);
            imageUri = noteX.uri;
        }

        nameId = findViewById(R.id.nameId);
        nameId.setText(currentNote);

        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        editTextTextPersonName.setText(noteX.description);

        Spinner spinner = findViewById(R.id.levelId);
        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, levels);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        if (noteX.level.name == "low") {
            spinner.setSelection(0);
        }
        if (noteX.level.name == "middle") {
            spinner.setSelection(1);
        }
        if (noteX.level.name == "high") {
            spinner.setSelection(2);
        }

        textD = findViewById(R.id.textId);
        textD.setText(noteX.text);
    }

    public void addIcon(View view){
        imgField = findViewById(R.id.addIcon);
        imgField.setImageResource(R.drawable.dragon);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void saveNote(View view){
        Spinner lvl = findViewById(R.id.levelId);
        String lvlName = lvl.getSelectedItem().toString();
        Bitmap bitmap1 = BitmapFactory.decodeFile(imageUri.getPath());

        Level levelX = new Level("low", bitmap1);
        if (lvlName == "middle") {
            levelX = new Level("middle", bitmap1);
        }
        if (lvlName == "high") {
            levelX = new Level("high", bitmap1);
        }

        Note updatedNote = new Note(nameId.getText().toString(),
                editTextTextPersonName.getText().toString(), levelX,
                LocalDateTime.now(), bitmap1, textD.getText().toString(), imageUri);

        ((NotesList) this.getApplication()).updateNote(updatedNote);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openGallery(View view) {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        imgField = findViewById(R.id.addIcon);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            imgField.setImageURI(imageUri);
        }
    }
}