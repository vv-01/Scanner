package com.asv.codescan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Api extends AppCompatActivity {
    //String value = getIntent().getStringExtra("BARCODE_DATA");
//{'studId': '1974614871874687134', 'teamNumber': 'E13', 'teamName': 'Fledge', 'studName': 'Vaishnav\xa0N'}
    String studentId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);
        ListView listview = findViewById(R.id.listview);
        Bundle bundle1 = getIntent().getExtras();
        if (bundle1 != null) {

            String sdata = bundle1.getString("DATA");
            try {
                JSONObject jsonObj = new JSONObject(sdata);
                studentId = jsonObj.getString("studId");
                System.out.println(studentId);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            //System.out.println(sdata);
        }
        else {
            System.out.print("NULL");
        }
        String url = "https://checkin.hacktofuture.in/get_student/" + studentId + "/"; //get
        ArrayList<String> arrNames = new ArrayList<>();
        AndroidNetworking.initialize(this);
        AndroidNetworking.get(url)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("RES", response.toString());
                        //parsing

                        try {
                            for (int i = 0;i < response.length();i++) {
                                JSONObject objResult = response.getJSONObject(0);
                                String studentName = objResult.getString("studName");
                                //String studentID = objResult.getString("studId");
                                String studentSize = objResult.getString("studTeez");
                                arrNames.add("Name : " + studentName);
                                //arrNames.add(studentID);
                                arrNames.add("T-Shirt SIZE : " + studentSize);
                                ArrayAdapter<String> arrAdapter = new ArrayAdapter<String>(Api.this, android.R.layout.simple_list_item_1, arrNames);
                                listview.setAdapter(arrAdapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                        Log.e("ERROR",anError.toString());
                    }
                });
    }
}