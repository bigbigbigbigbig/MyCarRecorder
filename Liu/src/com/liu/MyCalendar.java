package com.liu;

import android.app.Activity;
import android.os.Bundle;


/**
 * Created by lwj on 2016/5/8.
 */
public class MyCalendar extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycalendar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
