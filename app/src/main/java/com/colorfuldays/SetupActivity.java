package com.colorfuldays;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class SetupActivity extends PreferenceActivity {

	public SetupActivity() {

	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.settings_preference);
		// setContentView(R.layout.activity_setup);
	}

}
