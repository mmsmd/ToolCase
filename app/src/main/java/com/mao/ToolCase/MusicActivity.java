package com.mao.ToolCase;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MusicActivity extends Activity {
    MyServiceConn conn;
    Intent intent1;
    MusicInterface mi;

    private static SeekBar sb;

    private static TextView tv_progress;
    private static TextView tv_total;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music);

        TextView tv_user = (TextView) findViewById(R.id.tv_user);

        Intent intent = getIntent();

        tv_progress = (TextView) findViewById(R.id.tv_progress);
        tv_total = (TextView) findViewById(R.id.tv_total);

        intent1 = new Intent(this, com.mao.ToolCase.MusicService.class);

        startService(intent1);

        conn = new MyServiceConn();

        bindService(intent1, conn, BIND_AUTO_CREATE);

        sb = (SeekBar) findViewById(R.id.sb);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                int progress = seekBar.getProgress();

                mi.seekTo(progress);
            }
        });
    }

    public static Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {

            Bundle bundle = msg.getData();

            int duration = bundle.getInt("duration");

            int currentPostition = bundle.getInt("currentPosition");

            sb.setMax(duration);
            sb.setProgress(currentPostition);

            int minute = duration / 1000 / 60;
            int second = duration / 1000 % 60;

            String strMinute = null;
            String strSecond = null;

            if(minute < 10) {

                strMinute = "0" + minute;
            } else {

                strMinute = minute + "";
            }

            if(second < 10)
            {
                strSecond = "0" + second;
            } else {

                strSecond = second + "";
            }

            tv_total.setText(strMinute + ":" + strSecond);

            minute = currentPostition / 1000 / 60;
            second = currentPostition / 1000 % 60;

            if(minute < 10) {

                strMinute = "0" + minute;
            } else {

                strMinute = minute + "";
            }

            if(second < 10) {

                strSecond = "0" + second;
            } else {

                strSecond = second + "";
            }

            tv_progress.setText(strMinute + ":" + strSecond);
        }
    };

    public void play(View view) {


        mi.play();
    }

    public void pausePlay(View view) {

        mi.pausePlay();
    }

    public void continuePlay (View view) {

        mi.continuePlay();
    }

    public void exit(View view) {



        finish();

    }

    class MyServiceConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            mi = (MusicInterface) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }



}