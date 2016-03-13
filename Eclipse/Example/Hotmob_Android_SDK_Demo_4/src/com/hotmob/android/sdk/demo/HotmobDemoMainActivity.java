package com.hotmob.android.sdk.demo;

import com.hotmob.sdk.manager.HotmobManager;
import com.hotmob.sdk.manager.HotmobManagerListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class HotmobDemoMainActivity extends HotmobDemoBaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hotmob_demo_main);
		HotmobManager.start(this);
		HotmobManager.setDebug(true);
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		HotmobManagerListener listener = new HotmobManagerListener() {
		     @Override
		     public void didLoadBanner(View bannerView) {
		         
		     }
		};
		
		HotmobManager.getPopup(this, listener, "MainActivityPopup", "hotmob_uat_android_image_inapp_popup", true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hotmob_demo_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
