package com.example.startgame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/*
Klash poy diaxeirizetai th leitourgia Sign in
 */

public class Signin extends AppCompatActivity {

    private EditText mailET, pwET;
    private ImageView sback;
    private Button signin;

    static Connection con = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        sback = (ImageView)findViewById(R.id.sinb);
        signin = (Button)findViewById(R.id.sin);

        mailET = (EditText) findViewById(R.id.usrusr);
        pwET = (EditText) findViewById(R.id.pswrd);


        sback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Signin.this,StartingScreen.class);
                startActivity(it);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mailET.getText().equals("") || pwET.getText().equals(""))
                    Toast.makeText(Signin.this, "Please fill in the necessary info", Toast.LENGTH_SHORT).show();
                else {

                    final String mail = mailET.getText().toString();
                    System.out.println("The mail is: " + mail);

                    final String pw = pwET.getText().toString();
                    System.out.println("The pw is: " + pw);

                    new Task(mail, pw).execute();
                }
            }
        });
    }

    /*
    Klash gia syndesh me th vash.
    Sthn doInBackground kanoume th syndesh kai eksetazoume an to mail kai o kwdikos
    yparxoun hdh sth vash. An nai tote o xrhsths mpainei sthn efarmogh
     */
    class Task extends AsyncTask<Void, Void, Void> {

        String mail;
        String pw;
        ArrayList<String> events = new ArrayList<>();

        public Task(String mail, String pw){
            super();
            this.mail=mail;
            this.pw=pw;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try{
                //connect to DB
                Class.forName("com.mysql.jdbc.Driver");
                //allakste thn ip me th dikia sas ip
                con = DriverManager.getConnection("jdbc:mysql://192.168.1.179:3306/startgame?allowPublicKeyRetrieval=true&useSSL=false", "newuser", "0000");

                //create Statement
                Statement st = null;
                st = con.createStatement();

                //create Result Set and execute query to check if there is already a user with that email
                ResultSet rs = null;
                rs = st.executeQuery("SELECT * from users where email =" + "\"" + mail + "\"" + " and password = " + "\"" + pw + "\"" + ";");
                //String passwordDB = rs.getString(3);
                try {
                    if (rs.next()) {

                        //store the Mail
                        storeMail sm = (storeMail) getApplicationContext();
                        sm.setMail(mail);

                        //change Activity
                        Intent i = new Intent(Signin.this, Home.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(Signin.this, "Wrong E-mail or Password", Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }
}
