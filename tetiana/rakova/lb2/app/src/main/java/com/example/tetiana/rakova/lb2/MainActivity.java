package com.example.tetiana.rakova.lb2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ImageView imgField;
    TextView noteName;
    ListView listView;
    String currentNote;
    ArrayList<Note> notes = new ArrayList<Note>();


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("is loaded");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (((NotesList) this.getApplication()).lang == "eng") {
            TextView t1 = findViewById(R.id.textView);
            t1.setText("Search");

            TextView t2 = findViewById(R.id.addNote);
            t2.setText("Add new");
        } else {
            TextView t1 = findViewById(R.id.textView);
            t1.setText("Пошук");

            TextView t2 = findViewById(R.id.addNote);
            t2.setText("Додати");
        }

        Bitmap bitmap1 = BitmapFactory.decodeFile("dragon.png");

        Level level1 = new Level("low", bitmap1);
        Level level2 = new Level("low", bitmap1);
        Level level3 = new Level("low", bitmap1);



        //((NotesList) this.getApplication()).addNote(note1);
        //((NotesList) this.getApplication()).addNote(note2);



        // System.out.println(((NotesList) this.getApplication()).getNotes());
        notes = ((NotesList) this.getApplication()).getNotes();


        ListView productList = findViewById(R.id.middleBar);
        NoteAdapter adapter = new NoteAdapter(this, R.layout.list_item, notes);
        productList.setAdapter(adapter);


        listView = findViewById(R.id.middleBar);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = ((TextView) view.findViewById(R.id.countView)).getText().toString();
                Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT).show();
                currentNote = item;
                return false;
            }
        });

        registerForContextMenu(listView);


    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        // you can set menu header with title icon etc
        if (v == findViewById(R.id.imageButton2)) {
            menu.setHeaderTitle("Importance level");
            // add menu items
            menu.add(0, v.getId(), 0, "Low");
            menu.add(0, v.getId(), 0, "Middle");
            menu.add(0, v.getId(), 0, "High");
        } else {
            menu.setHeaderTitle("Action");
            // add menu items
            menu.add(0, v.getId(), 0, "Edit");
            menu.add(0, v.getId(), 0, "Delete");
        }
    }

    // menu item select listener
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getTitle() == "Edit") {
            Toast.makeText(getApplicationContext(), "edit", Toast.LENGTH_SHORT).show();
            // Selecting a note to be edited
            ((NotesList) this.getApplication()).setCurrentNoteName(currentNote);
            Intent intent = new Intent(this, NewNoteActivity.class);
            startActivity(intent);
        } else if (item.getTitle() == "Delete") {
            ((NotesList) this.getApplication()).setCurrentNoteName(currentNote);
            ((NotesList) this.getApplication()).deleteNote(currentNote);
            Toast.makeText(getApplicationContext(), "delete", Toast.LENGTH_SHORT).show();
            notes = ((NotesList) this.getApplication()).getNotes();
            currentNote = null;
            ((NotesList) this.getApplication()).setCurrentNoteName(null);
            ListView productList = findViewById(R.id.middleBar);
            NoteAdapter adapter = new NoteAdapter(this, R.layout.list_item, notes);
            productList.setAdapter(adapter);
            return true;
        }
        return true;
    }

    public void changeLocEng(View view){

        ((NotesList) this.getApplication()).lang = "eng";
        TextView t1 = findViewById(R.id.textView);
        t1.setText("Search");

        TextView t2 = findViewById(R.id.addNote);
        t2.setText("Add new");

    }

    public void changeLocUkr(View view){

        ((NotesList) this.getApplication()).lang = "ukr";
        TextView t1 = findViewById(R.id.textView);
        t1.setText("Пошук");

        TextView t2 = findViewById(R.id.addNote);
        t2.setText("Додати");

    }

    public void addNote(View view){
        ((NotesList) this.getApplication()).setCurrentNoteName(null);
        Intent intent = new Intent(this, NewNoteActivity.class);
        startActivity(intent);
    }

    static boolean twoStrings(String s1, String s2)
    {
        boolean v[]=new boolean[26];
        Arrays.fill(v,false);

        for (int i = 0; i < s1.length(); i++)
            v[s1.charAt(i) - 'a'] = true;

        for (int i = 0; i < s2.length(); i++)
            if (v[s2.charAt(i) - 'a'])
                return true;

        return false;
    }

    public void applyFilter(View view){

        notes = ((NotesList) this.getApplication()).getNotes();
        CheckBox ch1 = findViewById(R.id.checkBox1);
        CheckBox ch2 = findViewById(R.id.checkBox2);
        CheckBox ch3 = findViewById(R.id.checkBox3);
        EditText t1 = findViewById(R.id.editTextTextPersonName);
        ArrayList<Note> notes1 = new ArrayList<Note>();
        for (int i = 0; i < notes.size(); ++i) {
            String s1 = notes.get(i).name;
            String s2 = t1.getText().toString();
            if (notes.get(i).level.name == "low" && ch1.isChecked() && s1.startsWith(s2)) {
                notes1.add(notes.get(i));
            }
            if (notes.get(i).level.name == "middle" && ch2.isChecked() && s1.startsWith(s2)) {
                notes1.add(notes.get(i));
            }
            if (notes.get(i).level.name == "high" && ch3.isChecked() && s1.startsWith(s2)) {
                notes1.add(notes.get(i));
            }
        }

        ListView productList = findViewById(R.id.middleBar);
        NoteAdapter adapter = new NoteAdapter(this, R.layout.list_item, notes1);
        productList.setAdapter(adapter);
    }
}