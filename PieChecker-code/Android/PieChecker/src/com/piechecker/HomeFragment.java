/*
 * Fragment creates a text view with descriptive text
 * and then uses an adapter to display the images with text
 */
package com.piechecker;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class HomeFragment extends Fragment {

	TextView welcomeText;
	ListView list;
	
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		

		View v = inflater.inflate(R.layout.fragment_home, container, false);
	;
		
		 
		welcomeText = (TextView) v.findViewById(R.id.welcome_text);
		welcomeText
				.setText("When baking, you usually stand in front of the oven constantly to check the food by"
						+ " looking through the oven windows. Fruit cakes has provided the luxury of your new "
						+ "experience with the Pie Checker. This device can be attached to your oven and will"
						+ " monitor your food, and relay this information to any of your devices. Allowing you"
						+ " to enjoy the freedom of movement while waiting for your food to bake.");
		list = (ListView) v.findViewById(R.id.home_page_list);
		list.setAdapter(new ImageListAdapter(v.getContext()));
		
		return v;
	}
	
}
