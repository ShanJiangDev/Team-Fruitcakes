/*
 * Image adapter used by the gallery fragment to correctly
 * scale and fit the images to the display.
 */
package com.piechecker;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
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
    public View getView(int position, View convertView, ViewGroup parent) {
    	
    	
    	
        ImageView imageView;
        
        if (convertView == null) {  // if it's not recycled, initialise some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(280, 280));
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setPadding(8, 8, 8, 8);
            
        } else {
            imageView = (ImageView) convertView;
        }
       imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to the images
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