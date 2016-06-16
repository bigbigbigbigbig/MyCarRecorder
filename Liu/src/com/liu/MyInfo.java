package com.liu;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyInfo extends Activity implements OnClickListener{
	StringBuilder strLog;
	StringBuilder phoneInfo;
	public Button getinfo;
	public Button basicinfo;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.myinfo);
        getinfo=(Button)findViewById(R.id.getinfo);
        basicinfo=(Button)findViewById(R.id.basicinfo);
        
        getinfo.setOnClickListener(this);
        basicinfo.setOnClickListener(this);
        
        getbasiclist();
        getSensorList();
	}
	//获取手机基本信息
	private void  getbasiclist() {
		phoneInfo = new StringBuilder();
		
		phoneInfo.append("Product: " + android.os.Build.PRODUCT + System.getProperty("line.separator"));
        phoneInfo.append( "CPU_ABI: " + android.os.Build.CPU_ABI + System.getProperty("line.separator"));
        phoneInfo.append( "TAGS: " + android.os.Build.TAGS + System.getProperty("line.separator"));
        phoneInfo.append( "VERSION_CODES.BASE: " + android.os.Build.VERSION_CODES.BASE + System.getProperty("line.separator"));
        phoneInfo.append( "MODEL: " + android.os.Build.MODEL + System.getProperty("line.separator"));
        phoneInfo.append( "SDK: " + android.os.Build.VERSION.SDK + System.getProperty("line.separator"));
        phoneInfo.append( "VERSION.RELEASE: " + android.os.Build.VERSION.RELEASE + System.getProperty("line.separator"));
        phoneInfo.append( "DEVICE: " + android.os.Build.DEVICE + System.getProperty("line.separator"));
        phoneInfo.append( "DISPLAY: " + android.os.Build.DISPLAY + System.getProperty("line.separator"));
        phoneInfo.append( "BRAND: " + android.os.Build.BRAND + System.getProperty("line.separator"));
        phoneInfo.append( "BOARD: " + android.os.Build.BOARD + System.getProperty("line.separator"));
        phoneInfo.append( "FINGERPRINT: " + android.os.Build.FINGERPRINT + System.getProperty("line.separator"));
        phoneInfo.append( "ID: " + android.os.Build.ID + System.getProperty("line.separator"));
        phoneInfo.append( "MANUFACTURER: " + android.os.Build.MANUFACTURER + System.getProperty("line.separator"));
        phoneInfo.append( "USER: " + android.os.Build.USER + System.getProperty("line.separator"));       
        
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);  
        phoneInfo.append("DeviceId(IMEI) = " + tm.getDeviceId() + System.getProperty("line.separator"));  
        phoneInfo.append("DeviceSoftwareVersion = " + tm.getDeviceSoftwareVersion() + System.getProperty("line.separator"));  
        phoneInfo.append("Line1Number = " + tm.getLine1Number() + System.getProperty("line.separator"));  
        phoneInfo.append("NetworkCountryIso = " + tm.getNetworkCountryIso() + System.getProperty("line.separator"));  
        phoneInfo.append("NetworkOperator = " + tm.getNetworkOperator() + System.getProperty("line.separator"));  
        phoneInfo.append("NetworkOperatorName = " + tm.getNetworkOperatorName() + System.getProperty("line.separator"));  
        phoneInfo.append("NetworkType = " + tm.getNetworkType() + System.getProperty("line.separator"));  
        phoneInfo.append("PhoneType = " + tm.getPhoneType() + System.getProperty("line.separator"));  
        phoneInfo.append("SimCountryIso = " + tm.getSimCountryIso() + System.getProperty("line.separator"));  
        phoneInfo.append("SimOperator = " + tm.getSimOperator() + System.getProperty("line.separator"));  
        phoneInfo.append("SimOperatorName = " + tm.getSimOperatorName() + System.getProperty("line.separator"));  
        phoneInfo.append("SimSerialNumber = " + tm.getSimSerialNumber() + System.getProperty("line.separator"));  
        phoneInfo.append("SimState = " + tm.getSimState() + System.getProperty("line.separator"));  
        phoneInfo.append("SubscriberId(IMSI) = " + tm.getSubscriberId() + System.getProperty("line.separator"));  
        phoneInfo.append("VoiceMailNumber = " + tm.getVoiceMailNumber() + System.getProperty("line.separator"));  
	}
	private void getSensorList() {
		// 获取传感器管理器
		SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

		// 获取全部传感器列表
		List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

		// 打印每个传感器信息
		strLog = new StringBuilder();
		int iIndex = 1;
		for (Sensor item : sensors) {
			strLog.append(iIndex + ".");
			strLog.append("	Sensor Type - " + item.getType() + "\r\n");
			strLog.append("	Sensor Name - " + item.getName() + "\r\n");
			strLog.append("	Sensor Version - " + item.getVersion() + "\r\n");
			strLog.append("	Sensor Vendor - " + item.getVendor() + "\r\n");
			strLog.append("	Maximum Range - " + item.getMaximumRange() + "\r\n");
			strLog.append("	Minimum Delay - " + item.getMinDelay() + "\r\n");
			strLog.append("	Power - " + item.getPower() + "\r\n");
			strLog.append("	Resolution - " + item.getResolution() + "\r\n");
			strLog.append("\r\n");
			iIndex++;
		}
		//System.out.println(strLog.toString());
	}
	//弹出对话框1
    public void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MyInfo.this);
        builder.setMessage(strLog.toString());
        builder.setTitle("所有传感器信息");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
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
    //弹出对话框2
    public void dialog2() {
        AlertDialog.Builder builder2 = new AlertDialog.Builder(MyInfo.this);
        builder2.setMessage(phoneInfo.toString());
        builder2.setTitle("手机基本信息");
        builder2.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder2.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder2.create().show();
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.getinfo:
			dialog();
			break;
		case R.id.basicinfo:
			dialog2();
			break;
		default:
			break;
		}
	}
}
