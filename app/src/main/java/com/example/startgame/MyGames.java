package com.example.startgame;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


/*
Activity pou emfanizei thn o8onh paixnidiwn toy xrhsth. H o8onh perilamvanei
ta events (krathseis) pou exei dhmiourghsei o xrhsths. Parexetai h dynatothta dhmioyrgias
event me to pathma tou koumpiou + pou vrisketai katw deksia ths o8onhs.
Epipleon dinetai h epilogh panw aristera na anoiksei to navigation drawer
 */
public class MyGames extends AppCompatActivity {

    static Connection con = null;

    String mail;

    //no Events Text View
    private TextView noEventsTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_games);
        Toolbar mytoolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(mytoolbar);

        storeMail sm = (storeMail) getApplicationContext();
        mail = sm.getMail();

        DrawerBase.getDrawer(this, mytoolbar, mail);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), SelectSport.class);
                startActivity(i);
            }
        });

        new Task(mail).execute();
    }

    /*
    me8odos pou enhmerwnei to ListView pou periexei ta events tou xrhsth
     */
    public void updateLV(ArrayList<String> array){
        ArrayList<String> eventTexts = new ArrayList<String>();
        for(int i=0; i<array.size(); i+=4){
            String event = "Event " + " " + array.get(i+1) + " " + array.get(i+2) + " " + array.get(i+3);
            eventTexts.add(event);
        }


        if(eventTexts.size() != 0){
            final ListView resultsLV = (ListView) findViewById(R.id.mygamesLV);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, eventTexts);
            resultsLV.setAdapter(adapter);
        }else{
            noEventsTV = (TextView) findViewById(R.id.noGamesTV);
            noEventsTV.setText("You haven't planned any Events. \nStart now by clicking the Add button below!");
        }
    }

    /*
   Klash gia syndesh me th vash.
   Sthn doInBackground kanoume th syndesh kai pairname ta events se ena arraylist,
   me to opoio enhmerwnoume to ListView poy 8a emfanistei sthn o8onh
    */
    private class Task extends AsyncTask<Void, Void, ArrayList<String>> {

        String mail;
        ArrayList<String> events = new ArrayList<>();

        public Task(String mail) {
            super();
            this.mail=mail;
        }


        @Override
        protected ArrayList<String> doInBackground(Void... voids) {

            try {
                //connect to DB
                Class.forName("com.mysql.jdbc.Driver");
                //allakste thn ip me th dikh sas ip
                con = DriverManager.getConnection("jdbc:mysql://192.168.1.179:3306/startgame?allowPublicKeyRetrieval=true&useSSL=false", "newuser", "0000");

                //create Statement
                Statement st = null;
                st = con.createStatement();

                //create Result Set and execute query to check if there is already a user with that email
                ResultSet rs = null;
                rs = st.executeQuery("SELECT * from events where mail =" + "\"" + mail + "\"" + ";");
                try {
                    while (rs.next()) {
                        events.add(rs.getString(2));
                        events.add(rs.getString(3));
                        events.add(rs.getString(4));
                        events.add(rs.getString(5));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return events;
        }

        @Override
        protected void onPostExecute(ArrayList<String> v){
            updateLV(v);
        }
    }

}
