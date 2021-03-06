package com.olamic.androidtest;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	// no comment!
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/** Called when the user clicks the Sound button */
	public void soundButton(View view) {
		Intent intent = new Intent(MainActivity.this, ShotMeter.class);
	    startActivity(intent);
	}
	
	/** Called when the user clicks the Anim button */
	public void animButton(View view) {
		Intent intent = new Intent(MainActivity.this, AnimationTutorial.class);
	    startActivity(intent);
	}
	
	/** Called when the user clicks the Cross button */
	public void crossButton(View view) {
		Intent intent = new Intent(MainActivity.this, CrossHair.class);
	    startActivity(intent);
	}

}
