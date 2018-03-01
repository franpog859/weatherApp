package com.example.fran.udemy_24_whatstheweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText locationEditText;
    private TextView descriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setWidgets();
    }

    private void setWidgets() {
        locationEditText = (EditText) findViewById(R.id.location);
        descriptionTextView = (TextView) findViewById(R.id.description);
    }

    public void search(View view) {
        String location = getLocation();
        setDescriptionFor(location);
    }

    private String getLocation() {
        return locationEditText.getText().toString();
    }

    private void setDescriptionFor(String location) {
        String description = getDescriptionFor(location);
        descriptionTextView.setText(description);
    }

    private String getDescriptionFor(String location) {
        String url = getUrlFor(location);
        String data = getDataFrom(url);
        String description = getEditedDescriptionFrom(data);
        return description;
    }

    private String getUrlFor(String location) {
        return "http://openweathermap.org/data/2.5/weather?q=" +
                location +
                "&appid=b6907d289e10d714a6e88b30761fae22";
    }

    private String getDataFrom(String url) {
        try {
            HtmlDownloader downloader = new HtmlDownloader();
            return downloader.execute(url).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getEditedDescriptionFrom(String data) {
        return JSONParser.getDataFrom(data);
    }
}