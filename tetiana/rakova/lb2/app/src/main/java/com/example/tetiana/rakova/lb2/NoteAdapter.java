package com.example.tetiana.rakova.lb2;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class NoteAdapter extends ArrayAdapter<Note> {
    private LayoutInflater inflater;
    private int layout;
    private ArrayList<Note> noteList;

    NoteAdapter(Context context, int resource, ArrayList<Note> notes) {
        super(context, resource, notes);
        this.noteList = notes;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Note note = noteList.get(position);

        viewHolder.nameView.setText(note.name);
        if (note.level.name == "low") {
            viewHolder.imageView.setImageResource(R.drawable.low);
        }
        if (note.level.name == "middle") {
            viewHolder.imageView.setImageResource(R.drawable.middle);
        }
        if (note.level.name == "high") {
            viewHolder.imageView.setImageResource(R.drawable.high);
        }
        viewHolder.dataView.setText(note.dateTime.toString());

        viewHolder.imgField.setImageURI(note.uri);

        return convertView;
    }
    private class ViewHolder {
        final TextView nameView;
        final ImageView imageView;
        final TextView dataView;
        final ImageView imgField;
        ViewHolder(View view){
            nameView = view.findViewById(R.id.countView);
            imageView = view.findViewById(R.id.levelIcon);
            dataView = view.findViewById(R.id.textView);
            imgField = view.findViewById(R.id.imageView);
        }
    }
}