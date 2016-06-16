package com.liu;

import java.io.File;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.MediaController.MediaPlayerControl;
import android.widget.VideoView;


public class PlayVideo extends Activity implements MediaPlayerControl{

	/*自动打开 */

	private VideoView videoView;
	private MediaController controller;
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);


		// 全局设置完毕后加载布局
		setContentView(R.layout.playvideo);

		// 定义ui组件
		videoView = (VideoView) findViewById(R.id.playVideoItem);

		controller = new MediaController(this);
		videoView.setMediaController(controller);
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			String s = Environment.getExternalStorageDirectory().getAbsolutePath();
			File f = new File(s+"/V");
			//获取文件名称
			Bundle bundle = this.getIntent().getExtras();  
			String filename=bundle.getString("filename");
//			Toast.makeText(getApplicationContext(), filename, Toast.LENGTH_SHORT).show();
			File videoFile = new File(f,filename+".mp4");
			videoView.setVideoURI(Uri.fromFile(videoFile));
			videoView.start();
		}
	}
	//暂停
	@Override
	public boolean canPause() {
		// TODO Auto-generated method stub
		return videoView.canPause();
	}
	//后退
	@Override
	public boolean canSeekBackward() {
		// TODO Auto-generated method stub
		return videoView.canSeekForward();
	}
	//前进
	@Override
	public boolean canSeekForward() {
		// TODO Auto-generated method stub
		return videoView.canSeekForward();
	}

	
	public int getAudioSessionId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBufferPercentage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCurrentPosition() {
		// TODO Auto-generated method stub
		return videoView.getCurrentPosition();
	}

	@Override
	public int getDuration() {
		// TODO Auto-generated method stub
		return videoView.getDuration();
	}

	@Override
	public boolean isPlaying() {
		// TODO Auto-generated method stub
		return videoView.isPlaying();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		if(videoView.isPlaying()){
			videoView.pause();
		}
	}

	@Override
	public void seekTo(int pos) {
		// TODO Auto-generated method stub
		videoView.seekTo(pos);
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		videoView.start();
	}

}
