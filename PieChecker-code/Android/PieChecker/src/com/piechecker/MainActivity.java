/*
 * The activity to hold the fragments. Creates and controls the 
 * drawer layout (menu). Input on the menu causes the fragment manager
 * to swap the fragments. Log.d statements can be ignored. They are part of the attempt to 
 * fix the rotation bug.
 */

package com.piechecker;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	public static final String TAG = "MainActivity";

	private ListView mDrawerList;
	private String[] items = { "Home", "My Video", "Gallery", "Twitch Stream", "Account Settings", "Logout" };
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	private int globalPos = 5;
	private String key = "key";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(savedInstanceState != null){
			globalPos = savedInstanceState.getInt(key,0);
		}
		setContentView(R.layout.activity_main);

		Log.d(TAG,"On Create " + globalPos);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, items));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
			
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				
				invalidateOptionsMenu();
			}

		};
		// Set the drawer toggle as the DrawerListener
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		swapFrag(globalPos);
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		
		return super.onPrepareOptionsMenu(menu);
	}



	public boolean onOptionsItemSelected(MenuItem item) {
		// Pass the event to ActionBarDrawerToggle, if it returns
		// true, then it has handled the app icon touch event
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle your other action bar items...

		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			
			
			/** Swaps fragments in the main content view */
			swapFrag(position);

			// Highlight the selected item, update the title, and close the
			// drawer

		mDrawerLayout.closeDrawer(mDrawerList);

		}
}
		public void swapFrag(int position) {
			globalPos = position;
			Fragment fragment = null;
			ActionBar bar = getActionBar();
			Log.d(TAG,"In swapFrag, globalPos " + globalPos);
			switch (position) {
			case 0:	
				bar.setTitle("Home");
				fragment = new HomeFragment();
				
				break;
			case 1:
				bar.setTitle("My Video");
				fragment = new MyVideoFragment();
				break;
			case 2:
				bar.setTitle("Gallery");
				fragment = new GalleryFragment();
				break;
			case 3:
				bar.setTitle("Twitch Stream");
				fragment = new StreamInFragment();
				break;
			case 4:
				bar.setTitle("Account");
				fragment = new AccountSettingsFragment();
				break;
			case 5:
				fragment = new LoginFragment();
				break;
			default:
				break;
			}
			Log.d(TAG,"In swapFrag, position " + position);
			// Insert the fragment by replacing any existing fragment
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, fragment).commit();
			mDrawerList.setItemChecked(position, true);
			mDrawerLayout.closeDrawer(mDrawerList);
		}
	

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}



	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d(TAG,"SavedState, globalPos = " + globalPos);
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putInt(key, globalPos);
	}

}
