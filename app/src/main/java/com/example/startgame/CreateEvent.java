package com.example.startgame;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


/*
Activity pou dhmiourgietai to event (krathsh ghpedou) pou 8elei o xrhsths
 */

public class CreateEvent extends AppCompatActivity {

    private Button confirmBTN;
    private EditText dateET, timeET, companyET;
    private ImageView pictureIV;
    private String  pictureURL;

    static Connection con = null;

    private String mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        confirmBTN = (Button) findViewById(R.id.confirmBTN);
        dateET = (EditText)findViewById(R.id.dateET);
        timeET = (EditText)findViewById(R.id.timeET);
        companyET = (EditText) findViewById(R.id.companyET);

        pictureURL = getIntent().getStringExtra("picURL");
        pictureIV = (ImageView) findViewById(R.id.fieldIV);

        confirmBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String date = dateET.getText().toString();
                final String time = timeET.getText().toString();
                final String company = companyET.getText().toString();

                storeMail sm = (storeMail) getApplicationContext();
                mail = sm.getMail();

                new Task(mail, date, time, company).execute();

            }
        });

        //Picture
        if(pictureURL.equals("")){
            pictureURL="https://rickyrubio9.weebly.com/uploads/1/9/6/1/19612197/2503234_orig.png";
        }
        CreateEvent.LoadImage loadImage = new CreateEvent.LoadImage(pictureIV);
        loadImage.execute(pictureURL);

    }

    /*
    Klash gia syndesh me th vash.
    Sthn doInBackground kanoume th syndesh kai kanoume insert ta nea dedomena
    ston pinaka events
     */

    private class Task extends AsyncTask<Void, Void, Void> {

        String mail;
        String date;
        String time;
        String company;

        public Task(String mail, String date, String time, String company){
            super();
            this.mail = mail;
            this.date = date;
            this.time=time;
            this.company=company;
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
                rs = st.executeQuery("SELECT COUNT(event_id), mail,date,time,company from events;");
                while(rs.next()) {

                    int event_index = rs.getInt(1) + 1;

                    //instert into events
                    PreparedStatement ps = con.prepareStatement("INSERT INTO events(event_id, mail, date, time, company) VALUES(?,?,?,?,?);");
                    ps.setInt(1, event_index);
                    ps.setString(2, mail);
                    ps.setString(3, date);
                    ps.setString(4, time);
                    ps.setString(5, company);

                    int x = ps.executeUpdate();

                    //change Activity
                    Intent i = new Intent(CreateEvent.this, Reserved.class);
                    startActivity(i);
                }
            }catch(Exception e){
                e.printStackTrace();
            }

            return null;
        }
    }

    /*
    Klash gia fortwsh url eikonas
    Sthn doInBackground anoigoume ena stream kai fortwnoume thn eikona ws bitmap
     */
    class LoadImage extends AsyncTask<String, Void, Bitmap> {

        ImageView pictureIV;
        Bitmap bm = null;

        public LoadImage(ImageView pictureIV){
            this.pictureIV=pictureIV;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {

            String pictureURL = strings[0];


            try{
                InputStream is = new java.net.URL(pictureURL).openStream();
                bm = BitmapFactory.decodeStream(is);
            }catch (IOException e){
                e.printStackTrace();
            }

            return bm;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            pictureIV.setImageBitmap(bm);
        }
    }
}
