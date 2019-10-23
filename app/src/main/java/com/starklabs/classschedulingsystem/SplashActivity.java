package com.starklabs.classschedulingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

public class SplashActivity extends AppCompatActivity {

    Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mHandler = new Handler();

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences = getApplicationContext().getSharedPreferences("LoginInfo",0);
                SharedPreferences.Editor editor = preferences.edit();
                if(preferences.getBoolean("isLoggedIn",false))
                {
                    int type = preferences.getInt("type",0);
                    if(type==1)
                    {
                        RequestQueue requestQueue = Volley.newRequestQueue(SplashActivity.this);
                        final String user,pass;
                        user = preferences.getString("username",null);
                        pass = preferences.getString("password",null);
                        String url = "http://172.19.13.70:8080/scheduleing/Login/Professor/" + user + "/" + pass;
                        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.contains("invalid")) {
                                    Intent intent = new Intent(SplashActivity.this,FirstActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                                    intent.putExtra("Type", 1);
                                    intent.putExtra("Course", response);
                                    intent.putExtra("profid",user);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Snackbar.make(findViewById(android.R.id.content), "Error connecting the server", Snackbar.LENGTH_LONG).show();
                            }
                        });

                        requestQueue.add(stringRequest);
                    }
                    else if(type==2)
                    {
                        RequestQueue requestQueue = Volley.newRequestQueue(SplashActivity.this);
                        final String user,pass;
                        user = preferences.getString("username",null);
                        pass = preferences.getString("password",null);
                        String url = "http://172.19.13.70:8080/scheduleing/Login/Student/" +user+ "/" + pass;
                        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.contains("invalid")) {
                                    Intent intent = new Intent(SplashActivity.this,FirstActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                                    intent.putExtra("Type", 2);
                                    intent.putExtra("studentid",user);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Snackbar.make(findViewById(android.R.id.content), "Error connecting the server", Snackbar.LENGTH_LONG).show();
                            }
                        });

                        requestQueue.add(stringRequest);
                    }
                    else
                    {
                        Intent intent = new Intent(SplashActivity.this,FirstActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else
                {
                    Intent intent = new Intent(SplashActivity.this,FirstActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },2000);
    }

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
