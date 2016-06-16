package com.liu;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class ShowPic extends Activity {

	String filepath = "/sdcard/V/a.jpg";
	private ImageView img;
	public Button b_return;
	// sharedpreferences的读取
	SharedPreferences sharedPreferences;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.picmain);
		img = (ImageView) findViewById(R.id.picview);
		b_return = (Button) findViewById(R.id.b_return);

		sharedPreferences = getSharedPreferences("lwj", Context.MODE_PRIVATE);
		filepath = sharedPreferences.getString("picpath", "");
		File file = new File(filepath);
		if (file.exists()) {
			Bitmap bm = BitmapFactory.decodeFile(filepath);
			// 将图片显示到ImageView中
			img.setImageBitmap(bm);
		}
		b_return.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ShowPic.this.finish();
			}
		});
	}
}
