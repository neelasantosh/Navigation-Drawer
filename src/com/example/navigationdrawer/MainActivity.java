package com.example.navigationdrawer;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

public class MainActivity extends FragmentActivity {

	FrameLayout drawer;
	FrameLayout containerFrame;
	ListView listviewOptoins;
	String[] options = { "Home", "About Us","Register", "Exit" };
	ArrayAdapter<String> optionAdapter;
	boolean optionEnabled = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		drawer = (FrameLayout) findViewById(R.id.drawerlayout);
		containerFrame = (FrameLayout) findViewById(R.id.containerFrame);
		listviewOptoins = (ListView) findViewById(R.id.listViewoption);
		optionAdapter = new ArrayAdapter<String>(MainActivity.this,
				android.R.layout.simple_list_item_1, options);
		listviewOptoins.setAdapter(optionAdapter);

		// enable actionbar app icon and use
		// as toggle button to show/hide options
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);

		listviewOptoins.setVisibility(View.GONE);

		// handle option selection
		listviewOptoins.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
			
					FragmentManager fm = getSupportFragmentManager();
					FragmentTransaction ft = fm.beginTransaction();
					//drawer.removeAllViews();
					if(arg2 == 1)
					{
						ft.replace(R.id.containerFrame, new AboutUsFragment());
					}
					if(arg2 == 2)
					{
						ft.replace(R.id.containerFrame, new RegisterFragment());
					}
					ft.commit();
					listviewOptoins.setVisibility(View.GONE);
					optionEnabled = false;
			}
		});

		/*
		 * ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,
		 * drawer, R.drawable.ic_launcher, R.string.toggle1, R.string.toggle2);
		 * 
		 * drawer.setDrawerListener(drawerToggle);
		 */
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == android.R.id.home) {
			if (optionEnabled) {
				listviewOptoins.setVisibility(View.GONE);
				optionEnabled = false;

			} else {
				listviewOptoins.setVisibility(View.VISIBLE);
				optionEnabled = true;
				Animation anim = AnimationUtils.loadAnimation(
						MainActivity.this, R.anim.animate_options);
				listviewOptoins.startAnimation(anim);
			}
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
