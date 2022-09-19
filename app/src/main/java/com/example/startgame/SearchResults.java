package com.example.startgame;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/*
H klash pou pairnei ta apotelesmata anazhthshs kai ta emfanizei sthn o8onh
 */
public class SearchResults extends AppCompatActivity {

    private ArrayList<String> fields = new ArrayList<String>();
    private ArrayList<String> sites = new ArrayList<String>();
    private ArrayList<String> pictures = new ArrayList<String>();

    private TextView noItemsTV;

    private String mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        mail = getIntent().getStringExtra("mail");

        // Get the intent, verify the action and get the query
        // Get the arraylists containing necesary info
        fields = getIntent().getStringArrayListExtra("fields");
        sites = getIntent().getStringArrayListExtra("sites");
        pictures = getIntent().getStringArrayListExtra("pictures");


        //An o xrhsths pathsei panw se kapoio stoixeio ths listas
        //tote auto 8a ksekinhsei nea o8onh, th FieldDetails
        if(fields.size() != 0){
            final ListView resultsLV = (ListView) findViewById(R.id.resultsLV);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fields);
            resultsLV.setAdapter(adapter);
            resultsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(getApplicationContext(), FieldDetails.class);
                    i.putExtra("fieldIndex", position);
                    i.putExtra("fields", fields);
                    i.putExtra("sites", sites);
                    i.putExtra("pictures", pictures);
                    i.putExtra("mail", mail);
                    startActivity(i);
                }
            });
        }else{
            noItemsTV = (TextView) findViewById(R.id.noItemsTV);
            noItemsTV.setText("No items match your search");
        }

    }


}
