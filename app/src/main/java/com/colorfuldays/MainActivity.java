package com.colorfuldays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.colorfuldays.utils.AppUtils;
import com.colorfuldays.utils.LogUtils;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

	private Timer timer = null;
	private TimerTask task = null;
	private int iCounts = 0;
	// change it for test the advertisement is working normal

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		LogUtils.i("<--- MainActivity ---> onCreate MainActivity");

		TextView tvVersion;
		TextView tvDate;
		boolean isAdvPic;
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		LogUtils.isDebug = true;
		LogUtils.TAG = "MainActivity";

		isAdvPic = new Random().nextBoolean();

		if (isAdvPic) {
			setContentView(R.layout.activity_adv);
		} else {
			setContentView(R.layout.activity_main);
			tvVersion = (TextView) findViewById(R.id.tvVersion);
			tvDate = (TextView) findViewById(R.id.tvDate);

			if (AppUtils.getVersionName(this) != null) {
				tvVersion.setText(String.format("%s %s", this.getString(R.string.title_Version),
						AppUtils.getVersionName(this)));
			}

			tvDate.setText(this.getString(R.string.developtime));
		}

	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();
		startTimer();
	}

	// start Timer
	private void startTimer() {
		if (timer == null) {
			timer = new Timer();
			task = new TimerTask() {

				@Override
				public void run() {
					iCounts++;
					LogUtils.i("<--- MainActivity ---> iCounts: " + iCounts);
					if (iCounts == 2) {
						stopTimer();
						Intent itLogin = new Intent(MainActivity.this, LoginActivity.class);
						startActivity(itLogin);
						finish();
						overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
					}
				}
			};
			// delay: 1000ms, per 1000ms run once
			timer.schedule(task, 1000, 1000);
		}
	}

	// stop Timer
	private void stopTimer() {
		if (timer != null) {
			task.cancel();
			timer.cancel();

			task = null;
			timer = null;
		}
	}
}
