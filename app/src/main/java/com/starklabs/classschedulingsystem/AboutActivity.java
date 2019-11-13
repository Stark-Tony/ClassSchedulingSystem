package com.starklabs.classschedulingsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener{

    CircleImageView img1, img2, img3, img4;
    TextView link2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        link2 = findViewById(R.id.link2);

        img1 = findViewById(R.id.imgView1);
        img2 = findViewById(R.id.imgView2);
        img3 = findViewById(R.id.imgView3);
        img4 = findViewById(R.id.imgView4);

        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);

        link2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutActivity.this, InstructionActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.imgView1:
            case R.id.imgView2:
            case R.id.imgView3:
            case R.id.imgView4:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                LayoutInflater inflater = LayoutInflater.from(this);
                View view=inflater.inflate(R.layout.image_dailog,null);
                CircleImageView tempImg= (CircleImageView)v;
                ImageView alertImage = view.findViewById(R.id.alertImage);
                alertImage.setImageDrawable(tempImg.getDrawable());
                alert.setView(view);
                alert.create().show();
                break;
        }
    }
}
