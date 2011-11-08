package edu.berkeley.cs160;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.webkit.WebView;

public class Manual_Activity extends Activity{
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);   
        //TextView tv = new TextView(this);
        //tv.setText("This is the manual!");
        setContentView(R.layout.manual);
        
        Button back_button = (Button) findViewById(R.id.button1);
        back_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }

        });
        
        WebView webview = (WebView) findViewById(R.id.webView1);
        //String data = "<html><body><h1>First Aid Manual</h1><img src=\"file:///android_asset/blah.bmp\"  /><br /><textarea rows=\"5\" cols=\"30\" disabled=\"disabled\"> To treat a sprained ankle you have to call the FBI. </textarea><form><input type=\"radio\" /> Sprained ankle<br /><input type=\"radio\" /> fever</form></body></html> ";
        //final String mimeType = "text/html";
        //final String encoding = "utf-8";

        //webview.loadData(data, "text/html", null);
        //webview.loadDataWithBaseURL("fake://not/needed", data, mimeType, encoding, "");
        webview.loadUrl("file:///android_asset/manual.html"); 
	}

	@Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.
    }
	
	@Override
    protected void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").
    }

}
