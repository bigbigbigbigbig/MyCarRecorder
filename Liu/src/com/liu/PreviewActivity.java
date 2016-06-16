package com.liu;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class PreviewActivity extends Activity {
	 
	String str_path;
	private GridView gridview;
	gridadater adapter;
	
	Bitmap bit;
	 String b= "/mnt/sdcard/V";
	 List<String> listfe;
	 
	 public SharedPreferences sharedprefances;
	 public Editor editor;
	 
	 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedprefances=getSharedPreferences("lwj", Context.MODE_PRIVATE);
        editor=sharedprefances.edit();
        
        setContentView(R.layout.mygridview);
        
        gridview = (GridView) findViewById(R.id.gridview);
        
        listfe = getPictures(b);
        
        adapter = new gridadater(this);
        gridview.setAdapter(adapter);
          
        //点击GridView元素的响应
        gridview.setOnItemClickListener(new OnItemClickListener() {  
  
            @Override  
            public void onItemClick(AdapterView<?> parent, View view,  
                    int position, long id) {  
                //弹出单击GridView元素的位置
                Toast.makeText(getApplicationContext(),listfe.get(position), Toast.LENGTH_SHORT).show(); 
                editor.putString("picpath", listfe.get(position)).commit();
                
                Intent intent = new Intent();
                intent.setClass(PreviewActivity.this, ShowPic.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
            }  
        });  
    }
    /**
     * 获取sd卡对应目录下的所有图片
     * @param strPath
     * @return
     */
    public List<String> getPictures(final String strPath) { 
        List<String> list = new ArrayList<String>(); 
        File file = new File(strPath); 
        File[] allfiles = file.listFiles(); 
        if (allfiles == null) { 
        	Toast.makeText(getApplicationContext(),  "该目录下没有图片", Toast.LENGTH_SHORT).show();
          return null; 
        } 
        else{
        	for(int k = 0; k < allfiles.length; k++) { 
                final File fi = allfiles[k]; 
                if(fi.isFile()) { 
                        int idx = fi.getPath().lastIndexOf("."); 
                        if (idx <= 0) { 
                            continue; 
                        } 
                        String suffix = fi.getPath().substring(idx); 
                        if (suffix.toLowerCase().equals(".jpg") || 
                            suffix.toLowerCase().equals(".jpeg") || 
                            suffix.toLowerCase().equals(".bmp") || 
                            suffix.toLowerCase().equals(".png") || 
                            suffix.toLowerCase().equals(".gif") ) { 
                            list.add(fi.getPath()); 
                        } 
                } 
             } 
             Log.d("123", "list:"+list.get(0).toString());
             return list;
        }
         
     }
    
    public class gridadater extends BaseAdapter{

    	private Context context;
    	
    	public gridadater(Context context){
    		this.context = context;
    	}
    	
		@Override
		public int getCount() {
			return listfe.size();
		}

		@Override
		public Object getItem(int position) {
			return listfe.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			View view = LayoutInflater.from(context).inflate(R.layout.showpic, null);
			ImageView images = (ImageView) view.findViewById(R.id.ItemImage);
			bit = BitmapFactory.decodeFile(listfe.get(position).toString());
			images.setImageBitmap(bit);
			return view;
		}
    }
}
