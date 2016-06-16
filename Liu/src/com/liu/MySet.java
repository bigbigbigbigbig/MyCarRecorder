package com.liu;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.provider.Settings;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Switch;
import android.widget.Toast;

public class MySet extends Activity {
	
	public int voiceflag=0;//声音开关标记
	public int dpiflag=2;//视频分辨率标记
	public int longthflag=0;//视频长度
	
	//使用sharedprefences存储数据,位置为/data/data/<package name>/shared_prefs目录下
	public SharedPreferences sharedprefances;
	public Editor editor;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.myset);
        RadioGroup group=(RadioGroup)findViewById(R.id.radioGroup1);
        RadioGroup group2=(RadioGroup)findViewById(R.id.radioGroup2);
        SeekBar seekbar=(SeekBar)findViewById(R.id.seekBar1);
        Switch switch1=(Switch)findViewById(R.id.switch1);
        
        sharedprefances=getSharedPreferences("lwj", Context.MODE_PRIVATE);
        editor=sharedprefances.edit();
        
        // 进度条绑定最大亮度255  
        seekbar.setMax(255);  
        // 取得当前亮度  
        int normal = Settings.System.getInt(getContentResolver(),  
                Settings.System.SCREEN_BRIGHTNESS, 255);  
        // 进度条绑定当前亮度  
        seekbar.setProgress(normal);  
        
        seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				// 取得当前进度  
                int tmpInt = seekBar.getProgress();  
  
                // 当进度小于20时，设置成20，防止太黑看不见的后果。  
                if (tmpInt < 20) {  
                    tmpInt = 20;  
                }  
  
                // 根据当前进度改变亮度  
                Settings.System.putInt(getContentResolver(),  
                        Settings.System.SCREEN_BRIGHTNESS, tmpInt);  
                tmpInt = Settings.System.getInt(getContentResolver(),  
                        Settings.System.SCREEN_BRIGHTNESS, -1);  
                WindowManager.LayoutParams wl = getWindow().getAttributes();  
  
                float tmpFloat = (float) tmpInt / 255;  
                if (tmpFloat > 0 && tmpFloat <= 1) {  
                    wl.screenBrightness = tmpFloat;  
                }  
                getWindow().setAttributes(wl);  
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				// TODO Auto-generated method stub
				
			}
		});
        
        //屏幕分辨率选择
        group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				//获取变更后的选中项的ID
				int radioButtonId = group.getCheckedRadioButtonId();
				//根据ID获取RadioButton的实例
				RadioButton rb = (RadioButton)findViewById(radioButtonId);
				switch (rb.getId()) {
				case R.id.radio0:
					dpiflag=0;
					Toast.makeText(getApplicationContext(),"分辨率设置为400*300", Toast.LENGTH_SHORT).show();
					break;
				case R.id.radio1:
					dpiflag=1;
					Toast.makeText(getApplicationContext(),"分辨率设置为480*800", Toast.LENGTH_SHORT).show();
					break;
				case R.id.radio2:
					dpiflag=2;
					Toast.makeText(getApplicationContext(),"分辨率设置为1280*720", Toast.LENGTH_SHORT).show();
					break;
				default:
					break;
				}
				
				editor.putInt("dpiflag", dpiflag).commit();
			}
		});
        //视频长短选择
        group2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				//获取变更后的选中项的ID
				int radioButtonId = group.getCheckedRadioButtonId();
				//根据ID获取RadioButton的实例
				RadioButton rb2 = (RadioButton)findViewById(radioButtonId);
				switch (rb2.getId()) {
				case R.id.radio_slow:
					longthflag=0;
					Toast.makeText(getApplicationContext(),"视频长度设为10秒", Toast.LENGTH_SHORT).show();
					break;
				case R.id.radio_medium:
					longthflag=1;
					Toast.makeText(getApplicationContext(),"视频长度设为60秒", Toast.LENGTH_SHORT).show();
					break;
				case R.id.radio_long:
					longthflag=2;
					Toast.makeText(getApplicationContext(),"视频长度设为5分钟", Toast.LENGTH_SHORT).show();
					break;
				default:
					break;
				}
				editor.putInt("longthflag", longthflag).commit();
			}
		});
        //声音录制选择
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() 
        {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					voiceflag=0;
					Toast.makeText(getApplicationContext(),"声音已打开", Toast.LENGTH_SHORT).show();
				}
				else {
					voiceflag=1;
					Toast.makeText(getApplicationContext(),"声音已关闭", Toast.LENGTH_SHORT).show();
				}
				
				editor.putInt("voiceflag", voiceflag).commit();
			}
		});
	}

}
