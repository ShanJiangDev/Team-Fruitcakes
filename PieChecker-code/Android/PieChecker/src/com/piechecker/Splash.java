/*
 * displays a full screen image for 1 second
 * before calling the main activity. Can be used 
 * to initialise data for the app if needed
 */

package com.piechecker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_splash);
		
		Thread timer = new Thread(){
			public void run(){				
					try {
						sleep(1000);
					} catch (InterruptedException e) {						
						e.printStackTrace();
					}finally{
						Intent openStart = new Intent("android.intent.action.MAINACTIVITY");
						startActivity(openStart);
					}
			}	
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		
		super.onPause();
		finish();
	}
	
	

	

}
