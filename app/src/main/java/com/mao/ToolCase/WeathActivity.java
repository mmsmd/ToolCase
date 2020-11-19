package com.mao.ToolCase;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class WeathActivity extends Activity implements View.OnClickListener {
    private Button btn;
    private EditText ed_city;
    private TextView city_result1;
    private TextView city_result2;
     @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);
        btn=(Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);

        ed_city=(EditText) findViewById(R.id.ed_city);
        city_result1=(TextView) findViewById(R.id.city_result1);
        city_result2=(TextView) findViewById(R.id.city_result2);
    }


    private final static String PATH="http://wthrcdn.etouch.cn/weather_mini?city=";
    protected static final int SUCCESS = 0;
    protected static final int INVALID_CITY = 1;
    protected static final int ERROR = 2;

    private String city;
    String ul;


        private Handler mhandler=new Handler(){
        public void handleMessage(android.os.Message msg) {
            dialog.dismiss();
            switch (msg.what) {
                case SUCCESS:

                    JSONArray data=(JSONArray) msg.obj;
                    try {
                        String day01= data.getString(0);
                        String day02= data.getString(1);
                        city_result1.setText(day01);
                        city_result2.setText(day02);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;



                case INVALID_CITY:
                    Toast.makeText(WeathActivity.this, "城市无效", Toast.LENGTH_SHORT).show();
                    break;
                case ERROR:
                    Toast.makeText(WeathActivity.this, "网络无效", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        };
        };
    ProgressDialog dialog=null;
    public void onClick(View v) {
        // TODO Auto-generated method stub
        city = ed_city.getText().toString().trim();
        if (TextUtils.isEmpty(city)) {
            Toast.makeText(this, "路径错误", 0).show();
            return;
        }
        dialog = new ProgressDialog(this);
        dialog.setMessage("正在玩命加载中");
        dialog.show();
        //发起请求给那个网站
        new Thread() {
            public void run() {
                try {
				    ul = PATH + URLEncoder.encode(city, "UTF-8");

                    URL url = new URL(ul);

                    //设置必要的参数信息
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(5000);
                    conn.setRequestMethod("GET");

                    //判断响应码
                    int code = conn.getResponseCode();
                    if (code == 200) {
                        //连接网络成功
                        InputStream in = conn.getInputStream();
                        String data = StreamTool.decodeStream(in);


                        //解析json格式的数据
                        JSONObject jsonObj = new JSONObject(data);
                        //获得desc的值
                        String result = jsonObj.getString("desc");
                        if ("OK".equals(result)) {
                            //城市有效，返回了需要的数据
                            JSONObject dataObj = jsonObj.getJSONObject("data");

                            JSONArray jsonArray = dataObj.getJSONArray("forecast");
                            //通知更新ui
                            Message msg = Message.obtain();
                            msg.obj = jsonArray;
                            msg.what = SUCCESS;
                            mhandler.sendMessage(msg);
                        } else {
                            //城市无效
                            Message msg = Message.obtain();
                            msg.what = INVALID_CITY;
                            mhandler.sendMessage(msg);
                        }
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    Message msg = Message.obtain();
                    msg.what = ERROR;
                    mhandler.sendMessage(msg);
                }
            }

            ;
        }.start();
    }
}
