package com.nas.banksampah;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TampilAnggota extends ActionBarActivity {
	Button btnexit;
	TextView tnik,tnama,tnotlp,talamat;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tampil_anggota);
		tnik = (TextView) findViewById(R.id.textView4);
        tnama = (TextView) findViewById(R.id.textView5);
        tnotlp = (TextView) findViewById(R.id.textView6);
        talamat = (TextView) findViewById(R.id.textView8);
        
        tnik.setText(getIntent().getStringExtra("nik"));
        tnama.setText(getIntent().getStringExtra("nama"));
        tnotlp.setText(getIntent().getStringExtra("notlp"));
        talamat.setText(getIntent().getStringExtra("alamat"));
        btnexit = (Button) findViewById(R.id.button1);
        
        btnexit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
	}

	
}
