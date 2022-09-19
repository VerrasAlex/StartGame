package com.example.startgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/*
Activity pou emfanizei tis epilogew Sign in kai Sign up.
Patwntas sta antistoixa koympia phgainoume stis antisoixes o8ones
 */
public class StartingScreen extends AppCompatActivity {

    private TextView signin;
    private LinearLayout circle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen);


        circle = (LinearLayout)findViewById(R.id.circle);
        signin = (TextView)findViewById(R.id.sin);

        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(StartingScreen.this,SignUp.class);
                startActivity(it);

            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(StartingScreen.this,Signin.class);
                startActivity(it);
            }
        });
    }
}
