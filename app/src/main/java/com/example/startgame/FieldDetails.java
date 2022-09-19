package com.example.startgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;

/*
Activity pou emfanizei ta stoixeia tou ghpedou pou o xrhsths epelekse.
O xrhsths mporei na va8mologhsei to ghpedo kai na pathsei ena koumpi
gia na kanei krathsh toy ghpedou
 */
public class FieldDetails extends AppCompatActivity {

    private ArrayList<String> fields = new ArrayList<String>();
    private ArrayList<String> sites = new ArrayList<String>();
    private ArrayList<String> pictures = new ArrayList<String>();

    //Field Name Text View
    private TextView fieldNameTV;
    private String fieldName;

    //site URL
    private String siteURL="";

    //picture URL Image View
    private ImageView pictureIV;
    private String  pictureURL;

    //Rating Bar
    private RatingBar ratingBar;

    //Reserve Button
    private Button buttonReserve;

    //Site Text View
    private TextView siteTV;

    //Connection
    static Connection con = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_details);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        fields = getIntent().getStringArrayListExtra("fields");
        sites = getIntent().getStringArrayListExtra("sites");
        pictures = getIntent().getStringArrayListExtra("pictures");
        int fieldIndex = getIntent().getIntExtra("fieldIndex", 0);
        fieldName=fields.get(fieldIndex);
        siteURL=sites.get(fieldIndex);
        pictureURL=pictures.get(fieldIndex);

        //Field Name Text View
        fieldNameTV = (TextView)findViewById(R.id.fieldNameTV);
        fieldNameTV.setText(fieldName);

        //Rating Bar
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(getApplicationContext(), "Thank you for your rating:  " + String.valueOf(rating), Toast.LENGTH_SHORT).show();
            }
        });

        //Reserve Button
        buttonReserve =(Button) findViewById(R.id.buttonReserve);
        buttonReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CreateEvent.class);
                i.putExtra("picURL", pictureURL);
                startActivity(i);
            }
        });

        //Site URL
        siteTV = (TextView) findViewById(R.id.siteTV);
        if(siteURL.equals("")){
            siteURL="There is no site. Sorry.";
            siteTV.setText(siteURL);
        }else{
            siteTV.setText(siteURL);
        }

        //Load Picture
        if(pictureURL.equals("")){
            pictureURL="https://rickyrubio9.weebly.com/uploads/1/9/6/1/19612197/2503234_orig.png";
        }
        pictureIV = (ImageView) findViewById(R.id.pictureIV);
        LoadImage loadImage = new LoadImage(pictureIV);
        loadImage.execute(pictureURL);



    }

    /*
    Klash gia fortwsh url eikonas
    Sthn doInBackground anoigoume ena stream kai fortwnoume thn eikona ws bitmap
     */

    class LoadImage extends AsyncTask<String, Void, Bitmap>{

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
