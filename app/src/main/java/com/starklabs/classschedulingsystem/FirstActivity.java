package com.starklabs.classschedulingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class FirstActivity extends AppCompatActivity {

    MaterialButton student, instructor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        student = findViewById(R.id.first_button_student);
        instructor = findViewById(R.id.first_button_instructor);

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this,LoginActivity.class);
                intent.putExtra("Type",2);
                startActivity(intent);
                finish();
            }
        });

        instructor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, LoginActivity.class);
                intent.putExtra("Type",1);
                startActivity(intent);
                finish();
            }
        });
    }
}
