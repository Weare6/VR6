package com.rohheat.mainvr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Toast;

import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.io.IOException;
import java.io.InputStream;

public class ViewImageActivity extends AppCompatActivity {


    private VrPanoramaView paraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        paraView = findViewById(R.id.pano_view);
        loadImage();

    }

    public void loadImage(){

        String photoKey = getIntent().getStringExtra("key");

        VrPanoramaView.Options options = new VrPanoramaView.Options();
        InputStream inputStream = null;

        AssetManager assetManager = getAssets();

        try{
            inputStream = assetManager.open(photoKey);
            options.inputType = VrPanoramaView.Options.TYPE_MONO;
            paraView.loadImageFromBitmap(BitmapFactory.decodeStream(inputStream),options);
            inputStream.close();
        }catch (IOException e){
            Toast.makeText(ViewImageActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onPause() {
        paraView.pauseRendering();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        paraView.resumeRendering();
    }

    @Override
    protected void onDestroy() {
        paraView.shutdown();
        super.onDestroy();
    }
}