package edu.berkeley.cs160;

import android.app.Activity;
import android.os.Bundle;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.graphics.drawable.Drawable;
//import android.graphics.drawable.shapes.RectShape;
//import android.graphics.*;
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
        /*
        setContentView(R.layout.main);
        RectShape r = new RectShape();
        Canvas c = new Canvas();
        Paint p = new Paint();
        p.setColor(Color.rgb(237, 28, 36));
        r.draw(c, p);
        */
        /*
        TextView tv = new TextView(this);
        tv.setText("Hello world!!!");
        
        Bitmap haha = BitmapFactory.decodeFile("C:\\Users\\Minkai\\eclipse workspace\\ace_medic\\src\\am\\p\\blah.bmp");
        if(haha == null){
        	tv.append("null!");
        }
        */
        //int h = haha.getHeight();
        //String h2 = Integer.toString(h);
        //ImageView iv = new ImageView(this);
        //iv.setImageBitmap( haha );
        
        //tv.append(h2);
        //tv.setTextSize(20);
        //setContentView(new MySurfaceView(this));
        setContentView(R.layout.main);
        //setContentView(tv);
        
        //Drawable d = Drawable.createFromPath("blah.bmp");
        //am_title.setImageDrawable(d);
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