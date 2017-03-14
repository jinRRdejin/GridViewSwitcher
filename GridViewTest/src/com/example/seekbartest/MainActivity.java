package com.example.seekbartest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends Activity {
	
	
	
	int[] images = new int[]
			{R.drawable.page01,R.drawable.page02,R.drawable.page03,R.drawable.page04,
			R.drawable.page05,R.drawable.page06,R.drawable.page07,R.drawable.page08,
			R.drawable.page09,R.drawable.page10,R.drawable.page11,R.drawable.page12,
			R.drawable.page13,R.drawable.page14,R.drawable.page15,R.drawable.page16};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
      List<Map<String, Object>> listItems = new ArrayList<Map<String,Object>>();
      
      for(int i = 0; i<images.length; i++){
    	  Map<String, Object> listItem = new HashMap<String, Object>();
    	  listItem.put("image",images[i]);
    	  listItems.add(listItem);
      }
    	  //获取显示的图片ImageSwitcher
    	  final ImageSwitcher switcher = (ImageSwitcher) findViewById(R.id.switcher);
    	  //设置动画的切换效果
    	  
    	  switcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
    	  switcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
    	  
    	  //为ImageSwitcher设置图片的动画效果
    	  
    	  switcher.setFactory(new ViewFactory() {
			
			@Override
			public View makeView() {
				ImageView imageView = new ImageView(MainActivity.this);
				imageView.setBackgroundColor(0xff0000);
				imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);//ScaleType决定了图片在View上显示的样子，当前为居中显示
				imageView.setLayoutParams(new ImageSwitcher.LayoutParams
						(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));				
										
				return imageView;
			}
		});
    	  
    	  SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems, R.layout.ceil,
    			  new String[]{"image"},new int[]{R.id.image1});
    	  
    	  GridView grid = (GridView) findViewById(R.id.grid01);
    	  
    	  //为gridView设置adapter
    	  grid.setAdapter(simpleAdapter);
    	  
    	  grid.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// 显示当前被选中的图片
			  switcher.setImageResource(images[position % images.length]);				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {							
			}
		});
    	// 添加列表项被单击的监听器	
    	  grid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switcher.setImageResource(images[position % images.length]);
			}
		}) ;    	   	  
    	  
      }
    }
