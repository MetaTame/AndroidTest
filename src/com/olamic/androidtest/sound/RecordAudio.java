package com.olamic.androidtest.sound;

import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

public class RecordAudio extends AsyncTask <Void,double[],Void>{

	private int blocksize;
	private int frequencyHz;
	private int audioFormat;
	private int encoding;
	private TextView currentNoiseTxt;
	private TextView peakNoiseTxt;
	private boolean started;
	private double peak = -1;

	public RecordAudio(int blocksize, int frequencyHz, int audioFormat, int encoding) {
		this.blocksize = blocksize;
		this.frequencyHz = frequencyHz;
		this.audioFormat = audioFormat;
		this.encoding = encoding;
	}

	@Override
	protected Void doInBackground(Void... params) {
		try{
			started = true;
			
			int bufferSize = AudioRecord.getMinBufferSize(frequencyHz,audioFormat,encoding);
			AudioRecord audioRecord = new AudioRecord( MediaRecorder.AudioSource.MIC, frequencyHz, audioFormat, encoding, bufferSize);

			short[] buffer = new short[blocksize];
			double[] meter = new double[blocksize];

			audioRecord.startRecording();

			while(started){
				int noOfReads = audioRecord.read(buffer, 0, blocksize);

				for (int i = 0; i < blocksize && i < noOfReads; i++) { 
					meter[i] = (double) buffer[i] / 32768.0; // signed 16 bit
				}
				
				// don't publish progress so much
				publishProgress(meter);
				
				Thread.sleep(200);
			}
			audioRecord.stop();

		}catch (Throwable t) {
			Log.e("AudioRecord","RecordingFail");
		}

		return null;
	}
	
	public void setCurrentTextView(TextView txt){
		this.currentNoiseTxt = txt;
	}
	
	public void setPeakTextView(TextView txt){
		this.peakNoiseTxt = txt;
	}

	@Override
	protected void onProgressUpdate(double[]... meter) {

		for(int i = 0 ; i < meter.length ; i++){
			double volumeSnapshot = meter[i][0]; // get first read of sample, maybe...
			currentNoiseTxt.setText(Double.toString(volumeSnapshot));
			
			if(volumeSnapshot > peak){
				peak = volumeSnapshot;
				
				peakNoiseTxt.setText(Double.toString(peak));			
			}
		}

	}

	public void stopRecording() {
		started = false;		
	}

	public boolean isStarted() {
		return started;
	}

}
