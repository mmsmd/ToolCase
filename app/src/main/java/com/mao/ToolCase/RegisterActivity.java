package com.mao.ToolCase;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;


import org.xmlpull.v1.XmlPullParser;

import java.io.File;
import java.io.FileInputStream;

public class RegisterActivity extends AppCompatActivity {

    private EditText new_et_name;
    private EditText new_et_pwd;

    private String name;
    private String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        new_et_name=(EditText)findViewById(R.id.et_name);
        new_et_pwd=(EditText)findViewById(R.id.et_pwd);

        Button bt=findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent it=new Intent();
                it.setClass(RegisterActivity.this,MainActivity.class);
                RegisterActivity.this.startActivity(it);

            }
        });
        getInfo();
    }


    private void getInfo(){

        XmlPullParser pullParser= Xml.newPullParser();

        try {
            File file=new File(getFilesDir(),"info.xml");

            FileInputStream fis=new FileInputStream(file);

            pullParser.setInput(fis,"utf-8");

            int eventType = pullParser.getEventType();

            while (eventType!=pullParser.END_DOCUMENT){

                if(eventType==pullParser.START_TAG){

                    if(pullParser.getName().equalsIgnoreCase("name")){
                        name=pullParser.nextText();
                    }
                    if(pullParser.getName().equalsIgnoreCase("pwd")){
                        pwd=pullParser.nextText();
                    }
                }
                System.out.println("eventType=="+eventType);

                eventType=pullParser.next();
            }

            new_et_name.setText(name);
            new_et_pwd.setText(pwd);

            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}