package com.starklabs.classschedulingsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DownloadManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    com.starklabs.classschedulingsystem.MyTextView mon1,mon2,mon3,mon4,mon5,mon6,mon7,mon8,tue1,tue2,tue3,tue4,tue5,tue6,tue7,tue8,
                                                    wed1,wed2,wed3,wed4,wed5,wed6,wed7,wed8,thu1,thu2,thu3,thu4,thu5,thu6,thu7,thu8,
                                                    fri1,fri2,fri3,fri4,fri5,fri6,fri7,fri8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mon1 = findViewById(R.id.mon1);
        mon2 = findViewById(R.id.mon2);
        mon3 = findViewById(R.id.mon3);
        mon4 = findViewById(R.id.mon4);
        mon5 = findViewById(R.id.mon5);
        mon6 = findViewById(R.id.mon6);
        mon7 = findViewById(R.id.mon7);
        mon8 = findViewById(R.id.mon8);

        tue1 = findViewById(R.id.tue1);
        tue2 = findViewById(R.id.tue2);
        tue3 = findViewById(R.id.tue3);
        tue4 = findViewById(R.id.tue4);
        tue5 = findViewById(R.id.tue5);
        tue6 = findViewById(R.id.tue6);
        tue7 = findViewById(R.id.tue7);
        tue8 = findViewById(R.id.tue8);

        wed1 = findViewById(R.id.wed1);
        wed2 = findViewById(R.id.wed2);
        wed3 = findViewById(R.id.wed3);
        wed4 = findViewById(R.id.wed4);
        wed5 = findViewById(R.id.wed5);
        wed6 = findViewById(R.id.wed6);
        wed7 = findViewById(R.id.wed7);
        wed8 = findViewById(R.id.wed8);

        thu1 = findViewById(R.id.thu1);
        thu2 = findViewById(R.id.thu2);
        thu3 = findViewById(R.id.thu3);
        thu4 = findViewById(R.id.thu4);
        thu5 = findViewById(R.id.thu5);
        thu6 = findViewById(R.id.thu6);
        thu7 = findViewById(R.id.thu7);
        thu8 = findViewById(R.id.thu8);

        fri1 = findViewById(R.id.fri1);
        fri2 = findViewById(R.id.fri2);
        fri3 = findViewById(R.id.fri3);
        fri4 = findViewById(R.id.fri4);
        fri5 = findViewById(R.id.fri5);
        fri6 = findViewById(R.id.fri6);
        fri7 = findViewById(R.id.fri7);
        fri8 = findViewById(R.id.fri8);

        mon5.setOnClickListener(this);
        tue5.setOnClickListener(this);
        wed5.setOnClickListener(this);
        thu5.setOnClickListener(this);
        fri5.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.mon5:
            case R.id.tue5:
            case R.id.wed5:
            case R.id.thu5:
            case R.id.fri5:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                final View view = inflater.inflate(R.layout.lunch_break,null);
                builder.setView(view);
                builder.create().show();
                break;
        }
    }

    @Override
    protected void onResume() {
        String url ="http://172.19.13.70:8080/scheduleing/Slot/Get/";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Toast.makeText(MainActivity.this,"Responded:"+response,Toast.LENGTH_LONG).show();
                for(int i=0;i<response.length();i++)
                {
                    try
                    {
                        JSONObject tempObj = response.getJSONObject(i);
                        String slotid = tempObj.getString("slotid");
                        String profid = tempObj.getString("profid");
                        String status  = tempObj.getString("status");

                    }
                    catch (JSONException e) {
                        Toast.makeText(MainActivity.this, "JSON Object Exception", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"Error:"+error,Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
        super.onResume();
    }

    private void putToSlot(String slotid, String profid, String status, String subjectid) {

    }
}
