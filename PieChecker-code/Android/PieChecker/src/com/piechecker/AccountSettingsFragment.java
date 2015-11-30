/*
 * Displays a list of options. Interaction with the options is not implemented
 */
package com.piechecker;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AccountSettingsFragment extends Fragment {
	
	private ListView accountSettings;
	private String[] settingsItems = { "Add or Remove device", "Change password", "Deactivate account" };
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View v = inflater.inflate(R.layout.fragment_account_settings, container, false);
		accountSettings = (ListView) v.findViewById(R.id.listView_accountSettings);
		accountSettings.setAdapter(new ArrayAdapter<String>(v.getContext(),
				R.layout.account_setting_list, settingsItems));
		return v;
	}
}
