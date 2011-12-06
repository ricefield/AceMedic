package edu.berkeley.cs160;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

public class MapActivity extends Activity {

	private static final int TASK_0 = 0;
	private static final int TASK_1 = 1;
	private static final int TASK_2 = 2;
	private static final int TASK_3 = 3;
	private static final int TASK_4 = 4;

	private int taskID = TASK_1;
	private Menu mMenu;
	@Override
	protected void onCreate(Bundle savedState) {
		super.onCreate(savedState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		Bundle extras = getIntent().getExtras();
		if (extras != null){
			if (extras.getString("treatment").equals("treatment")) {
				taskID = TASK_3;
			}
			else if (extras.getString("shop").equals("shop")) {
				taskID = TASK_2;
			}
			else if (extras.getString("manual").equals("manual")) {
				taskID = TASK_1;
			}
		}

		setContentView(R.layout.map);

		Button store = (Button) findViewById(R.id.storeButton);
		store.setOnClickListener(storeClickListener);

		Button field = (Button) findViewById(R.id.fieldButton);
		field.setOnClickListener(fieldClickListener);
		
		Button football = (Button) findViewById(R.id.footballButton);
		football.setOnClickListener(footballClickListener);

		Button menu = (Button) findViewById(R.id.menuButton);
		menu.setOnClickListener(menuClickListener);

	}


	private final OnClickListener storeClickListener = new OnClickListener() {
		public void onClick(View v) {
			MapActivity.this.startActivity(new Intent(MapActivity.this, ShopActivity.class));
			finish();
		}
	};

	private final OnClickListener footballClickListener = new OnClickListener() {
		public void onClick(View v) {
			MapActivity.this.startActivity(new Intent(MapActivity.this, TreatmentActivity.class));
			finish();
		}
	};
	
	private final OnClickListener fieldClickListener = new OnClickListener() {
		public void onClick(View v) {
			MapActivity.this.startActivity(new Intent(MapActivity.this, serious_TreatmentActivity.class));
			finish();
		}
	};

	private final OnClickListener menuClickListener = new OnClickListener() {
		public void onClick(View v) {
			//To-Do
			openOptionsMenu();
		}
	};

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case TASK_0:
			return new AlertDialog.Builder(MapActivity.this)
			.setIcon(R.drawable.star_on)
			.setTitle(R.string.task1)
			.setMessage(R.string.task1_msg)
			.setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {

					/* User clicked OK so do some stuff */
				}
			})
			.create();
		case TASK_1:
			return new AlertDialog.Builder(MapActivity.this)
			.setIcon(R.drawable.star_on)
			.setTitle(R.string.task2)
			.setMessage(R.string.task2_msg)
			.setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {

					/* User clicked OK so do some stuff */
				}
			})
			.create();
		case TASK_2:
			return new AlertDialog.Builder(MapActivity.this)
			.setIcon(R.drawable.star_on)
			.setTitle(R.string.task3)
			.setMessage(R.string.task3_msg)
			.setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {

					/* User clicked OK so do some stuff */
				}
			})
			.create();
		case TASK_3:
			return new AlertDialog.Builder(MapActivity.this)
			.setIcon(R.drawable.star_on)
			.setTitle(R.string.task4)
			.setMessage(R.string.task4_msg)
			.setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {

					/* User clicked OK so do some stuff */
				}
			})
			.create();
		}
		return null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Hold on to this
		mMenu = menu;

		// Inflate the currently selected menu XML resource.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.layout.map_menu, menu);

		return true;
	}


	/*---------------------Menu Stuff----------------
	 * (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// For "Title only": Examples of matching an ID with one assigned in
		//                   the XML
		case R.id.manual:
			MapActivity.this.startActivity(new Intent(MapActivity.this, Manual_Activity.class));
			return true;

		case R.id.tasks:
			//Toast.makeText(this, "Display the Tasks!", Toast.LENGTH_SHORT).show();
			showDialog(taskID);
			return true;

			// For "Groups": Toggle visibility of grouped menu items with
			//               nongrouped menu items
		case R.id.mainPage:
			// The refresh item is part of the browser group
			MapActivity.this.startActivity(new Intent(MapActivity.this, Ace_medicActivity.class));
			finish();
			return true;

		default:
			// None
			break;
		}

		return false;
	}

}