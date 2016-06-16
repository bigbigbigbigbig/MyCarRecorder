package com.liu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class First extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				handler.sendEmptyMessage(1); // 给UI主线程发送消息
			}
		}, 3000); // 启动等待3秒钟
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			Intent intent = new Intent();
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 1:
				// 切换到主页面
				intent.setClass(First.this, MainActivity.class);
				//intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
				First.this.finish();
				break;
			}
		}
	};
}
