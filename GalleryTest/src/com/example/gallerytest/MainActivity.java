package com.example.gallerytest;



import android.os.Bundle;
import android.app.Activity;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

//Gallery����ˮƽ�б����϶��鿴,ΪImageswitcher����һ��ViewFactory����ΪGalley����һ��Adapter����
//Ϊ����ImageSwitcher������ʾGalleyѡ�е�ͼƬ��ΪGalley����ItemSelectListener���������ɡ�


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
        
        final Gallery gallery = (Gallery) findViewById(R.id.gallery);
        final ImageSwitcher switcher = (ImageSwitcher) findViewById(R.id.switcher);
        
        switcher.setFactory(new ViewFactory() {
			
			@Override
			public View makeView() {
				ImageView imageView = new ImageView(MainActivity.this);
				imageView.setBackgroundColor(0xff0000);
				imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
				imageView.setLayoutParams(new ImageSwitcher.LayoutParams
						(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));//�ؼ�λ������
				return imageView;
			}
		});
        
        //����ͼƬ�����Ķ���Ч��
        switcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
        switcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
   
        //����һ��BaseAdapter���󣬸ö������ṩGallery����ʾ��ÿ��ͼƬ
        BaseAdapter baseAdapter = new BaseAdapter() {
			//�÷������ص�View�ʹ���ÿ���б���
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				ImageView imageView = new ImageView(MainActivity.this);
				imageView.setImageResource(images[position % images.length]);
				//����ImageView����������
				imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
				imageView.setLayoutParams(new Gallery.LayoutParams(75,100));
				TypedArray typedArray = obtainStyledAttributes(R.styleable.Gallery);
				imageView.setBackgroundResource(typedArray.getResourceId
						(R.styleable.Gallery_android_galleryItemBackground, 0));
				return imageView;
			}
			
			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}
			
			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return position;
			}
			
			@Override
			public int getCount() {
				return images.length;
			}
		};
		
		gallery.setAdapter(baseAdapter);
		gallery.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switcher.setImageResource(images[position % images.length]);
			}
		});
    
    }
    
}
