package com.colorfuldays;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.colorfuldays.commu.OptionsUtils;
import com.colorfuldays.commu.ServerUtils;
import com.colorfuldays.db.DbManager;
import com.colorfuldays.db.User;
import com.colorfuldays.utils.LogUtils;
import com.colorfuldays.utils.SPUtils;

public class LoginActivity extends Activity {

	private static final String KEY_IS_SAVE_USER = "isSaveUser";
	private static final String KEY_USER_PIN = "userPin";
	private static final String KEY_USER_PWD = "userPwd";
	private static final String KEY_IS_SAVE_SERVER = "isSaveServer";
	private static final String KEY_SERVER_IP = "serverIP";
	private static final String KEY_SERVER_COMKEY = "serverComkey";
	private static final String KEY_IS_OFFLINE_MODE = "isOfflineMode";
	private EditText edtUser = null;
	private EditText edtPwd = null;
	private Button btnLogin = null;
	private RelativeLayout relayoutServer = null;
	private EditText edtServer = null;
	private EditText edtComkey = null;
	private Button btnTestServer = null;
	private Button btnSaveServer = null;
	private CheckBox cbUser = null;
	private CheckBox cbServer = null;
	private TextView tvSetup = null;
	private ProgressDialog progressDialog = null;
	private SharedPreferences spDefault = null;
	private boolean isSetupClick = true;
	private boolean isServerSetup = false;
	private String serialNumber = "";
	private long lastClickTime = 0;

	public LoginActivity() {

	}

	private void setShowSetupView(boolean isClick) {

		if (isClick) {
			relayoutServer.setVisibility(View.VISIBLE);
			isSetupClick = false;
		} else {
			relayoutServer.setVisibility(View.INVISIBLE);
			isSetupClick = true;
		}
	}

	private void progressSyncDatas() {
		progressDialog = ProgressDialog.show(LoginActivity.this, "Loading",
				"Loading,Please wait.");
		new Thread() {
			public void run() {
				DbManager.syncDeviceInfoToDb(edtServer.getText().toString(),
						edtComkey.getText().toString());
				String tmpSerialNumber = OptionsUtils.getOptions("GetOption", "Name", "~SerialNumber");
				if (null != tmpSerialNumber) {
					serialNumber = tmpSerialNumber;
				}
				DbManager.syncDataToDb();
				progressDialog.dismiss();
			}
		}.start();
	}

