package com.starklabs.classschedulingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText username, password;
    MaterialButton login;
    TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        login = findViewById(R.id.button_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        error = findViewById(R.id.error);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                error.setText("");
                if (username.getText().toString().trim().equals("") || password.getText().toString().trim().equals("")) {
                    Snackbar.make(findViewById(android.R.id.content), "None of the fields can be empty", Snackbar.LENGTH_LONG).show();
                } else {
                    Intent intent = getIntent();
                    int type = intent.getIntExtra("Type", 0);
                    if (type == 1) {
                        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
                        final String user,pass;
                        user = username.getText().toString().trim();
                        pass = password.getText().toString().trim();
                        String url = "http://172.20.38.201:8080/scheduleing/Login/Professor/" + user + "/" + pass;
                        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.contains("invalid")) {
                                    error.setText("Invalid Username/Password");
                                } else {
                                    SharedPreferences preferences = getApplicationContext().getSharedPreferences("LoginInfo",0);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putBoolean("isLoggedIn",true);
                                    editor.putString("username",user);
                                    editor.putString("password",pass);
                                    editor.putInt("type",1);
                                    editor.commit();
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.putExtra("Type", 1);
                                    intent.putExtra("Course", response);
                                    intent.putExtra("profid",username.getText().toString().trim());
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Snackbar.make(findViewById(android.R.id.content), "Error connecting the server\n"+error, Snackbar.LENGTH_LONG).show();
                            }
                        });

                        requestQueue.add(stringRequest);
                    } else if (type == 2) {
                        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
                        final String user,pass;
                        user = username.getText().toString().trim();
                        pass = password.getText().toString().trim();
                        String url = "http://172.20.38.201+:8080/scheduleing/Login/Student/" +user+ "/" + pass;
                        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.contains("invalid")) {
                                    error.setText("Invalid Username/Password");
                                } else {
                                    SharedPreferences preferences = getApplicationContext().getSharedPreferences("LoginInfo",0);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putBoolean("isLoggedIn",true);
                                    editor.putString("username",user);
                                    editor.putString("password",pass);
                                    editor.putInt("type",2);
                                    editor.commit();
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.putExtra("Type", 2);
                                    intent.putExtra("studentid",username.getText().toString().trim());
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Snackbar.make(findViewById(android.R.id.content), "Error connecting the server\n"+error, Snackbar.LENGTH_LONG).show();
                            }
                        });

                        requestQueue.add(stringRequest);
                    } else {
                        Snackbar.make(findViewById(android.R.id.content), "Something went wrong", Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
}
