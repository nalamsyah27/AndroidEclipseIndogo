package com.nas.banksampah;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;


public class MainActivity extends ActionBarActivity {

	protected boolean _active = true;
    protected int _splashTime = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Thread splashThread = new Thread() {
      	   //Timer Splash
      	   public void run() {
      	    try{
      	     int waited = 0;
      	      while(_active && (waited < _splashTime)) {
      	                         sleep(100);
      	                         if(_active) {
      	                             waited += 100;
      	                         }
      	                     }
      	                 } catch(InterruptedException e) {
      	                     // do nothing
      	                 } finally {
      	                     finish();
      	                     Intent newIntent=new Intent(MainActivity.this, LoginAnggota.class);//pindah Activity Main
      	                     startActivityForResult(newIntent,0);
      	                 }
      	             }
      	         };
      	         splashThread.start();
    }
}
