package com.nas.banksampah;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SetorAnggota extends ActionBarActivity {
	Button total,simpan,batal;
	TextView tnik,tnama,tnotlp,talamat;
	EditText tgl,hplastik,hbesi,jplastik,jbesi,jumlah;
	CheckBox cplastik,cbesi;
	private StringBuilder str,ss;
	public String a,b,c,d,e,f,g,h,i;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setor_anggota);
		tnik = (TextView) findViewById(R.id.textView1);
        tnama = (TextView) findViewById(R.id.textView2);
        tnotlp = (TextView) findViewById(R.id.textView3);
        talamat = (TextView) findViewById(R.id.textView4);
        tgl = (EditText) findViewById(R.id.edtgl);
        hplastik = (EditText) findViewById(R.id.edhplastik);
        hbesi = (EditText) findViewById(R.id.edhbesi);
        jplastik = (EditText) findViewById(R.id.edjplastik);
        jbesi = (EditText) findViewById(R.id.edjbesi);
        jumlah = (EditText) findViewById(R.id.edtotal);
        cplastik = (CheckBox) findViewById(R.id.cplastik);
        cbesi = (CheckBox) findViewById(R.id.cbesi);
        total = (Button) findViewById(R.id.btotal);
        simpan = (Button) findViewById(R.id.bsimpan);
        batal = (Button) findViewById(R.id.bbatal);
        
        tnik.setText(getIntent().getStringExtra("nik"));
        tnama.setText(getIntent().getStringExtra("nama"));
        tnotlp.setText(getIntent().getStringExtra("notlp"));
        talamat.setText(getIntent().getStringExtra("alamat"));
        startup();
        tglku();
        
        cplastik.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				str = new StringBuilder();
	               
                if(cplastik.isChecked()){
                    str.append("10000");
                    jplastik.setEnabled(true);
    				jplastik.requestFocus();
                }else {
					jplastik.setText("0");
					hplastik.setText("0");
					jplastik.setEnabled(false);
				}
                               
                hplastik.setText(str);
				
				
			}
		});
        
        cbesi.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ss = new StringBuilder();
	               
                if(cbesi.isChecked()){
                    ss.append("15000");
                    jbesi.setEnabled(true);
    				jbesi.requestFocus();
                }else {
					jbesi.setText("0");
					hbesi.setText("0");
					jbesi.setEnabled(false);
				}
                               
                hbesi.setText(ss);
				
			}
		});
        
        total.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				a=hplastik.getText().toString();
				b=jplastik.getText().toString();
				c=hbesi.getText().toString();
				d=jbesi.getText().toString();
				
				
				Double NilaiA = Double.parseDouble(a);
				Double NilaiB = Double.parseDouble(b);
				Double NilaiC = Double.parseDouble(c);
				Double NilaiD = Double.parseDouble(d);
				
				Double hasil =((NilaiA*NilaiB)+(NilaiC*NilaiD));
				String hasil1=String.valueOf(hasil);
				jumlah.setText(hasil1);
				simpan.setEnabled(true);
				
			}
		});
        
        
        simpan.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int TIMEOUT_MILLISEC = 10000; 
				HttpParams httpParams = new BasicHttpParams();
				HttpConnectionParams.setConnectionTimeout(httpParams, TIMEOUT_MILLISEC);
				HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT_MILLISEC);
				HttpClient client = new DefaultHttpClient(httpParams);
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3); 
				nameValuePairs.add(new BasicNameValuePair("tglsetor", tgl.getText().toString()));
				nameValuePairs.add(new BasicNameValuePair("nik", tnik.getText().toString()));
				nameValuePairs.add(new BasicNameValuePair("nama", tnama.getText().toString()));
				nameValuePairs.add(new BasicNameValuePair("qtyplastik", jplastik.getText().toString()));
				nameValuePairs.add(new BasicNameValuePair("qtybesi", jbesi.getText().toString()));
				nameValuePairs.add(new BasicNameValuePair("total", jumlah.getText().toString()));
				
			    try {
			    	HttpPost request = new HttpPost("http://192.168.55.2/banksampah/setor/addsetor.php");
			    	request.setEntity(new UrlEncodedFormEntity(nameValuePairs));
					
					HttpResponse response = client.execute(request);
				}
				catch(Exception e){
					Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
				Toast.makeText(getApplicationContext(), "Update Berhasil", Toast.LENGTH_LONG).show();
				finish();
				
			}
		});
        
        batal.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ListAnggota.ma.RefreshList();
				finish();
				
			}
		});

        
	}
	
	public void startup(){
		tgl.setEnabled(false);
		jumlah.setEnabled(false);
		simpan.setEnabled(false);
		cplastik.setSelected(false);
		cbesi.setSelected(false);
		hplastik.setEnabled(false);
		jplastik.setEnabled(false);
		hbesi.setEnabled(false);
		jbesi.setEnabled(false);
		hplastik.setText("0");
		jplastik.setText("0");
		hbesi.setText("0");
		jbesi.setText("0");
		jumlah.setText("0");
	}
	
	public void tglku(){
	    Date sss=new Date();
	    SimpleDateFormat k=new SimpleDateFormat("dd/MM/yyyy hh:mm");
	    tgl.setText(k.format(sss));
	    }
	
}
