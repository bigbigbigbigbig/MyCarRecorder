package com.liu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class MyTeach extends Activity implements OnClickListener{
	private TextView textshow;
	private ImageButton button_reco;
	private ImageButton button_play;
	private ImageButton button_came;
	private ImageButton button_ligh;
	private ImageButton button_cale;
	private ImageButton button_posi;
	private ImageButton button_desc;
	private ImageButton button_out;
	private ImageButton button_set;
	private ImageButton button_info;
	private ImageButton button_about;
	private ImageButton button_move;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myteach);
        textshow=(TextView)findViewById(R.id.show);
        button_reco=(ImageButton)findViewById(R.id.button_record);
        button_play=(ImageButton)findViewById(R.id.button_play);
        button_came=(ImageButton)findViewById(R.id.button_camera);
        button_ligh=(ImageButton)findViewById(R.id.button_light);
        button_posi=(ImageButton)findViewById(R.id.button_position);
        button_cale=(ImageButton)findViewById(R.id.button_calendar);
        button_desc=(ImageButton)findViewById(R.id.button_description);
        button_out=(ImageButton)findViewById(R.id.button_out);
        button_set=(ImageButton)findViewById(R.id.button_set);
        button_info=(ImageButton)findViewById(R.id.button_info);
        button_about=(ImageButton)findViewById(R.id.button_about);
        button_move=(ImageButton)findViewById(R.id.button_move);
        
        button_reco.setOnClickListener(this);
        button_play.setOnClickListener(this);
        button_came.setOnClickListener(this);
        button_ligh.setOnClickListener(this);
        button_posi.setOnClickListener(this);
        button_cale.setOnClickListener(this);
        button_desc.setOnClickListener(this);
        button_out.setOnClickListener(this);
        button_set.setOnClickListener(this);
        button_info.setOnClickListener(this);
        button_about.setOnClickListener(this);
        button_move.setOnClickListener(this);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
			case R.id.button_record:
				textshow.setText(R.string.text_lz);
				break;
			case R.id.button_play:
				textshow.setText(R.string.text_bf);
				break;
			case R.id.button_camera:
				textshow.setText(R.string.text_pz);
				break;
			case R.id.button_light:
				textshow.setText(R.string.text_sd);
				break;
			case R.id.button_position:
				textshow.setText(R.string.text_dw);
				break;
			case R.id.button_calendar:
				textshow.setText(R.string.text_rl);
				break;
			case R.id.button_description:
				textshow.setText(R.string.text_jc);
				break;
			case R.id.button_out:
				textshow.setText(R.string.text_tc);
				break;
			case R.id.button_set:
				textshow.setText(R.string.text_set);
				break;
			case R.id.button_info:
				textshow.setText(R.string.text_info);
				break;
			case R.id.button_about:
				textshow.setText(R.string.text_about);
				break;
			case R.id.button_move:
				textshow.setText(R.string.text_move);
				break;
		}
	}
	
}
