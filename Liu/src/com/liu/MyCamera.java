package com.liu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MyCamera extends Activity {

    private SurfaceView sfv_preview;//预览图
    private File dir;//文件流对象
    private Button btn_take;//拍照按钮
    private Button return1;//返回按钮
    private Button watch;
    private Camera camera = null;//摄像头对象
//    private Camera mcamera=null;
//    private ToggleButton toggleshan;
    //打开界面开启预览图
    private SurfaceHolder.Callback cpHolderCallback = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            startPreview();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            stopPreview();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycamera);
        bindViews();

    }
    //对象和控件绑定
    private void bindViews() {
        sfv_preview = (SurfaceView) findViewById(R.id.cameraview);
        btn_take = (Button) findViewById(R.id.pai);
        return1=(Button)findViewById(R.id.return1);
        sfv_preview.getHolder().addCallback(cpHolderCallback);
        watch=(Button)findViewById(R.id.watch);
        
        watch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 Intent intent = new Intent();
	             intent.setClass(MyCamera.this, PreviewActivity.class);
	             intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
	             startActivity(intent);
			}
		});
        //返回主页
        return1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MyCamera.this.finish();
			}
		});
        //拍照功能
        btn_take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera.takePicture(null, null, new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {
                    	File defaultDir = Environment.getExternalStorageDirectory();    
            	        //String path = defaultDir.getAbsolutePath()+File.separator+"V"+File.separator;//创建文件夹存放视频
            	        String path="/mnt/sdcard/V";
                    	dir = new File(path);    
            	        if(!dir.exists()){    
            	            dir.mkdir();    
            	        }
            	       // camera.setDisplayOrientation(90);   //相机选择90度
                        if ((path = saveFile(data)) != null) {
                            Intent it = new Intent(MyCamera.this, PreviewActivity.class);
                            it.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                            it.putExtra(dir.toString(), path);
                            startActivity(it);
                        } else {
                            Toast.makeText(MyCamera.this, "存放照片失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    //保存临时文件
    private String saveFile(byte[] bytes){
    	
        try {
        	//获取当前时间作为文件名
	        Date now = new Date();//声明一个日期
            SimpleDateFormat f2 = new SimpleDateFormat("yyyyMMdd");//添加不同参数，显示不同格式的日期
            SimpleDateFormat f3= new SimpleDateFormat("HHmmss");//显示时间，这里是24小时制
            
//            File file= File.createTempFile( f2.format(now)+"-"+ f3.format(now)+"-", ".jpg",dir);//创建临时文件
            String picpath="/sdcard/V/"+f2.format(now)+"-"+f3.format(now)+".jpg";
            FileOutputStream fos = new FileOutputStream(picpath);
            fos.write(bytes);
            fos.flush();
            fos.close();
            return picpath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dir.toString();
    }


    //开始预览
    private void startPreview(){
        camera = Camera.open();
        try {
            camera.setPreviewDisplay(sfv_preview.getHolder());
            //camera.setDisplayOrientation(90);   //相机旋转90度
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //停止预览
    private void stopPreview() {
        camera.stopPreview();
        camera.release();
        camera = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}