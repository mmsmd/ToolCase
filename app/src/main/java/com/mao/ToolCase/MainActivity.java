package com.mao.ToolCase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText ll_pwd;
	private EditText ll_user;
	private CheckBox cb;
	private String user;
	private String pwd;
	int id =0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//??????
				ll_user = (EditText) findViewById(R.id.ll_user);
				ll_pwd=(EditText) findViewById(R.id.ll_pwd);
				cb=(CheckBox) findViewById(R.id.cb);


        Button btn_rg=findViewById(R.id.btn_rg);
        btn_rg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent it=new Intent();
                it.setClass(MainActivity.this,RegisterActivity.class);
                MainActivity.this.startActivity(it);

            }
        });
				getInfo();
	}

	private void getInfo() {
		XmlPullParser pullParser = Xml.newPullParser();
		try {
			File file = new File(this.getFilesDir(),"person.xml");
			FileInputStream fis = new FileInputStream(file);
			pullParser.setInput(fis, "utf-8");
			
			int eventtType = pullParser.getEventType();
			
			while(eventtType!=pullParser.END_DOCUMENT){
				if(eventtType==pullParser.START_TAG){
					if(pullParser.getName().equalsIgnoreCase("user")){
						user = pullParser.nextText();
					}
					if(pullParser.getName().equalsIgnoreCase("pwd")){
						pwd = pullParser.nextText();
					}
				}
				eventtType=pullParser.next();
			}
			ll_user.setText(user);
			ll_pwd.setText(pwd);
			fis.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		

	public void login(View view){
				user = ll_user.getText().toString().trim();
				pwd = ll_pwd.getText().toString().trim();
				
				
				if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pwd)) {
					Toast.makeText(this, "请输入账号密码", Toast.LENGTH_LONG).show();
				} else if (cb.isChecked()) {
					saveInfo(user, pwd);
					
					Intent intent = new Intent(this, IndexActivity.class);
					
					
					intent.putExtra("user",user);
					intent.putExtra("pwd",pwd);
					
					startActivity(intent);

				} else {
					Toast.makeText(this, "请勾选记住密码", Toast.LENGTH_LONG).show();
				}
	}

	private void saveInfo(String user, String pwd) {
		// TODO Auto-generated method stub
		XmlSerializer serializer = Xml.newSerializer();
		try {
			File file = new File(this.getFilesDir(), "person.xml");
			FileOutputStream fos = new FileOutputStream(file);
			serializer.setOutput(fos, "utf-8");
			serializer.startDocument("utf-8", true);
			serializer.startTag("", "persons");	
			serializer.startTag("", "person");
			serializer.attribute("", "id", id + "");
			serializer.startTag("", "user");
			serializer.text(user);
			serializer.endTag("", "user");
			serializer.startTag("", "pwd");
			serializer.text(pwd);
			serializer.endTag("", "pwd");
			serializer.endTag("", "person");

			serializer.endTag("", "persons");
			serializer.endDocument();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
