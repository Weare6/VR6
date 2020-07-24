package com.rohheat.mainvr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PhotosActivity extends AppCompatActivity {

    private ImageView tajmahal,paris,liberty,tokyo,pyramids;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        tajmahal = findViewById(R.id.sample);
        paris = findViewById(R.id.paris);
        liberty = findViewById(R.id.liberty);
        tokyo = findViewById(R.id.tokyo);
        pyramids = findViewById(R.id.pyramids);



        tajmahal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: send string
                Intent toView = new Intent(PhotosActivity.this,ViewImageActivity.class);
                toView.putExtra("key","tajmahal.jpg");
                startActivity(toView);
            }
        });

        paris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: send string
                Intent toView = new Intent(PhotosActivity.this,ViewImageActivity.class);
                toView.putExtra("key","paris.jpg");
                startActivity(toView);
            }
        });

        tokyo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: send string
                Intent toView = new Intent(PhotosActivity.this,ViewImageActivity.class);
                toView.putExtra("key","tokyo.jpg");
                startActivity(toView);
            }
        });

        pyramids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: send string
                Intent toView = new Intent(PhotosActivity.this,ViewImageActivity.class);
                toView.putExtra("key","pyramids.jpg");
                startActivity(toView);
            }
        });

        liberty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: send string
                Intent toView = new Intent(PhotosActivity.this,ViewImageActivity.class);
                toView.putExtra("key","liberty.jpg");
                startActivity(toView);
            }
        });

    }
}