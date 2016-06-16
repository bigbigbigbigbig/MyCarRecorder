package com.liu;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ToggleButton;

public class MyLight extends Activity implements OnClickListener{
	
	
	private Camera mCamera;
	//声明一个ToggleButton
    private ToggleButton togglebutton; 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.light);
		togglebutton = (ToggleButton) findViewById(R.id.togglebutton1);  
        togglebutton.setOnClickListener(this);
	}
                  
	//打开闪光灯
		public void openlight()
		{
			try{
				  mCamera = Camera.open();
				  Camera.Parameters mParameters;
				  mParameters = mCamera.getParameters();
				  mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
				  mCamera.setParameters(mParameters);
				} catch(Exception ex){}

		}
		//关闭闪光灯
		public void closelight()
		{
			try{
				  Camera.Parameters mParameters;
				  mParameters = mCamera.getParameters();
				  mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
				  mCamera.setParameters(mParameters);
				  mCamera.release();
				} catch(Exception ex){}

		}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		
		case R.id.togglebutton1:
			if (togglebutton.isChecked()) {              
                openlight(); 
                //togglebutton.setBackgroundResource(R.drawable.shou_on);
            }   
            // 当按钮再次点击时的响应事件
            else {              
                closelight();
                //togglebutton.setBackgroundResource(R.drawable.shou_off);
            }
			break;
		}
	}

}
