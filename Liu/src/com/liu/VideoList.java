package com.liu;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class VideoList extends Activity{
	
	String[] names = null;
	List<Map<String, Object>> fileNames = new ArrayList<Map<String, Object>>();
	File[] files = null;
	private ListView listView ; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.videolist);
		listView = (ListView) findViewById(R.id.videolistView);
		scannerSD();
		
		if(null!=(files) && files.length != 0){
			names = new String[files.length];
			for(int i = 0 ; i < files.length ; i++){
				String name = files[i].getName();
				
				String[] sname = name.split("\\.");
				names[i] = sname[0];
			}
			
		}
		
		Map<String,Object> map = null;
		for (int i = 0; i < names.length; i++) {
			map = new HashMap<String,Object>();
			map.put("name", names[i]);
			fileNames.add(map);
		}
		SimpleAdapter adapter = new SimpleAdapter(this
				, fileNames 
				, R.layout.videorow
				, new String[]{"name"}
				, new int[]{R.id.videoname});
		
		listView.setAdapter(adapter);
		//点击列表进入播放界面
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position,long l) {
				TextView tv = (TextView) view.findViewById(R.id.videoname);
				Intent intent = new Intent();
				Bundle bundle = new Bundle();  
				bundle.putString("filename", tv.getText().toString());
//				Toast.makeText(getApplicationContext(), tv.getTex t().toString(), Toast.LENGTH_SHORT).show();
				//Log.i("sp",tv.getText().toString());
				intent.putExtras(bundle);
				intent.setClass(VideoList.this, PlayVideo.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
				
//				Intent intent = new Intent(Intent.ACTION_VIEW);
//				intent.setDataAndType(Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "V" + File.separator + "/"+String.valueOf(tv.getText())+".mp4"), "video/mp4");
//                startActivity(intent);
			}
		});
	}
	
	private void scannerSD(){
		if(!(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))){
			Toast.makeText(VideoList.this, "没有sd卡", Toast.LENGTH_SHORT).show();
			finish();
		}
		String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
		File file = new File(absolutePath+"/V");
		files = file.listFiles();
	}
	
}