package com.nas.banksampah;

import java.util.ArrayList;
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
import android.widget.EditText;
import android.widget.Toast;

public class EditAnggota extends ActionBarActivity {
	Button btnedit,btnbatal;
	EditText edalamat,ednik,ednama,edusername,edpassword,ednotlp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_anggota);
		ednik = (EditText) findViewById(R.id.edhplastik);
        ednama = (EditText) findViewById(R.id.edjplastik);
        edusername = (EditText) findViewById(R.id.edhbesi);
        edpassword = (EditText) findViewById(R.id.edjbesi);
        ednotlp = (EditText) findViewById(R.id.edtgl);
        edalamat = (EditText) findViewById(R.id.edtotal);
        
        ednik.setText(getIntent().getStringExtra("nik"));
        ednama.setText(getIntent().getStringExtra("nama"));
        edusername.setText(getIntent().getStringExtra("username"));
        edpassword.setText(getIntent().getStringExtra("password"));
        ednotlp.setText(getIntent().getStringExtra("notlp"));
        edalamat.setText(getIntent().getStringExtra("alamat"));
        
        btnedit = (Button) findViewById(R.id.btotal);
        btnbatal = (Button) findViewById(R.id.bsimpan);
        
        btnedit.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				int TIMEOUT_MILLISEC = 10000; 
				HttpParams httpParams = new BasicHttpParams();
				HttpConnectionParams.setConnectionTimeout(httpParams, TIMEOUT_MILLISEC);
				HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT_MILLISEC);
				HttpClient client = new DefaultHttpClient(httpParams);
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3); 
				nameValuePairs.add(new BasicNameValuePair("id", getIntent().getStringExtra("id")));
				nameValuePairs.add(new BasicNameValuePair("nik", ednik.getText().toString()));
				nameValuePairs.add(new BasicNameValuePair("nama", ednama.getText().toString()));
				nameValuePairs.add(new BasicNameValuePair("username", edusername.getText().toString()));
				nameValuePairs.add(new BasicNameValuePair("password", edpassword.getText().toString()));
				nameValuePairs.add(new BasicNameValuePair("notlp", ednotlp.getText().toString()));
				nameValuePairs.add(new BasicNameValuePair("alamat", edalamat.getText().toString()));
				
			    try {
			    	HttpPost request = new HttpPost("http://192.168.55.2/banksampah/anggota/updateanggota.php");
			    	request.setEntity(new UrlEncodedFormEntity(nameValuePairs));
					
					HttpResponse response = client.execute(request);
				}
				catch(Exception e){
					Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
				Toast.makeText(getApplicationContext(), "Update Berhasil", Toast.LENGTH_LONG).show();
				ListAnggota.ma.RefreshList();
				finish();
			}
			
		});
        
        btnbatal.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ListAnggota.ma.RefreshList();
				finish();
				
			}
		});

	}

}
