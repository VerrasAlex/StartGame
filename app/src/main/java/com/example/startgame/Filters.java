package com.example.startgame;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/*
Activity filtrwn gia ghpeda podosfairou. O xrhsths mporei na filtrarei ta ghpeda
wste na vriskei pio eukola kai grhgora auto pou tou aresei.
Oi epiloges anazhthshs einai na pskasei me onoma h na psaksei me diafora filtra
pou aforoun to kostos, th xwrhtikothta atomwn kai alla
 */
public class Filters extends AppCompatActivity {

    //private SearchView searchView;
    private EditText searchET;

    //Image Buttons
    private ImageButton searchBTN;
    private ImageButton searchBTN_Filters;

    //Radio Groups
    private RadioGroup capacityRG;
    private RadioGroup cityRG;
    private RadioGroup costRG;

    //Radio Buttons
    private RadioButton capacityRB;
    private RadioButton cityRB;
    private RadioButton costRB;

    private String mail;
    //Connection
    static Connection con = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        mail = getIntent().getStringExtra("mail");

        searchBTN = (ImageButton) findViewById(R.id.searchBTN);
        searchBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchET = (EditText) findViewById(R.id.searchET);
                String searchText = searchET.getText().toString();

                if(searchText.equals("")){
                    Toast.makeText(Filters.this, "Please fill in the field name", Toast.LENGTH_SHORT).show();
                }else{
                    searchText="%"+searchText+"%";
                    new Task_Search(searchText,mail).execute();
                }
            }
        });

        searchBTN_Filters = (ImageButton)findViewById(R.id.searchButtonS);
        searchBTN_Filters.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //boolean checked = ((RadioButton) v).isChecked();
                capacityRG = (RadioGroup) findViewById(R.id.capacityRG);
                cityRG = (RadioGroup) findViewById(R.id.cityRG);
                costRG = (RadioGroup) findViewById(R.id.costRG);

                if ((capacityRG.getCheckedRadioButtonId()==-1 || cityRG.getCheckedRadioButtonId()==-1 || costRG.getCheckedRadioButtonId()==-1)){
                    Toast.makeText(Filters.this, "Please fill in the necessary filters", Toast.LENGTH_SHORT).show();
                    System.out.println("Please fill in the necessary filters");
                }else {
                    //getCapacity
                    int capacity = capacityRG.getCheckedRadioButtonId();
                    capacityRB = (RadioButton) findViewById(capacity);
                    String capacityText = (String) capacityRB.getText();
                    //System.out.println("--"+capacityText+"--");

                    String capacityFinal;
                    if(capacityText.equals("5x5")){
                        capacityFinal="5";
                    }else if(capacityText.equals("7x7")){
                        capacityFinal="7";
                    }else if(capacityText.equals("8x8")){
                        capacityFinal="8";
                    }else {
                        capacityFinal="10";
                    }


                    //getCity
                    int city = cityRG.getCheckedRadioButtonId();
                    cityRB = (RadioButton) findViewById(city);
                    String cityFinal = (String) cityRB.getText();
                    //System.out.println(cityFinal);

                    //getCost
                    int cost = costRG.getCheckedRadioButtonId();
                    costRB = (RadioButton) findViewById(cost);
                    String costText = (String) costRB.getText();
                    //System.out.println(costText);

                    new Task_Filters(capacityFinal, cityFinal, costText,mail).execute();
                }
            }
        });

    }

    /*
   Klash gia syndesh me th vash.
   Sthn doInBackground anazhtoume ghpeda me vash ta filtra pou epelekse o xrhsths
    */
    class Task_Filters extends AsyncTask<Void,Void,Void> {

        String capacity;
        String city;
        String cost;
        String mail;


        public Task_Filters(String capacity, String city, String cost, String mail){
            super();
            this.capacity=capacity;
            this.city=city;
            this.cost=cost;
            this.mail=mail;
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

                //create Result Set and execute query
                ResultSet rs = null;
                if (cost.equals("Low to High")){
                    rs = st.executeQuery("SELECT * from fields where capacity = " + "\"" + Integer.parseInt(capacity) + "\"" + " and location = " + "\"" + city + "\"" + " group by company order by cost_per_hour;");
                }else{
                    rs = st.executeQuery("SELECT * from fields where capacity = " + "\"" + Integer.parseInt(capacity) + "\"" + " and location = " + "\"" + city + "\"" + " group by company order by cost_per_hour desc;");
                }

                ArrayList<String> fields = new ArrayList<String>();
                ArrayList<String> sites = new ArrayList<String>();
                ArrayList<String> pictures = new ArrayList<String>();

                //Add results to arraylists
                try {
                    while(rs.next()){
                        fields.add(rs.getString(4));
                        sites.add(rs.getString(6));
                        pictures.add(rs.getString(7));
                    }

                    //change Activity
                    Intent i = new Intent(Filters.this, SearchResults.class);
                    i.putExtra("fields", fields);
                    i.putExtra("sites", sites);
                    i.putExtra("pictures", pictures);
                    i.putExtra("mail", mail);
                    startActivity(i);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }

    /*
   Klash gia syndesh me th vash.
   Sthn doInBackground anazhtoume ghpeda me vash to onoma pou plhktrologhse o xrhsths
    */
    class Task_Search extends AsyncTask<Void,Void,Void> {

        String searchText;
        String capacity="0";
        String mail;

        public Task_Search(String searchText, String mail){
            super();
            this.searchText=searchText;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                //connect to DB
                Class.forName("com.mysql.jdbc.Driver");
                //allakste thn ip me th dikh sas ip
                con = DriverManager.getConnection("jdbc:mysql://192.168.1.5:3306/startgame?allowPublicKeyRetrieval=true&useSSL=false", "newuser", "0000");
                //create Statement
                Statement st = null;
                st = con.createStatement();

                //create Result Set and execute query to check if there is already a user with that email
                ResultSet rs = null;
                rs = st.executeQuery("SELECT * from fields where capacity!=" + "\"" + Integer.parseInt(capacity) + "\"" + " and company LIKE " + "\"" + searchText + "\"" + " group by company;");

                ArrayList<String> fields = new ArrayList<String>();
                ArrayList<String> sites = new ArrayList<String>();
                ArrayList<String> pictures = new ArrayList<String>();

                try {
                    while(rs.next()){
                        fields.add(rs.getString(4));
                        sites.add(rs.getString(6));
                        pictures.add(rs.getString(7));
                    }
                    //change Activity
                    Intent i = new Intent(Filters.this, SearchResults.class);
                    i.putExtra("fields", fields);
                    i.putExtra("sites", sites);
                    i.putExtra("pictures", pictures);
                    i.putExtra("mail", mail);
                    startActivity(i);
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
