package com.nas.banksampah;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TampilDataSetor extends ActionBarActivity {
	Button btnexit;
	TextView ttgl,tnik,tnama,tqp,tqb,tt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tampil_data_setor);
		ttgl = (TextView) findViewById(R.id.textView4);
        tnik = (TextView) findViewById(R.id.textView5);
        tnama = (TextView) findViewById(R.id.textView6);
        tqp = (TextView) findViewById(R.id.textView8);
        tqb = (TextView) findViewById(R.id.textView10);
        tt = (TextView) findViewById(R.id.textView12);
        btnexit = (Button) findViewById(R.id.button1);
        ttgl.setText(getIntent().getStringExtra("tglsetor"));
        tnik.setText(getIntent().getStringExtra("nik"));
        tnama.setText(getIntent().getStringExtra("nama"));
        tqp.setText(getIntent().getStringExtra("qtyplastik"));
        tqb.setText(getIntent().getStringExtra("qtybesi"));
        tt.setText(getIntent().getStringExtra("total"));
        
        btnexit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
	}
	
}
