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
Activity filtrwn gia ghpeda basket. O xrhsths mporei na filtrarei ta ghpeda
wste na vriskei pio eukola kai grhgora auto pou tou aresei.
Oi epiloges anazhthshs einai na pskasei me onoma h na psaksei me diafora filtra
pou aforoun to kostos, th xwrhtikothta atomwn kai alla.
 */
public class FiltersBasketball extends AppCompatActivity{


    private EditText searchET;

    //Image Buttons
    private ImageButton searchBTN;
    private ImageButton searchBTN_Filters;

    //Radio Groups
    private RadioGroup typeRG;
    private RadioGroup cityRG;
    private RadioGroup costRG;

    //Radio Buttons
    private RadioButton typeRB;
    private RadioButton cityRB;
    private RadioButton costRB;
    private String mail;

    //Connection
    static Connection con = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters_basketball);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        searchBTN = (ImageButton) findViewById(R.id.searchBTN);
        searchBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchET = (EditText) findViewById(R.id.searchET);
                String searchText = searchET.getText().toString();

                if(searchText.equals("")){
                    Toast.makeText(FiltersBasketball.this, "Please fill in the field name", Toast.LENGTH_SHORT).show();
                }else{
                    searchText="%"+searchText+"%";
                    new Task_Search(searchText, mail).execute();
                }
            }
        });

        searchBTN_Filters = (ImageButton)findViewById(R.id.searchButtonS);
        searchBTN_Filters.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                typeRG = (RadioGroup) findViewById(R.id.typeRG);
                cityRG = (RadioGroup) findViewById(R.id.cityRG);
                costRG = (RadioGroup) findViewById(R.id.costRG);

                if((typeRG.getCheckedRadioButtonId()==-1 || cityRG.getCheckedRadioButtonId()==-1 || costRG.getCheckedRadioButtonId()==-1)){
                    Toast.makeText(FiltersBasketball.this, "Please fill in the necessary filters", Toast.LENGTH_SHORT).show();
                    System.out.println("Please fill in the necessary filters");
                }else{

                    //getType
                    int type = typeRG.getCheckedRadioButtonId();
                    typeRB = (RadioButton) findViewById(type);
                    String typeText = (String) typeRB.getText();

                    String typeFinal;
                    if(typeText.equals("Indoor")){
                        typeFinal="1";
                    }else{
                        typeFinal="0";
                    }

                    //getCity
                    int city = cityRG.getCheckedRadioButtonId();
                    cityRB = (RadioButton) findViewById(city);
                    String cityFinal = (String) cityRB.getText();
                    System.out.println(cityFinal);

                    //getCost
                    int cost = costRG.getCheckedRadioButtonId();
                    costRB = (RadioButton) findViewById(cost);
                    String costText = (String) costRB.getText();
                    //System.out.println(costText);

                    new Task_Filters(typeFinal, cityFinal, costText, mail).execute();
                }
            }
        });
    }

    /*
   Klash gia syndesh me th vash.
   Sthn doInBackground anazhtoume ghpeda me vash ta filtra pou epelekse o xrhsths
    */
    class Task_Filters extends AsyncTask<Void,Void,Void>{

        String capacity="0";
        String type;
        String city;
        String cost;
        String mail;

        public Task_Filters(String type, String city, String cost, String mail){
            super();
            this.type=type;
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

                //create Result Set and execute query to check if there is already a user with that email
                ResultSet rs = null;
                if (cost.equals("Low to High")){
                    rs = st.executeQuery("SELECT * from fields where capacity = " + "\"" + Integer.parseInt(capacity) + "\"" + " and type = " + "\"" + Integer.parseInt(type) + "\"" + " and location = " + "\"" + city + "\"" +" group by company order by cost_per_hour;");
                }else{
                    rs = st.executeQuery("SELECT * from fields where capacity = " + "\"" + Integer.parseInt(capacity) + "\"" + " and type = " + "\"" + Integer.parseInt(type) + "\"" + " and location = " + "\"" + city + "\"" + " group by company order by cost_per_hour desc;");
                }

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
                    Intent i = new Intent(FiltersBasketball.this, SearchResults.class);
                    i.putExtra("fields", fields);
                    i.putExtra("sites", sites);
                    i.putExtra("pictures", pictures);
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
            this.mail=mail;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                //connect to DB
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://192.168.1.5:3306/startgame?allowPublicKeyRetrieval=true&useSSL=false", "newuser", "0000");
                //create Statement
                Statement st = null;
                st = con.createStatement();

                //create Result Set and execute query to check if there is already a user with that email
                ResultSet rs = null;
                rs = st.executeQuery("SELECT * from fields where capacity=" + "\"" + Integer.parseInt(capacity) + "\"" + " and company LIKE " + "\"" + searchText + "\"" + " group by company;");

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
                    Intent i = new Intent(FiltersBasketball.this, SearchResults.class);
                    i.putExtra("fields", fields);
                    i.putExtra("sites", sites);
                    i.putExtra("pictures", pictures);
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
