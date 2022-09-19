package com.example.startgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*
Aplo Activity pou emfanizei thn o8onh oti egine epituxhs krathsh ghpedou
 */
public class Reserved extends AppCompatActivity {

    private Button startBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserved);

        startBTN = (Button) findViewById(R.id.confirmBTN);

        startBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Home.class);
                startActivity(i);
            }
        });

    }
}
