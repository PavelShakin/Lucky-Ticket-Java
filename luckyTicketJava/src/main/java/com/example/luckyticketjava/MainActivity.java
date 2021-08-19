package com.example.luckyticketjava;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private static final int NUMBER_OF_DIGITS = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("UseCompatLoadingForDrawables")
    public void onClick(View view) {
        EditText text = findViewById(R.id.etTicketNumber);
        String input = text.getText().toString();
        ImageView bulb = findViewById(R.id.imgBulb);
        Drawable bulbGreen = getDrawable(getResources()
                .getIdentifier("@drawable/ic_bulbgreen", null, getPackageName()));
        Drawable bulbRed = getDrawable(getResources()
                .getIdentifier("@drawable/ic_bulbred", null, getPackageName()));

        if (input.length() == NUMBER_OF_DIGITS) {
            try {
                String[] str = Arrays.copyOfRange(input.split(""),
                                                  1,
                                                  input.split("").length);
                int[] result = Arrays.stream(str).mapToInt(Integer::parseInt).toArray();
                if (result[0] + result[1] + result[2] == result[3] + result[4] + result[5]) {
                    bulb.setImageDrawable(bulbGreen);
                } else {
                    bulb.setImageDrawable(bulbRed);
                }
            } catch (NumberFormatException ex) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "You need to enter only numbers",
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "You mush enter 6 numbers",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}