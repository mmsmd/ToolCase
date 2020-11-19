package com.mao.ToolCase;

import android.app.Activity;
import android.net.Uri;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends Activity {
    private VideoView videoView;
    private Button btn_start,btn_end;
    private MediaController mediaController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);
        initView();
    }

    private void initView() {
        videoView= (VideoView) findViewById(R.id.videoView);
        btn_start= (Button) findViewById(R.id.btn_start);
        btn_end= (Button) findViewById(R.id.btn_end);


        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });
        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.stopPlayback();
            }
        });
    }

    private void init() {
        videoView = (VideoView) findViewById(R.id.videoView);
        mediaController = new MediaController(this);
        String uri = "android.resource://" + getPackageName() + "/" + R.raw.mv;
        videoView.setVideoURI(Uri.parse(uri));
        videoView.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoView);
        videoView.requestFocus();
        videoView.start();
    }

    public void out(View view) {


        finish();
    }

}
