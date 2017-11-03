package com.nas.banksampah;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class TabunganAnggota extends ActionBarActivity {
	EditText editTextNik;
    Button cek;
    private JSONObject jObject;
	private String jsonResult ="";
	private String url = "http://192.168.55.2/banksampah/setor/daftarsetoranggota.php";
	String[] daftarid;
	String[] daftarnik;
	String[] daftarnama;
	String[] daftartgl;
	String[] daftarqplastik;
	String[] daftarqbesi;
	String[] daftartotal;
	public String a;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tabungan_anggota);
		editTextNik = (EditText) findViewById(R.id.editTextNik);
        cek= (Button) findViewById(R.id.buttonGet);
 
        cek.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				RefreshList();
				
			}
		});
	}
	
	public void RefreshList() {
		a = editTextNik.getText().toString();
    	try {
        	jsonResult = getRequest(url + "?nik=" + a); 
        	
        	jObject = new JSONObject(jsonResult);
			JSONArray menuitemArray = jObject.getJSONArray("setor");

			daftarid = new String[menuitemArray.length()];
			daftartgl = new String[menuitemArray.length()];
			daftarnik = new String[menuitemArray.length()];
			daftarnama = new String[menuitemArray.length()];
			daftarqplastik = new String[menuitemArray.length()];
			daftarqbesi = new String[menuitemArray.length()];
			daftartotal = new String[menuitemArray.length()];

			for (int i = 0; i < menuitemArray.length(); i++)
			{
				daftarid[i] = menuitemArray.getJSONObject(i).getString("id").toString();
				daftartgl[i] = menuitemArray.getJSONObject(i).getString("tglsetor").toString();
				daftarnik[i] = menuitemArray.getJSONObject(i).getString("nik").toString();
				daftarnama[i] = menuitemArray.getJSONObject(i).getString("nama").toString();
				daftarqplastik[i] = menuitemArray.getJSONObject(i).getString("qtyplastik").toString();
				daftarqbesi[i] = menuitemArray.getJSONObject(i).getString("qtybesi").toString();
				daftartotal[i] = menuitemArray.getJSONObject(i).getString("total").toString();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ListView ListView01 = (ListView)findViewById(R.id.listView1);
        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftartgl));
        
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				final String selectionid = daftarid[arg2];
				final String selectiontgl = daftartgl[arg2];
				final String selectionnik = daftarnik[arg2]; 
				final String selectionnama = daftarnama[arg2]; 
				final String selectionqplastik = daftarqplastik[arg2]; 
				final String selectionqbesi = daftarqbesi[arg2];
				final String selectiontotal = daftartotal[arg2];
		    	final CharSequence[] dialogitem = {"Tampil", "Exit"};
		    	AlertDialog.Builder builder = new AlertDialog.Builder(TabunganAnggota.this);
		        builder.setTitle("Menu");
		        builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {
						switch(item){
						case 0 :
							Intent i = new Intent(getApplicationContext(), TampilDataSetor.class);  
							i.putExtra("id", selectionid);
							i.putExtra("tglsetor", selectiontgl);
							i.putExtra("nik", selectionnik);
							i.putExtra("nama", selectionnama);
							i.putExtra("qtyplastik", selectionqplastik);
							i.putExtra("qtybesi", selectionqbesi);
							i.putExtra("total", selectiontotal);
					    	startActivity(i);
							
							break;
						case 1 :
							finish();
							
							break;
						}
					}
				});
		        builder.create().show();
			}});

        ((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();
        
	}
	
	
	public String getRequest(String Url){

		String sret="";
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(Url);
		try{
			HttpResponse response = client.execute(request);
			sret =request(response);

		}catch(Exception ex){
			Toast.makeText(this,"Gagal "+sret, Toast.LENGTH_SHORT).show();
		}
		return sret;

	}
	/**
	 * Method untuk Menerima data dari server
	 */
	public static String request(HttpResponse response){
		String result = "";
		try{
			InputStream in = response.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder str = new StringBuilder();
			String line = null;
			while((line = reader.readLine()) != null){
				str.append(line + "\n");
			}
			in.close();
			result = str.toString();
		}catch(Exception ex){
			result = "Error";
		}
		return result;
	}

}
