package com.shamscorp.uniplus;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.shapes.Shape;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
//import android.util.Size;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private ArrayList<String> countryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        countryList = new ArrayList<>();
    }

    public void goToCountry(View view) {
        Intent myIntent = new Intent(this, UniSearchActivity.class);
        startActivity(myIntent);
    }

    public void getCountryJson() {
        String jsonData;
        try {
            InputStream is = getAssets().open("usunidata.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            jsonData = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(jsonData);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                countryList.add(obj.getString("INSTNM"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
