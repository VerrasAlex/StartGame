package com.example.startgame;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/*Activity gia pros8hkh ghpedou Basket. An o xrhsths dwsei ta aparaithta orismata tote to ghpedo
prosti8etai sth vash kai emfanizetai ena pop up mhnuma eidopoihshs sto xrhsth
 */

public class AddBasketballField extends AppCompatActivity {

    private EditText name, city, cost;
    private Button addBField;

    private RadioGroup typeRG;
    private RadioButton typeRB;

    static Connection con = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_basketball_field);


        addBField = (Button)findViewById(R.id.buttonAddBField);
        addBField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = (EditText)findViewById(R.id.editText);
                String nam = name.getText().toString();

                city = (EditText)findViewById(R.id.editText2);
                String cityname = city.getText().toString();

                cost = (EditText)findViewById(R.id.editText3);
                String costph = cost.getText().toString();

                typeRG = (RadioGroup) findViewById(R.id.rdGroup);

                if(typeRG.getCheckedRadioButtonId()==-1 || nam.equals("") || cityname.equals("") || costph.equals("")){
                    Toast.makeText(AddBasketballField.this, "Please fill in the necessary details", Toast.LENGTH_SHORT).show();
                }else{
                    //get Type
                    int type = typeRG.getCheckedRadioButtonId();
                    typeRB = (RadioButton) findViewById(type);
                    String typeText = (String) typeRB.getText();


                    if(typeText.equals("Indoor")){
                        typeText="1";
                    }else{
                        typeText="0";
                    }

                    new Task(nam, cityname, costph, typeText).execute();

                    DialogFragment df = new FieldAddedDialogFragment();
                    df.show(getSupportFragmentManager(), "Field added");
                }
            }
        });
    }

     /*
    Klash gia syndesh me th vash.
    Sthn doInBackground kanoume th syndesh kai kanoume insert ta nea dedomena
    ston pinaka fields
     */

    class Task extends AsyncTask<Void, Void, Void> {

        String name;
        String city;
        String cost;
        String type;
        int capacity=0;

        public Task(String name, String city, String cost, String type){
            super();
            this.name=name;
            this.city=city;
            this.cost=cost;
            this.type=type;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try{
                //connect to DB
                Class.forName("com.mysql.jdbc.Driver");
                //allakste thn ip me th dikh sas ip
                con = DriverManager.getConnection("jdbc:mysql://192.168.1.179:3306/startgame?allowPublicKeyRetrieval=true&useSSL=false", "newuser", "0000");

                //instert into DB
                PreparedStatement ps = con.prepareStatement("INSERT INTO fields(type, capacity, location, company, cost_per_hour, site_url, picture_url) VALUES(?,?,?,?,?,?,?);");
                ps.setInt(1, Integer.parseInt(type));
                ps.setInt(2, capacity);
                ps.setString(3, city);
                ps.setString(4, name);
                ps.setString(5, cost);
                ps.setString(6, "");
                ps.setString(7, "");

                int x = ps.executeUpdate();
            }catch(Exception e){
                e.printStackTrace();
            }

            return null;
        }
    }
}
