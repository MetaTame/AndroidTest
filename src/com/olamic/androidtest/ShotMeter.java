package com.olamic.androidtest;

import com.olamic.androidtest.sound.RecordAudio;

import android.media.AudioFormat;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ShotMeter extends Activity implements OnClickListener {

	RecordAudio record;
	TextView txt;
	Button start;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shot_meter);
		start = (Button)findViewById(R.id.button1);

		start.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		if(record == null){
			record = new RecordAudio(256, 600, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
			record.setTextView((TextView)findViewById(R.id.textView1));
			record.execute();
			
		} else if (record.isStarted()){
			record.stopRecording();
			record.cancel(true);
		} else {
			record.execute();
		}
	}
}
