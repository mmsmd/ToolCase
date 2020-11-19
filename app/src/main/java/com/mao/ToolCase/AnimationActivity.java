package com.mao.ToolCase;

import android.animation.Animator;
import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class AnimationActivity extends Activity {
    private ImageView imgA;
    private ImageView imgB;

    private ScaleAnimation sato0 =new ScaleAnimation(1,0,1,1,
            Animation.RELATIVE_TO_PARENT,0.5f,Animation.RELATIVE_TO_PARENT,0.5f);

    private ScaleAnimation sato1 =new ScaleAnimation(0,1,1,1,
            Animation.RELATIVE_TO_PARENT,0.5f,Animation.RELATIVE_TO_PARENT,0.5f);

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.card2d);
            initView();

            findViewById(R.id.root).setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v){
                    if(imgA.getVisibility()==View.VISIBLE){
                        imgA.startAnimation(sato0);
                    }else {
                        imgB.startAnimation(sato0);
                    }
                }
            });

        }

        private void showImageA(){
            imgA.setVisibility(View.VISIBLE);
            imgB.setVisibility(View.INVISIBLE);
        }

        private void showImageB(){
            imgB.setVisibility(View.VISIBLE);
            imgA.setVisibility(View.INVISIBLE);
        }

        private void initView(){
            imgA=(ImageView)findViewById(R.id.ivA);
            imgB=(ImageView)findViewById(R.id.ivB);
            showImageA();

            sato0.setDuration(500);
            sato1.setDuration(500);

            sato0.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    if(imgA.getVisibility()==View.VISIBLE){
                        imgA.setAnimation(null);
                        showImageB();
                        imgB.startAnimation(sato1);
                    }else{
                        imgB.setAnimation(null);
                        showImageA();
                        imgA.startAnimation(sato1);
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
}