	private void initDatas() {
		boolean isOfflineMode;
		// get the data from the SharedPreferences
		cbUser.setChecked((Boolean) SPUtils.get(this, KEY_IS_SAVE_USER, false));
		if (cbUser.isChecked()) {
			edtUser.setText((String) SPUtils.get(this, KEY_USER_PIN, ""));
			edtUser.setSelection(((String) SPUtils.get(this, KEY_USER_PIN, ""))
					.length());
			edtPwd.setText((String) SPUtils.get(this, KEY_USER_PWD, ""));
		}

		cbServer.setChecked((Boolean) SPUtils.get(this, KEY_IS_SAVE_SERVER,
				false));
		if (cbServer.isChecked()) {
			edtServer.setText((String) SPUtils.get(this, KEY_SERVER_IP,
					"192.168.1.201"));
			edtComkey.setText((String) SPUtils
					.get(this, KEY_SERVER_COMKEY, "0"));

			spDefault = PreferenceManager.getDefaultSharedPreferences(this);
			isOfflineMode = spDefault.getBoolean(KEY_IS_OFFLINE_MODE, false);

			// not choose the offline mode
			if (!isOfflineMode) {
				if (ServerUtils.saveServer((String) SPUtils.get(this, KEY_SERVER_IP, "192.168.1.201"),
						(String) SPUtils.get(this, KEY_SERVER_COMKEY, "0"))) {
					progressSyncDatas();
					isServerSetup = true;
				} else {
					// whether to still use under the offline mode
					new AlertDialog.Builder(this)
							.setTitle("Warnning")
							.setMessage("Do you want to still use under offline mode ?")
							.setPositiveButton("Yes",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog,int whichButton) {
											spDefault.edit().putBoolean(
													KEY_IS_OFFLINE_MODE,
													true).apply();
											isServerSetup = true;
										}
									})
							.setNegativeButton("No",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog,int whichButton) {
											DbManager.closeDbHandle();
											finish();
										}
									}).show();
				}
			} else {
				isServerSetup = true;
			}
		}
	}

	private void findViewProc() {
		LogUtils.i("<--- LoginActivity ---> FindViewProc");
		edtUser = (EditText) findViewById(R.id.edtUser);
		edtPwd = (EditText) findViewById(R.id.edtPassward);
		cbUser = (CheckBox) findViewById(R.id.cbRemUser);
		btnLogin = (Button) findViewById(R.id.btnLogin);

		relayoutServer = (RelativeLayout) findViewById(R.id.RelativeLayout_Server);
		edtServer = (EditText) findViewById(R.id.edtIPaddress);
		edtComkey = (EditText) findViewById(R.id.edtComKey);
		cbServer = (CheckBox) findViewById(R.id.cbRemServer);
		btnTestServer = (Button) findViewById(R.id.btnTest);
		btnSaveServer = (Button) findViewById(R.id.btnSave);
		tvSetup = (TextView) findViewById(R.id.tvSetup);

	}

	private void runViewProc() {
		LogUtils.i("<--- LoginActivity ---> RunViewProc");

		// login in the next activity
		btnLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isServerSetup) {
					Toast.makeText(getApplicationContext(),
							"Please set the server below !!!", Toast.LENGTH_LONG)
							.show();
					setShowSetupView(true);// set setup view VISIBLE

				} else if ("".equals(edtUser.getText().toString())
						|| "".equals(edtPwd.getText().toString())) {
					Toast.makeText(getApplicationContext(),
							"Please input the user id or password !!!",
							Toast.LENGTH_LONG).show();
				} else {
					String userPin = edtUser.getText().toString();
					String userPwd = edtPwd.getText().toString();
					User tmpUser = null;
					try {
						tmpUser = DbManager.queryUsers(userPin).get(0);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (null != tmpUser && tmpUser.getPassword().equals(userPwd)) {
						if (cbUser.isChecked()) {
							SPUtils.put(LoginActivity.this, KEY_IS_SAVE_USER, true);
							SPUtils.put(LoginActivity.this, KEY_USER_PIN, userPin);
							SPUtils.put(LoginActivity.this, KEY_USER_PWD, userPwd);
						} else {
							SPUtils.put(LoginActivity.this, KEY_IS_SAVE_USER, false);
						}

						Intent intentLogin = new Intent();
						intentLogin.setClass(LoginActivity.this, MainMenu.class);
						intentLogin.putExtra("UserPIN", tmpUser.getUserPIN());
						intentLogin.putExtra("UserPrivilege",
								String.valueOf(tmpUser.getPrivilege()));
						intentLogin.putExtra("serialNumber", serialNumber);
						startActivity(intentLogin);
						/**
						 * if login success so it can't return back to this activity
						 * unless change a new account to login again
						 */
						finish();
					} else {
						Toast.makeText(getApplicationContext(),
								"Login failed !!!", Toast.LENGTH_LONG)
								.show();
					}
				}
			}
		});

		// test server configure
		btnTestServer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if ("".equals(edtServer.getText().toString())
						|| "".equals(edtComkey.getText().toString())) {
					Toast.makeText(getApplicationContext(),
							"Please input IP Address and Comkey !!!",
							Toast.LENGTH_LONG).show();
				} else {
					String serverIP = edtServer.getText().toString();
					String serverComKey = edtComkey.getText().toString();

					if (!ServerUtils.testServer(serverIP, serverComKey)) {
						Toast.makeText(getApplicationContext(),
								"IP or Comkey wrong !!!", Toast.LENGTH_LONG)
								.show();
					} else {
						Toast.makeText(getApplicationContext(),
								"Connect to Server successful !!!", Toast.LENGTH_LONG)
								.show();
					}
				}
			}
		});

		// save server configure
		btnSaveServer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if ("".equals(edtServer.getText().toString())
						|| "".equals(edtComkey.getText().toString())) {
					Toast.makeText(getApplicationContext(),
							"Please input IP Address and Comkey !!!",
							Toast.LENGTH_LONG).show();
					isServerSetup = false;
				} else {
					String serverIP = edtServer.getText().toString();
					String serverComKey = edtComkey.getText().toString();

					boolean IsSaveServer = ServerUtils.saveServer(serverIP, serverComKey);

					if (IsSaveServer) {
						if (cbServer.isChecked()) {
							SPUtils.put(LoginActivity.this, KEY_IS_SAVE_SERVER, true);
							SPUtils.put(LoginActivity.this, KEY_SERVER_IP, serverIP);
							SPUtils.put(LoginActivity.this, KEY_SERVER_COMKEY, serverComKey);
						} else {
							SPUtils.put(LoginActivity.this, KEY_IS_SAVE_SERVER, false);
						}
						progressSyncDatas();
						isServerSetup = true;
						setShowSetupView(false);// set setup view INVISIBLE
					} else {
						Toast.makeText(getApplicationContext(),
								"IP or Comkey wrong !!!", Toast.LENGTH_LONG)
								.show();
						isServerSetup = false;
					}
				}
			}
		});

		// by click the setup to show the server set's feature
		tvSetup.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setShowSetupView(isSetupClick);
			}
		});
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// LogUtils.isDebug = true;
		// LogUtils.TAG = "LoginActivity";

		LogUtils.i("<--- LoginActivity ---> onCreate LoginActivity");
		DbManager.initDbHandle(this);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		LogUtils.i("<--- LoginActivity ---> " + this.getIntent().getDataString());

		findViewProc();
		initDatas();
		runViewProc();
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
				Toast.makeText(this, "click again to exit app !!!",
						Toast.LENGTH_SHORT).show();
				lastClickTime = currentClickTime;
			}
		}
	}
}
