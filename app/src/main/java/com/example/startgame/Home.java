package com.example.startgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/*
Activity pou emfanizei thn arxikh o8onh. H arxikh o8onh perilamvanei
kapoia apo ta events pou yparxoun sth vash. Dhladh parousiazontai sto xrhsth events pou mporei
na ton endiaferoun. Epipleon dinetai h epilogh panw aristera na anoiksei to navigation drawer
 */

public class Home extends AppCompatActivity {

    private Toolbar myToolbar;
    static  Connection con = null;
    private String mail;
    //no Events Text View
    private TextView noEventsTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        storeMail sm = (storeMail) getApplicationContext();
        mail = sm.getMail();

        DrawerBase.getDrawer(this, myToolbar, mail);

        new Task().execute();
    }

    /*
    me8odos pou enhmerwnei to ListView pou periexei ta events
     */
    public void updateLV(ArrayList<String> array){
        ArrayList<String> eventTexts = new ArrayList<String>();
        for(int i=0; i<array.size(); i+=4){
            String event = "Event " + " " + array.get(i+1) + " " + array.get(i+2) + " " + array.get(i+3);
            System.out.println(event);
            eventTexts.add(event);
        }

        if(eventTexts.size() != 0){
            final ListView resultsLV = (ListView) findViewById(R.id.mygamesLV);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, eventTexts);
            resultsLV.setAdapter(adapter);
        }else{
            noEventsTV = (TextView) findViewById(R.id.noEventsTV);
            noEventsTV.setText("There are no current Events");
        }
    }

    /*
    Klash gia syndesh me th vash.
    Sthn doInBackground kanoume th syndesh kai pairname ta events se ena arraylist,
    me to opoio enhmerwnoume to ListView poy 8a emfanistei sthn o8onh
     */
    private class Task extends AsyncTask<Void, Void, ArrayList<String>> {

        String mail;
        String pw;
        ArrayList<String> events = new ArrayList<>();

        public Task() {
            super();
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
                rs = st.executeQuery("SELECT * from events;");
                //events = new ArrayList<String>();
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