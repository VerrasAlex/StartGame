package com.example.startgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
/*
Activity pou o xrhsths 8a epileksei gia poio a8lhma 8elei na dhmiourghsei event
 */
public class SelectSport extends AppCompatActivity {

    private ImageButton buttonBasketball;
    private ImageButton buttonSoccer;
    private Toolbar myToolbar;
    private String mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_sport);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        buttonBasketball = (ImageButton)findViewById(R.id.buttonBasketball);
        buttonSoccer = (ImageButton)findViewById(R.id.buttonSoccer);
        mail = getIntent().getStringExtra("mail");

        buttonBasketball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), FiltersBasketball.class);
                i.putExtra("mail", mail);
                startActivity(i);
            }
        });

        buttonSoccer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Filters.class);
                i.putExtra("mail", mail);
                startActivity(i);
            }
        });
    }
}
