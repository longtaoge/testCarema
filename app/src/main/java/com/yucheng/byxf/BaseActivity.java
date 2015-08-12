package com.yucheng.byxf;

import com.yucheng.byxf.mini.ui.R;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

public class BaseActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		

	}
}
