package com.colorfuldays;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.colorfuldays.commu.OthersUtils;
import com.colorfuldays.db.DbManager;
import com.colorfuldays.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class MenuFragment extends Fragment {

	private ProgressDialog progressDialog = null;

	private int resIds[] = {R.drawable.mainmenu_menu_pressed,
			R.drawable.mainmenu_menu_pressed, R.drawable.mainmenu_menu_pressed,
			R.drawable.mainmenu_menu_pressed, R.drawable.mainmenu_menu_pressed,
			R.drawable.mainmenu_menu_pressed, R.drawable.mainmenu_menu_pressed,
			R.drawable.mainmenu_menu_pressed, R.drawable.mainmenu_menu_pressed};
	private String resNames[] = {"Department", "User Mng", "Access", "TCP/IP", "Record",
			"Door Open", "Data Sync", "Time Sync", "Reboot"};

	public MenuFragment() {
		// LogUtils.isDebug = true;
		// LogUtils.TAG = "MenuFragment";
	}

	private void progressSyncDatas(Context context) {
		progressDialog = ProgressDialog.show(context, "Loading", "Loading,Please wait.");
		new Thread() {
			public void run() {
				DbManager.syncDataToDb();
				progressDialog.dismiss();
			}
		}.start();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		LogUtils.i(" <--MenuFragment--> onCreat");
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
							 @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		LogUtils.i(" <--MenuFragment--> onCreateView");
		GridView gridView;

		View viewMenu = inflater.inflate(R.layout.menu_fragment, container, false);
		gridView = (GridView) viewMenu.findViewById(R.id.gvMainMenu);

		// save the resId and resName to the ArrayList to make a Adapter
		ArrayList<HashMap<String, Object>> items = new ArrayList<>();
		for (int i = 0; i < resIds.length; i++) {
			HashMap<String, Object> GridViewItems = new HashMap<>();
			GridViewItems.put("itemImage", resIds[i]);
			GridViewItems.put("itemName", resNames[i]);
			items.add(GridViewItems);
		}

		SimpleAdapter itemGridViewAdapter = new SimpleAdapter(getActivity(),
				items, R.layout.menu_gridview_cell, new String[]{"itemImage",
				"itemName"}, new int[]{R.id.ivItemImage,
				R.id.tvItemName});

		gridView.setAdapter(itemGridViewAdapter);

		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				LogUtils.i("ManuFragment Position is " + String.valueOf(position) + " id is " +
						String.valueOf(id));
				Toast.makeText(parent.getContext(), resNames[position], Toast.LENGTH_SHORT).show();
				switch (position) {
					case 0: // "Department"
						break;
					case 1: // "User Mng"
						break;
					case 2: // "Access"
						break;
					case 3: // "TCP/IP"
						break;
					case 4: // "Record"
						AttlogFragment attlogFragment = new AttlogFragment();
						MainMenu.getMainManager().beginTransaction()
								.replace(R.id.RelativeLayout_MainMenu, attlogFragment)
								.commit();
						break;
					case 5: // "Door Open"
						//"secret for this API"
						break;
					case 6: // "Data Sync"
						progressSyncDatas(parent.getContext());
						break;
					case 7: // "Time Sync"
						//" this function can be cheat by phone's local time"
						break;
					case 8: // "Reboot"
						OthersUtils.restart();
						break;
				}
			}
		});
		return viewMenu;
	}
}
