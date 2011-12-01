package edu.berkeley.cs160;

import android.app.Activity;
import android.os.Bundle;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;



public class Ace_medicActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);   
        setContentView(R.layout.main);
    }
    
    
    public void startGame(View view){
    	Intent intent = new Intent(this, MapActivity.class);
    	try{
    		startActivity(intent);
    		finish();
    	}
    	catch(ActivityNotFoundException e){
    		
    	}
    }
    
    public void switch_to_manual(View view){
    	Intent intent = new Intent(this, Manual_Activity.class);
    	try{
    		startActivity(intent);
    	}
    	catch(ActivityNotFoundException e){
    		
    	}
    }
    
    public void begin_exit(View view){
    	finish();
    }
    

}

