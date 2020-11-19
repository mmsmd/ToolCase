package com.mao.ToolCase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class IndexActivity extends Activity {
    private Button weather;
    private Button video;
    private Button music;
    private Button animation;
    private Button date;
    private Button news;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);

        weather = (Button) findViewById(R.id.weather);
        video = (Button) findViewById(R.id.video);
        music = (Button) findViewById(R.id.music);
        animation = (Button) findViewById(R.id.animation);
        date = (Button) findViewById(R.id.date);
        news = (Button) findViewById(R.id.news);

        Button weather=findViewById(R.id.weather);
        weather.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent it=new Intent();
                it.setClass(IndexActivity.this,WeathActivity.class);
                IndexActivity.this.startActivity(it);
            }
        });

        Button video=findViewById(R.id.video);
        video.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent it=new Intent();
                it.setClass(IndexActivity.this,VideoActivity.class);
                IndexActivity.this.startActivity(it);
            }
        });

        Button music=findViewById(R.id.music);
        music.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent it=new Intent();
                it.setClass(IndexActivity.this,MusicActivity.class);
                IndexActivity.this.startActivity(it);
            }
        });

        Button animation=findViewById(R.id.animation);
        animation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent it=new Intent();
                it.setClass(IndexActivity.this,AnimationActivity.class);
                IndexActivity.this.startActivity(it);
            }
        });

        Button date=findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent it=new Intent();
                it.setClass(IndexActivity.this,DateActivity.class);
                IndexActivity.this.startActivity(it);
            }
        });

        Button news=findViewById(R.id.news);
        news.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent it=new Intent();
                it.setClass(IndexActivity.this,AppActivity.class);
                IndexActivity.this.startActivity(it);
            }
        });
    }
}
