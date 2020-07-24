package com.rohheat.mainvr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class WatchActivity extends AppCompatActivity {

    private ImageView photos,videos,activities,healthtip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch);

        photos = findViewById(R.id.photos);
        videos = findViewById(R.id.videos);
        activities = findViewById(R.id.activities);
        healthtip = findViewById(R.id.healthtip);

        photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toPhotos = new Intent(WatchActivity.this,PhotosActivity.class);
                startActivity(toPhotos);
            }
        });

        videos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toVideos = new Intent(WatchActivity.this,VideosActivity.class);
                startActivity(toVideos);
            }
        });

        activities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toActivities = new Intent(WatchActivity.this,VrActivity.class);
                startActivity(toActivities);
            }
        });

        healthtip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toHealthTips = new Intent(WatchActivity.this,HealthActivity.class);
                startActivity(toHealthTips);
            }
        });

    }
}