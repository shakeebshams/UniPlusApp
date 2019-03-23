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
    
    void sort(int arr[]) 
    { 
        int n = arr.length; 
        for (int i = 1; i < n; ++i) { 
            int key = arr[i]; 
            int j = i - 1; 
  
            /* Move elements of arr[0..i-1], that are 
               greater than key, to one position ahead 
               of their current position */
            while (j >= 0 && arr[j] > key) { 
                arr[j + 1] = arr[j]; 
                j = j - 1; 
            } 
            arr[j + 1] = key; 
        } 
    } 
  
    /* A utility function to print array of size n*/
    static void printArray(int arr[]) 
    { 
        int n = arr.length; 
        for (int i = 0; i < n; ++i) 
            System.out.print(arr[i] + " "); 
  
        System.out.println(); 
    } 
  
    // Driver method 
    public static void driver(String args[]) 
    { 
        int arr[] = { 12, 11, 13, 5, 6 }; 
  
        InsertionSort ob = new InsertionSort(); 
        ob.sort(arr); 
  
        printArray(arr); 
    }
}
