package com.liu;

import java.io.File;
import java.io.IOException;
import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class VideoRecord extends Activity implements OnClickListener {

	private int recLen = 10;// 默认视频长度为10秒
	private TextView txtView; // 倒计时显示
	public File myRecAudioFile;
	private SurfaceView mSurfaceView; // 预览图
	private SurfaceHolder mSurfaceHolder;
	private Button buttonStart; // 开始按钮
	private Button buttonStop; // 停止按钮
	private Button buttonend;// 返回主界面
	public Mybroadcastreceiver mybm;
	public int x = 0;// 按钮bug标记
	public int y = 0;

	private File dir;
	private MediaRecorder recorder;
	public String path;// 路径

	// sharedpreferences的读取
	SharedPreferences sharedPreferences;

	// 声音开关标记变量（默认为开）
	public int flag_voice = 0;
	// 分辨率标记（默认1280*720）
	public int flag_dpi = 2;
	// 视频长度标记（默认10秒）
	public int flag_longth = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.videorecord);

		mybm = new Mybroadcastreceiver();

		// 首先扫描sd卡是否存在
		scannerSD();

		sharedPreferences = getSharedPreferences("lwj", Context.MODE_PRIVATE);

		txtView = (TextView) findViewById(R.id.time1);

		mSurfaceView = (SurfaceView) findViewById(R.id.videoView);
		mSurfaceHolder = mSurfaceView.getHolder();

		buttonStart = (Button) findViewById(R.id.start);
		buttonStop = (Button) findViewById(R.id.stop);
		buttonend = (Button) findViewById(R.id.end);

		File defaultDir = Environment.getExternalStorageDirectory();
		path = defaultDir.getAbsolutePath() + File.separator + "V" + File.separator;// 创建文件夹存放视频
		dir = new File(path);
		if (!dir.exists()) {
			dir.mkdir();
		}
		recorder = new MediaRecorder();
		// 开始录像
		buttonStart.setOnClickListener(this);
		// 停止录像
		buttonStop.setOnClickListener(this);
		// 返回主界面
		buttonend.setOnClickListener(this);

		flag_voice = sharedPreferences.getInt("voiceflag", 0);
		flag_dpi = sharedPreferences.getInt("dpiflag", 0);
		flag_longth = sharedPreferences.getInt("longthflag", 0);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	// 计时功能
	Handler handler = new Handler();
	Runnable runnable = new Runnable() {
		@Override
		public void run() {

			txtView.setText(recLen + "秒");
			handler.postDelayed(this, 1000);

			if (recLen == 0) {
				Toast.makeText(getApplicationContext(), "停止录像", Toast.LENGTH_SHORT).show();
				recorder.stop();
			}
			recLen--;
			if (recLen == -1) {
				handler.removeCallbacks(runnable);
				myrecorder();
				Toast.makeText(getApplicationContext(), "开始录像", Toast.LENGTH_SHORT).show();
				handler.postDelayed(runnable, 1000);
			}

		}
	};

	// 扫描sd卡
	public void scannerSD() {
		if (!(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))) {
			Toast.makeText(VideoRecord.this, "没有sd卡", Toast.LENGTH_SHORT).show();
			VideoRecord.this.finish();
		}
	}

	// 录像功能主函数
	public void myrecorder() {
		try {
			// 获取当前时间作为文件名
			Date now = new Date();// 声明一个日期
			SimpleDateFormat f2 = new SimpleDateFormat("yyyyMMdd");// 添加不同参数，显示不同日期
			SimpleDateFormat f3 = new SimpleDateFormat("HHmmss");// 显示时间，这里是24小时制
			String videopath="/sdcard/V/"+f2.format(now)+"-"+f3.format(now)+".mp4";
			//myRecAudioFile= new File(dir + File.separator + "V" +
			 //File.separator + videopath);
			// myRecAudioFile.createNewFile();
			// createTempFile，懒对象加载模式。命名会增加一长串数字，类型long
			//myRecAudioFile = File.createTempFile(f2.format(now) + "-" + f3.format(now) + "-", ".mp4", dir);// 创建临时文件
			//recorder.setPreviewDisplay(mSurfaceHolder.getSurface());// 预览
			recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);// 视频源
			// 判断sharedprefences传过来的声音开关标记的值,0表示开启声音，1表示关闭声音
			if (flag_voice == 0) {
				recorder.setAudioSource(MediaRecorder.AudioSource.MIC); // 录音源为麦克风
			}
			recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);// 输出格式为mp4
			// 判断sharedprefences传过来的视频分辨率标记的值，0表示低分辨率，1表示中分辨率，2表示高分辨率
			if (flag_dpi == 1) {
				recorder.setVideoSize(400, 300);// 视频尺寸
			} else if (flag_dpi == 2) {
				recorder.setVideoSize(800, 480);// 视频尺寸
			} else {
				recorder.setVideoSize(1280, 720);// 视频尺寸
			}
			// 预览
			recorder.setPreviewDisplay(mSurfaceHolder.getSurface());
			recorder.setVideoFrameRate(15);// 视频帧频率
			recorder.setVideoEncoder(MediaRecorder.VideoEncoder.H263);// 视频编码
			if (flag_voice == 0) {
				recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);// 音频编码
			}
			int t = 10;
			// sharedprefences的视频长度标记判断，0表示10秒，1表示1分钟，2表示5分钟
			if (flag_longth == 0) {
				t = 10;
			} else if (flag_longth == 1) {
				t = 60;
			} else {
				t = 300;
			}
			recorder.setMaxDuration(1000 * t);// 录制最大时间设置
			recLen = t;
			
			//recorder.setOutputFile(myRecAudioFile.getAbsolutePath());// 保存路径
			recorder.setOutputFile(videopath);
			recorder.prepare();
			recorder.start();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	// 点击按钮
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.start:
			if (x == 0) {
				myrecorder();
				Toast.makeText(getApplicationContext(), "开始录像", Toast.LENGTH_SHORT).show();
				handler.postDelayed(runnable, 1000);
			}
			x = 1;
			y = 1;
			break;
		case R.id.stop:
			if (y == 1) {
				//
				recorder.setOnErrorListener(null);
				//recorder.setPreviewDisplay(null);
				if (recLen > 1) {
					try {
						recorder.stop();
						Toast.makeText(getApplicationContext(), "停止录像", Toast.LENGTH_SHORT).show();
						handler.removeCallbacks(runnable);
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (RuntimeException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			x = 0;
			y = 0;
			break;
		case R.id.end:
			//recorder.stop();
			//handler.removeCallbacks(runnable);
			recorder.reset();
			recorder.release();
			recorder = null;
			VideoRecord.this.finish();
			break;
		}
	}

	// 关机广播监听函数
	public class Mybroadcastreceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {

			if (intent.getAction().equals(Intent.ACTION_SHUTDOWN)) {
				// 检测到关机信号，停止录像
				recorder.stop();
				handler.removeCallbacks(runnable);
				Toast.makeText(getApplicationContext(), "因关机而停止录像", Toast.LENGTH_SHORT).show();
				recorder.reset();
				recorder.release();
				recorder = null;
				VideoRecord.this.finish();
			}
			if (intent.getAction().equals(Intent.ACTION_BATTERY_LOW)) {
				// 检测到电量低，停止录像
				Toast.makeText(getApplicationContext(), "电量低，请及时充电！", Toast.LENGTH_SHORT).show();
				recorder.stop();
				handler.removeCallbacks(runnable);
				recorder.reset();
				recorder.release();
				recorder = null;
			}
			if (intent.getAction().equals(Intent.ACTION_BATTERY_OKAY)) {
				// 检测到电量充足，继续录像
				Toast.makeText(getApplicationContext(), "电量充足，可以录像", Toast.LENGTH_SHORT).show();
			}
			if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
				// 检测到屏幕关闭 ，停止录像
				recorder.stop();
				handler.removeCallbacks(runnable);
			}
		}
	}
}
