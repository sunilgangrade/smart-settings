package com.android.smarsettings;

import java.text.NumberFormat;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.SavedState;
import android.widget.Toast;

public class SmartSettingsActivity extends Activity {

	public final static Boolean DEBUG = true;
	public final static String TAG = "SmartSettingsActivity";
	TextView sdCardMem;
	TextView battery_Text;
	private BroadcastReceiver mBatInfoReceiver;
	ImageView battery_Btn;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main2);

		GridView gridview = (GridView) findViewById(R.id.gridview);
		gridview.setAdapter(new ImageAdapter(this));
		battery_Text = (TextView) findViewById(R.id.battery_Text);
		battery_Btn = (ImageView) findViewById(R.id.battery_Btn);
		sdCardMem = (TextView) findViewById(R.id.sdcardmem);
		displayMemoryStatus();
		batteryLevel();

		battery_Btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent I1 = new Intent();
				I1.setClassName("com.android.settings",
						"com.android.settings.fuelgauge.PowerUsageSummary");
				v.getContext().startActivity(I1);

			}
		});

	}

	void displayMemoryStatus() {

		String externalStorageState = null;

		// get external storage (SD card) state
		externalStorageState = Environment.getExternalStorageState();

		if (externalStorageState.equals(Environment.MEDIA_MOUNTED)
				|| externalStorageState.equals(Environment.MEDIA_UNMOUNTED)
				|| externalStorageState
						.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
			// obtain the stats from the root of the SD card.
			StatFs stats = new StatFs(Environment.getExternalStorageDirectory()
					.getPath());

			// total usable size
			int totalSize = stats.getBlockCount() * stats.getBlockSize();
			// available free space
			int freeSpace = stats.getAvailableBlocks() * stats.getBlockSize();
			Log.e("SUNIL", "freeSpace" + freeSpace);
			// initialize the NumberFormat object
			NumberFormat numberFormat = NumberFormat.getInstance();
			// disable grouping
			numberFormat.setGroupingUsed(false);
			// display numbers with two decimal places
			numberFormat.setMaximumFractionDigits(2);

			String totalMem = numberFormat
					.format((totalSize / (double) 1073741824));
			String availbaleMem = numberFormat
					.format((freeSpace / (double) 1073741824));

			String outputInfo = totalMem.concat("/" + availbaleMem + "GB");

			sdCardMem.setText(outputInfo);

		}

	}

	private void batteryLevel() {
		BroadcastReceiver batteryLevelReceiver = new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				context.unregisterReceiver(this);
				int rawlevel = intent.getIntExtra("level", -1);
				int scale = intent.getIntExtra("scale", -1);
				int level = -1;
				if (rawlevel >= 0 && scale > 0) {
					level = (rawlevel * 100) / scale;
				}
				battery_Text.setText(+ level + "%");
			}
		};
		IntentFilter batteryLevelFilter = new IntentFilter(
				Intent.ACTION_BATTERY_CHANGED);
		registerReceiver(batteryLevelReceiver, batteryLevelFilter);
	}

}