package com.liu;

import java.util.Random;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainActivity extends Activity implements OnClickListener {

	private ImageButton videorecord; // 录视频
	private ImageButton videolist; // 播放视频
	private ImageButton cam; // 拍照
	private ImageButton btnlight;// 照明
	private ImageButton exit;// 退出程序
	private ImageButton teach;// 教程
	private ImageButton calendar;// 日历
	private ImageButton posi;// 定位
	private ImageButton info;// 手机设备信息获取按钮
	private ImageButton set;// 设置按钮
	private ImageButton vib;// 震动检测
	private ImageButton abo;// 关于

	public String str = "";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		videorecord = (ImageButton) findViewById(R.id.videorecord);
		videolist = (ImageButton) findViewById(R.id.videolist);
		cam = (ImageButton) findViewById(R.id.came);
		btnlight = (ImageButton) findViewById(R.id.btnlight);
		exit = (ImageButton) findViewById(R.id.exit);
		teach = (ImageButton) findViewById(R.id.teach);
		calendar = (ImageButton) findViewById(R.id.calendar);
		posi = (ImageButton) findViewById(R.id.position);
		info = (ImageButton) findViewById(R.id.info);
		set = (ImageButton) findViewById(R.id.set);
		vib = (ImageButton) findViewById(R.id.vibrate);
		abo = (ImageButton) findViewById(R.id.about);

		videorecord.setOnClickListener(this);
		videolist.setOnClickListener(this);
		cam.setOnClickListener(this);
		btnlight.setOnClickListener(this);
		exit.setOnClickListener(this);
		teach.setOnClickListener(this);
		calendar.setOnClickListener(this);
		posi.setOnClickListener(this);
		info.setOnClickListener(this);
		set.setOnClickListener(this);
		vib.setOnClickListener(this);
		abo.setOnClickListener(this);

		/*videorecord.setBackgroundColor(Color.parseColor(colorcreate()));
		videolist.setBackgroundColor(Color.parseColor(colorcreate()));
		cam.setBackgroundColor(Color.parseColor(colorcreate()));
		btnlight.setBackgroundColor(Color.parseColor(colorcreate()));
		exit.setBackgroundColor(Color.parseColor(colorcreate()));
		teach.setBackgroundColor(Color.parseColor(colorcreate()));
		calendar.setBackgroundColor(Color.parseColor(colorcreate()));
		posi.setBackgroundColor(Color.parseColor(colorcreate()));
		info.setBackgroundColor(Color.parseColor(colorcreate()));
		set.setBackgroundColor(Color.parseColor(colorcreate()));
		vib.setBackgroundColor(Color.parseColor(colorcreate()));
		abo.setBackgroundColor(Color.parseColor(colorcreate()));*/
	}

	// 随机颜色生成
	public String colorcreate() {
		str = "#";
		for (int i = 0; i < 3; i++) {
			int num = new Random().nextInt(256);
			if (num < 16)
				str = str + "0" + Integer.toHexString(num);
			else
				str = str + Integer.toHexString(num);
		}
		return str;
	}

	// 弹出对话框
	public void dialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		builder.setMessage("确认退出吗？");
		builder.setTitle("提示");
		builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				// MainActivity.this.finish();
				// 退出当前界面
				System.exit(0);
				// 完全退出
				ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
				manager.killBackgroundProcesses(getPackageName());
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create().show();
	}

	// menu显示的内容
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_MENU) {
			// 在这里做你想做的事情
			super.openOptionsMenu(); // 调用这个，就可以弹出菜单
		}
		if(keyCode==KeyEvent.KEYCODE_BACK)
		{
			dialog();
		}
		return true; // 最后，一定要做完以后返回 true，或者在弹出菜单后返回true，其他键返回super，让其他键默认
	}

	@Override
	public void onOptionsMenuClosed(Menu menu) {

		super.onOptionsMenuClosed(menu);
	}

	// 然后是对menu菜单的配置，如下：

	@Override

	public void openOptionsMenu() {

		// TODO Auto-generated method stub

		super.openOptionsMenu();

	}

	@Override

	public boolean onCreateOptionsMenu(Menu menu) {

		// TODO Auto-generated method stub

		super.onCreateOptionsMenu(menu);

		int group1 = 1;

		menu.add(group1, 1, 1, "软件名：多功能行车记录仪");

		menu.add(group1, 1, 2, "制作人：刘文锦");

		return true;

	}

	@Override

	public boolean onOptionsItemSelected(MenuItem item) {

		// TODO Auto-generated method stub

		switch (item.getItemId()) {

		case 1: // do something here

			Log.i("MenuTest:", "ItemSelected:1");

			break;

		case 2: // do something here

			Log.i("MenuTest:", "ItemSelected:2");

			break;

		default:

			return super.onOptionsItemSelected(item);
		}
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.videorecord:
			intent.setClass(MainActivity.this, VideoRecord.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			startActivity(intent);
			break;
		case R.id.videolist:
			intent.setClass(MainActivity.this, VideoList.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			startActivity(intent);
			break;
		case R.id.came:
			intent.setClass(MainActivity.this, MyCamera.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			startActivity(intent);
			break;
		case R.id.btnlight:
			intent.setClass(MainActivity.this, MyLight.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			startActivity(intent);
			break;
		case R.id.exit:
			dialog();
			break;
		case R.id.teach:
			intent.setClass(MainActivity.this, MyTeach.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			startActivity(intent);
			break;
		case R.id.calendar:
			intent.setClass(MainActivity.this, MyCalendar.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			startActivity(intent);
			break;
		case R.id.position:
			intent.setClass(MainActivity.this, LocationDemo.class);
			// intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			startActivity(intent);
			break;
		case R.id.info:
			intent.setClass(MainActivity.this, MyInfo.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			startActivity(intent);
			break;
		case R.id.set:
			intent.setClass(MainActivity.this, MySet.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			startActivity(intent);
			break;
		case R.id.vibrate:
			intent.setClass(MainActivity.this, MySensor.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			startActivity(intent);
			break;
		case R.id.about:
			intent.setClass(MainActivity.this, SoftWareInfo.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}