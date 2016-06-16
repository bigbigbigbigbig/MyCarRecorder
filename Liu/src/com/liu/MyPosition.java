package com.liu;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MyPosition extends Activity {
	public MapView mMapView;
	public BaiduMap mBaiduMap;
	
	boolean isFirstLoc = true; // 是否首次定位

	@Override
	public void onCreate(Bundle savedInstanceState) {
		SDKInitializer.initialize(getApplicationContext());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myposition);

		// 获取地图控件引用
		mMapView = (MapView) findViewById(R.id.bmapView);
		// mBaiduMap = mMapView.getMap();
		mBaiduMap = mMapView.getMap();
		
		Log.e("tag","--------->");
	}

	@Override
	protected void onPause() {
		mMapView.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		mMapView.onResume();
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		mMapView.onDestroy();
		mMapView = null;
		super.onDestroy();
	}

}