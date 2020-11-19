package com.mao.ToolCase;


import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

import com.mao.ToolCase.R;

import java.util.Timer;
import java.util.TimerTask;


public class MusicService extends Service {
 
    private MediaPlayer player;
    private Timer timer;
 

    @Override
    public IBinder onBind(Intent intent) {
 
        return new MusicControl();
    }
 
    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this,
				"登录成功",
				Toast.LENGTH_SHORT).show();
		player = MediaPlayer.create(this, R.raw.test);
		player.setLooping(false);
    }
 
    @Override
    public void onDestroy() {
    	super.onDestroy();
		player.stop();
        player.release();
		Toast.makeText(this,
				"退出",
				Toast.LENGTH_SHORT).show();
    }
 
    public void play() {
 
        
 
            player.reset();
 
            player = MediaPlayer.create(this, R.raw.test);
 
            Toast.makeText(this,
    				"音乐开始播放",
    				Toast.LENGTH_SHORT).show();
    		player.start();
 
            //???????
            addTimer();
            
 
        
    }
 
    //???????????
    public void pausePlay() {
 
        player.pause();
    }
 
    //????????????
    public void continuePlay() {
 
        player.start();
    }
 
    //??????????????????????????
    class MusicControl extends Binder implements MusicInterface {
 
        @Override
        public void play() {
 
            MusicService.this.play();
        }
 
        @Override
        public void pausePlay() {
 
            MusicService.this.pausePlay();
        }
 
        @Override
        public void continuePlay() {
 
            MusicService.this.continuePlay();
        }
 
        @Override
        public void seekTo(int progress) {
 
            MusicService.this.seekTo(progress);
        }
    }
 
    //????????????λ??
    public void seekTo(int progress) {
 
        player.seekTo(progress);
    }
 
    //?????????????????????????е???????
    public void addTimer() {
 
        //?????д????????????
        if(timer == null) {
 
            //?????????????
            timer = new Timer();
 
            timer.schedule(new TimerTask() {
 
                //??м??????
                @Override
                public void run() {
 
                    //???????????
                    int duration = player.getDuration();
 
                    //?????????????????
                    int currentPosition = player.getCurrentPosition();
 
                    //???????????
                    Message msg = MusicActivity.handler.obtainMessage();
 
                    //??????????????????????????
                    Bundle bundle = new Bundle();
                    bundle.putInt("duration", duration);
                    bundle.putInt("currentPosition", currentPosition);
                    msg.setData(bundle);
 
                    //???????????????????????
                    MusicActivity.handler.sendMessage(msg);
                }
            },
 
            //????????????5????????????run??????????500??????????
            5, 500);
        }
    }
}