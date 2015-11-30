/*
 * Adapter that scales the referenced images and 
 * displays the image plus the image name
 */

package com.piechecker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageListAdapter extends BaseAdapter {
	Context context;
	public ImageListAdapter(Context context){
		this.context = context;
	}
	@Override
	public int getCount() {
		return mThumbIds.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		 LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         convertView = inflater.inflate(R.layout.home_list_items_layout, null);
         
         ImageView imageView = (ImageView)convertView.findViewById(R.id.home_image);
       
         imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
         TextView tvBottom = (TextView)convertView.findViewById(R.id.home_list_secondLine);
         tvBottom.setText(mThumbIds[position]);
         imageView.setImageResource(mThumbIds[position]);
		return convertView;
	}
	 private Integer[] mThumbIds = {
	    		R.drawable.pie1, R.drawable.pie2,
	            R.drawable.pie3, R.drawable.pie4,
	            R.drawable.pie5, R.drawable.pie6,
	            R.drawable.pie7, R.drawable.pie8,
	            R.drawable.pie9, R.drawable.pie10,
	            R.drawable.pie11, R.drawable.pie12,
	            R.drawable.pie13, R.drawable.pie14,
	            R.drawable.pie15, R.drawable.pie16,
	            R.drawable.pie17, R.drawable.pie18,
	            R.drawable.pie19, R.drawable.pie20,
	            R.drawable.pie21, R.drawable.pie22,
	            R.drawable.pie23, R.drawable.pie24,
	            R.drawable.pie25
	      
	    	
	    };

}
