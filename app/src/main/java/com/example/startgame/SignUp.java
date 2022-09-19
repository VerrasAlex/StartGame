package com.example.startgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
Klash pou diaxeirizetai th dhmiorgia logariasmou neou xrhsth.
O xrhsths symplhrwnei ta stoixeia tou kai ayta stelnontai sth vash
 */
public class SignUp extends AppCompatActivity {

    private EditText fnET, unET,mailET, pwET;
    private Button createa;
    private ImageView sback;

    static Connection con = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        sback = (ImageView)findViewById(R.id.sback);
        sback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(SignUp.this,StartingScreen.class);
                startActivity(it);
            }
        });

        fnET = (EditText) findViewById(R.id.fname);
        unET = (EditText) findViewById(R.id.usrusr);
        mailET = (EditText) findViewById(R.id.mail);
        pwET = (EditText) findViewById(R.id.pswrd);

        createa = (Button) findViewById(R.id.sup);

        createa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (unET.getText().equals("") || pwET.getText().equals("") || fnET.getText().equals("") || mailET.getText().equals("")) {
                    Toast.makeText(SignUp.this, "Please fill the all the above fields", Toast.LENGTH_SHORT).show();
                } else {
                    final String fn = fnET.getText().toString();
                    System.out.println("The fn is: " + fn);

                    final String  un = unET.getText().toString();
                    System.out.println("The fn is: " + un);

                    final String mail = mailET.getText().toString();
                    System.out.println("The mail is: " + mail);

                    final String pw = pwET.getText().toString();
                    System.out.println("The pw is: " + pw);

                    new Task(mail, un, pw, fn).execute();
                }
            }
        });
    }

    /*
   Klash gia syndesh me th vash.
   Sthn doInBackground kanoume th syndesh kai kanoume insert ta nea dedomena
   ston pinaka users
    */
    class Task extends AsyncTask<Void, Void, Void> {

        String mail;
        String un;
        String pw;
        String fn;

        public Task(String mail, String un, String pw, String fn){
            super();
            this.mail=mail;
            this.un=un;
            this.pw=pw;
            this.fn=fn;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try{
                //connect to DB
                Class.forName("com.mysql.jdbc.Driver");
                //allakste thn ip me th dikh sas ip
                con = DriverManager.getConnection("jdbc:mysql://192.168.1.179:3306/startgame?allowPublicKeyRetrieval=true&useSSL=false", "newuser", "0000");

                //create Statement
                Statement st = null;
                st = con.createStatement();

                //create Result Set and execute query to check if there is already a user with that email
                ResultSet rs = null;
                rs = st.executeQuery("SELECT * from users where email =" + "\"" + mail + "\"" + ";");
                if (rs.next()) {
                    System.out.println("You already have an account");
                } else {
                    //instert into DB
                    PreparedStatement ps = con.prepareStatement("INSERT INTO users(email, username, password, fullname) VALUES(?,?,?,?);");
                    ps.setString(1, mail);
                    ps.setString(2, un);
                    ps.setString(3, pw);
                    ps.setString(4, fn);

                    int x = ps.executeUpdate();

                    //store the Mail
                    storeMail sm = (storeMail) getApplicationContext();
                    sm.setMail(mail);

                    //change Activity
                    Intent i = new Intent(SignUp.this, Home.class);
                    startActivity(i);
                }
            }catch(Exception e){
                e.printStackTrace();
            }

            return null;
        }
    }

}
