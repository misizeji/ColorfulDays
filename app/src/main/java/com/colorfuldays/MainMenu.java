package com.colorfuldays;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.colorfuldays.db.DbManager;
import com.colorfuldays.utils.LogUtils;

public class MainMenu extends FragmentActivity {

	public static FragmentManager mainManager = null;
	public static String userPin = "";
	public static String userPrivilege = "";
	public static String serialNumber = "";
	private TextView tvMenu = null, tvAttlog = null, tvNote = null, tvMore = null;
	private long lastClickTime = 0;

	public MainMenu() {

	}

	public static String getUserPin() {
		return userPin;
	}

	public static String getUserPrivilege() {
		return userPrivilege;
	}

	public static String getSerialNumber() {
		return serialNumber;
	}

	public static FragmentManager getMainManager() {
		return mainManager;
	}

	private void FindViewProc() {
		tvMenu = (TextView) findViewById(R.id.tvMenu);
		tvAttlog = (TextView) findViewById(R.id.tvAttlog);
		tvNote = (TextView) findViewById(R.id.tvNote);
		tvMore = (TextView) findViewById(R.id.tvMore);
	}

	private void RunViewProc() {
		tvMenu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				MenuFragment menuFragment = new MenuFragment();
				mainManager.beginTransaction()
						.replace(R.id.RelativeLayout_MainMenu, menuFragment)
						.commit();
			}
		});

		tvAttlog.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AttlogFragment attlogFragment = new AttlogFragment();
				mainManager.beginTransaction()
						.replace(R.id.RelativeLayout_MainMenu, attlogFragment)
						.commit();
			}
		});

		tvNote.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				NoteFragment noteFragment = new NoteFragment();
				mainManager.beginTransaction()
						.replace(R.id.RelativeLayout_MainMenu, noteFragment)
						.commit();
			}
		});

		tvMore.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				MoreFragment moreFragment = new MoreFragment();
				mainManager.beginTransaction()
						.replace(R.id.RelativeLayout_MainMenu, moreFragment)
						.commit();
			}
		});
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		LogUtils.i("<--- MainMenu ---> onCreate MainMenu");
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_mainmenu);

		// LogUtils.isDebug = true;
		// LogUtils.TAG = "MainMenu";

		Intent intentLogin = getIntent();
		userPin = intentLogin.getExtras().getString("UserPIN");
		userPrivilege = intentLogin.getExtras().getString("UserPrivilege");
		serialNumber = intentLogin.getExtras().getString("serialNumber");

		LogUtils.i("<--- MainMenu ---> userPin: " + userPin);
		LogUtils.i("<--- MainMenu ---> Privilege: " + userPrivilege);
		LogUtils.i("<--- MainMenu ---> serialnumber: " + serialNumber);

		FindViewProc();

		mainManager = getSupportFragmentManager();
		MenuFragment menuFragment = new MenuFragment();
		mainManager.beginTransaction().replace(R.id.RelativeLayout_MainMenu, menuFragment).commit();

		RunViewProc();
	}

	@Override
	public void onBackPressed() {

		if (lastClickTime <= 0) {
			Toast.makeText(this, "click again to exit app !!!", Toast.LENGTH_SHORT).show();
			lastClickTime = System.currentTimeMillis();
		} else {
			long currentClickTime = System.currentTimeMillis();
			if (currentClickTime - lastClickTime < 1000) {
				DbManager.closeDbHandle();
				finish();
			} else {
				Toast.makeText(this, "click again to exit app !!!", Toast.LENGTH_SHORT).show();
				lastClickTime = currentClickTime;
			}
		}
	}
}
