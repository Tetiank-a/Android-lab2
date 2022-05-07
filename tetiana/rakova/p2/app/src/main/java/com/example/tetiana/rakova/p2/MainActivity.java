package com.example.tetiana.rakova.p2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar seekBar1 = findViewById(R.id.red);
        SeekBar seekBar2 = findViewById(R.id.green);
        SeekBar seekBar3 = findViewById(R.id.blue);
        TextView textView = findViewById(R.id.textView1);
        int redC = seekBar1.getProgress();
        int greenC = seekBar2.getProgress();
        int blueC = seekBar3.getProgress();
        textView.setBackgroundColor(Color.rgb(redC, greenC, blueC));
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int redColor = seekBar1.getProgress();
                int greenColor = seekBar2.getProgress();
                int blueColor = seekBar3.getProgress();
                textView.setBackgroundColor(Color.rgb(redColor, greenColor, blueColor));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int redColor = seekBar1.getProgress();
                int greenColor = seekBar2.getProgress();
                int blueColor = seekBar3.getProgress();
                textView.setBackgroundColor(Color.rgb(redColor, greenColor, blueColor));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int redColor = seekBar1.getProgress();
                int greenColor = seekBar2.getProgress();
                int blueColor = seekBar3.getProgress();
                textView.setBackgroundColor(Color.rgb(redColor, greenColor, blueColor));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}