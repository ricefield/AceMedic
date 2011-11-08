package edu.berkeley.cs160;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.ListView;

class globalVar extends Application {

	boolean hasBandageToken = true;
	String[] inv = {"Close"};
	
}

public class ShopActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);        
        setContentView(R.layout.shop);
        ListView lv = (ListView)findViewById(R.id.listView1);
    	lv.setVisibility(View.INVISIBLE);
    }
    
    public globalVar gv = new globalVar();
    
	public void click_leave(View view) {
		startActivity(new Intent(ShopActivity.this, MapActivity.class));
		finish();
		//ListView lv = (ListView)findViewById(R.id.listView1);
    	//lv.setVisibility(View.INVISIBLE);
    	//TextView diag = (TextView) findViewById(R.id.textView1);
    	//Toast.makeText(this, "Pretend window is gone!", Toast.LENGTH_LONG).show();  
    	//diag.setText("I hate you too!");
    }
    public void click_buy(View view) {
    	final ListView lv = (ListView)findViewById(R.id.listView1);
    	lv.setVisibility(View.INVISIBLE);
    	TextView diag = (TextView) findViewById(R.id.textView1);
    	String[] myList = new String[] {"Bandage","Band-Aids","First Aid Kit","Close"};           
        lv.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myList)); 
    	diag.setText("What would you\nlike to buy?");
    	lv.setVisibility(view.VISIBLE);
    	
    	lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
 	       
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { 
            	TextView diag = (TextView) findViewById(R.id.textView1);
            	if (lv.getItemAtPosition(position) == "Bandage" && gv.hasBandageToken){
            		Context context = getApplicationContext();
            		Toast.makeText(context, "You bought a Bandage!", Toast.LENGTH_SHORT).show();
            		gv.hasBandageToken = false; 
            		gv.inv = new String[] {"Bandage","Close"};
            	}
            	else if (lv.getItemAtPosition(position) == "Close"){
            		diag.setText("How can I help you?");
            		lv.setVisibility(View.INVISIBLE);
            	}
            	else{
            		Context context = getApplicationContext();
            		Toast.makeText(context, "Sorry you do not have a token for a " + lv.getItemAtPosition(position), Toast.LENGTH_SHORT).show(); 

            	}
            }
    	});
    }
    public void click_sell(View view) {
    	final ListView lv = (ListView)findViewById(R.id.listView1);
    	lv.setVisibility(View.INVISIBLE);
    	TextView diag = (TextView) findViewById(R.id.textView1);         
        lv.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,gv.inv)); 
    	diag.setText("What would you\nlike to sell?");
    	lv.setVisibility(view.VISIBLE);
    	
    	lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
 	       
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { 
            	TextView diag = (TextView) findViewById(R.id.textView1);
            	if (lv.getItemAtPosition(position) == "Bandage"){
            		Context context = getApplicationContext();
            		gv.hasBandageToken = true; 
            		gv.inv = new String[] {"Close"};
            		lv.setAdapter(new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,gv.inv));
            		Toast.makeText(context, "You sold a Bandage!", Toast.LENGTH_SHORT).show();
            	}
            	else if (lv.getItemAtPosition(position) == "Close"){
            		diag.setText("How can I help you?");
            		lv.setVisibility(View.INVISIBLE);
            	}
            	else{
            		Context context = getApplicationContext();
            		Toast.makeText(context, "Sorry you can not sell a " + lv.getItemAtPosition(position), Toast.LENGTH_SHORT).show(); 

            	}
            }
    	});
    }
}