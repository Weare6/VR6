package com.rohheat.mainvr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class VideosActivity extends AppCompatActivity {

    private ImageView sample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);

        sample = findViewById(R.id.sample);

        sample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toVideoView = new Intent(VideosActivity.this,ViewVideoActivity.class);
                toVideoView.putExtra("key","samplevideo.mp4");
                startActivity(toVideoView);
            }
        });

    }
}