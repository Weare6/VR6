package com.rohheat.mainvr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.google.vr.sdk.widgets.video.VrVideoEventListener;
import com.google.vr.sdk.widgets.video.VrVideoView;

import java.io.IOException;

public class ViewVideoActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{

    private VrVideoView vrVideoView;
    private SeekBar seekBar;
    private Button button,playBtn;

    private boolean isPause;
    private boolean isMuted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_video);

        vrVideoView = findViewById(R.id.video_view);
        seekBar = findViewById(R.id.seek_bar);
        playBtn = findViewById(R.id.playbtn);
        vrVideoView.setEventListener(new ActivityEventListner());
        seekBar.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) ViewVideoActivity.this);

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playPause();
            }
        });

        VideoLoaderTask bGVideoLoaderTask = new VideoLoaderTask();
        bGVideoLoaderTask.execute();

    }

    public void playPause(){
        if (isPause){
            vrVideoView.playVideo();
        }else {
            vrVideoView.pauseVideo();
        }
        isPause = !isPause;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            vrVideoView.seekTo(progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) { }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) { }

    private class ActivityEventListner extends VrVideoEventListener {
        @Override
        public void onLoadSuccess() {
            super.onLoadSuccess();

            seekBar.setMax((int) vrVideoView.getDuration() );
            isPause = false;

        }

        @Override
        public void onLoadError(String errorMessage) {
            super.onLoadError(errorMessage);
        }

        @Override
        public void onClick() {
            super.onClick();
        }

        @Override
        public void onNewFrame() {
            super.onNewFrame();
            seekBar.setProgress((int) vrVideoView.getCurrentPosition() );
        }

        @Override
        public void onCompletion() {
            super.onCompletion();
            vrVideoView.seekTo(0);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        vrVideoView.pauseRendering();
        isPause = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        vrVideoView.resumeRendering();
        isPause= false;
    }

    @Override
    protected void onDestroy() {
        vrVideoView.shutdown();
        super.onDestroy();
    }

    class VideoLoaderTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                String videoFile = getIntent().getStringExtra("key");
                VrVideoView.Options options = new VrVideoView.Options();
                options.inputType = VrVideoView.Options.TYPE_MONO;
                vrVideoView.loadVideoFromAsset(videoFile, options);
            } catch( IOException e ) {
                //Handle exception
            }

            return true;
        }
    }

}