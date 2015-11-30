/*
 * creates a video view with a controller and 
 * streams the contents of the video path
 */

package com.piechecker;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

public class MyVideoFragment extends Fragment {
	VideoView video;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View v = inflater.inflate(R.layout.fragment_my_video, container, false);
		video = (VideoView)v.findViewById(R.id.videoView1);
		
		MediaController mc = new MediaController(v.getContext());
		mc.setAnchorView(video);
		mc.setMediaPlayer(video);
		
		
		video.setMediaController(mc);
		
		video.requestFocus();
		// sample stream from Apple
		video.setVideoPath("http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8");
		// source video location used for the demo
		//video.setVideoPath("/storage/extSdCard/DCIM/Camera/demo2.mp4");
		
				return v;
	}
	
	

		

	
}
