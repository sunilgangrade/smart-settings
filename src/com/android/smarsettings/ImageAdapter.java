package com.android.smarsettings;

import java.util.Set;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;




public class ImageAdapter extends BaseAdapter {
	
    ImageView imageView;
    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.sound, R.drawable.sound, R.drawable.wifi,
            R.drawable.wifi, R.drawable.lock, R.drawable.bluetooth, 
            R.drawable.autosync, R.drawable.autosync, R.drawable.lock, 
            R.drawable.lock, R.drawable.lock, R.drawable.networkmode,

    };
	
	
	private static final String TAG = "ImageAdapter";
	private Context mContext;
	

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(128, 128));
            imageView.setClickable(true);
            imageView.setBackgroundColor(0xFF696969);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(8, 8, 8, 8);
            

            imageView.setOnClickListener(new View.OnClickListener() {

              @Override
              public void onClick(View view) {
                Log.e(TAG,"position ["+position+"]");
                switch (position) {
				case 0:
					mContext.startActivity(new Intent(Settings.ACTION_BLUETOOTH_SETTINGS));
					break;
				case 1:
					//mContext.startActivity(new Intent(Settings.ACTION_SOUND_SETTINGS));
					
						//Context mContext = getApplicationContext();
						Dialog dialog = new Dialog(mContext);
						dialog.setContentView(R.layout.dialog);
						dialog.setTitle("Custom Dialog");
						dialog.show();
						dialog.setOnDismissListener(new OnDismissListener() {
							
							public void onDismiss(DialogInterface dialog) {
								// TODO Auto-generated method stub
								
								
							}
						});
					
					break;
				case 2:
					mContext.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
					changeImageColor();
					break;
				case 3:
					mContext.startActivity(new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS));
					break;
				case 4:
					Intent I1 = new Intent();
					I1.setClassName("com.android.settings", "com.android.settings.fuelgauge.PowerUsageSummary");
				    mContext.startActivity(I1);
					break;
				case 5:
					mContext.startActivity(new Intent(Settings.ACTION_DISPLAY_SETTINGS));
					break;
				case 6:
					mContext.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
					break;
				case 7:
					mContext.startActivity(new Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS));
					break;
				case 8:
					mContext.startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS));
					break;
				case 9:
					mContext.startActivity(new Intent(Settings.ACTION_PRIVACY_SETTINGS));
					break;
				case 10:
					mContext.startActivity(new Intent(Settings.ACTION_DISPLAY_SETTINGS));
					break;
				case 11:
					Intent I = new Intent();
					I.setClassName("com.android.settings", "com.android.settings.ChooseLockGeneric");
				    mContext.startActivity(I);
					break;
				case 12:

					break;						

				default:
					break;
				}
                
              }

			private void changeImageColor() {
				// TODO Auto-generated method stub

			}

            });

        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }


    

}

