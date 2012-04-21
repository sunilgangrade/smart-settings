package com.android.smarsettings;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class BatteryStatus extends Activity {

	TextView batteryText;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);

	}

	private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context arg0, Intent intent) {
			// TODO Auto-generated method stub
			Log.e("SUNIL", "onReceive");
			int level = intent.getIntExtra("level", 0);
			batteryText.setText(String.valueOf(level) + "%");
		}
	};

}