package com.mao.ToolCase;



import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppActivity extends Activity {

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);

        lv=(ListView)findViewById(R.id.lv);
//        指定适配器
        /*
         * 1、context:上下文
         *
         * 2、List<? extends Map<String,?>> data  列表 放的都是Map对象 data要显示的数据
         *
         * 3、每个条目要显示布局
         *
         * 4、显示key
         *
         * 5、映射关系
         *
         * */

        List<Map<String,Object>> data=new ArrayList<Map<String, Object>>();

        HashMap<String, Object> map1 = new HashMap<String, Object>();

        map1.put("icon", R.drawable.jd);
        map1.put("name", "京东");

        HashMap<String, Object> map2 = new HashMap<String, Object>();

        map2.put("icon", R.drawable.qq);
        map2.put("name", "QQ");

        HashMap<String, Object> map3 = new HashMap<String, Object>();

        map3.put("icon", R.drawable.qq_dizhu);
        map3.put("name", "QQ斗地主");

        HashMap<String, Object> map4 = new HashMap<String, Object>();

        map4.put("icon", R.drawable.sina);
        map4.put("name", "新浪微博");

        HashMap<String, Object> map5 = new HashMap<String, Object>();

        map5.put("icon", R.drawable.tmall);
        map5.put("name", "天猫");

        HashMap<String, Object> map6 = new HashMap<String, Object>();

        map6.put("icon", R.drawable.uc);
        map6.put("name", "UC浏览器");

        HashMap<String, Object> map7 = new HashMap<String, Object>();

        map7.put("icon", R.drawable.weixin);
        map7.put("name", "微信");

        data.add(map1);
        data.add(map2);
        data.add(map3);
        data.add(map4);
        data.add(map5);
        data.add(map6);
        data.add(map7);

        lv.setAdapter(new SimpleAdapter(this,data, R.layout.my_item,new String[]{"icon","name"},
                new int[]{R.id.imageView,R.id.tv}));
    }
}
