package com.colorfuldays;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.colorfuldays.adapter.CommonAdapter;
import com.colorfuldays.adapter.ViewHolder;
import com.colorfuldays.db.DbManager;
import com.colorfuldays.db.Device;

import java.util.ArrayList;
import java.util.List;

public class DeviceInfoActivity extends Activity {

	ArrayList<DeviceCellData> deviceListDatas = new ArrayList<>();
	CommonAdapter<DeviceCellData> deviceAdapter;
	private ListView lvDeviceInfo = null;

	public DeviceInfoActivity() {
		// LogUtils.isDebug = true;
		// LogUtils.TAG = "DeviceInfoActivity";
	}

	private void findViewById() {

		lvDeviceInfo = (ListView) findViewById(R.id.lvDeviceInfo);
	}

	private void runViewProc() {
		// in the offline mode, the serial number will be ""
		List<Device> tempDeviceList;
		if (MainMenu.getSerialNumber().equals("")) {
			tempDeviceList = DbManager.queryDeviceInfo("ALL");
		} else {
			tempDeviceList = DbManager.queryDeviceInfo(MainMenu.getSerialNumber());
		}
		deviceListDatas.add(new DeviceCellData("Device Name", tempDeviceList
				.get(0).getDeviceName()));
		deviceListDatas.add(new DeviceCellData("SerialNumber", tempDeviceList
				.get(0).getSerialNumber()));
		deviceListDatas.add(new DeviceCellData("MAC Address", tempDeviceList
				.get(0).getMacAddress()));
		deviceListDatas.add(new DeviceCellData("Users Capacity", tempDeviceList
				.get(0).getMaxUserCounts() + "00"));
		deviceListDatas.add(new DeviceCellData("Fingerprint Capacity",
				tempDeviceList.get(0).getMaxFpCounts() + "00"));
		deviceListDatas.add(new DeviceCellData("Attlog Capacity",
				tempDeviceList.get(0).getMaxAttCounts() + "0000"));
		deviceListDatas.add(new DeviceCellData("FP Algorithm", tempDeviceList
				.get(0).getZKFPVersion() + ".0"));
		deviceListDatas.add(new DeviceCellData("Platform Info", tempDeviceList
				.get(0).getPlatformInfo()));
		deviceListDatas.add(new DeviceCellData("manufacturer", tempDeviceList
				.get(0).getManufacturer()));
		deviceListDatas.add(new DeviceCellData("manufacture Date",
				tempDeviceList.get(0).getManufactureDate()));

		lvDeviceInfo.setAdapter(deviceAdapter = new CommonAdapter<DeviceCellData>(
				this, deviceListDatas, R.layout.device_listview_cell) {
			@Override
			public void convert(ViewHolder helper, DeviceCellData Items) {
				helper.setText(R.id.tvItemName, Items.getItemName());
				helper.setText(R.id.tvItemValue, Items.getItemValue());
			}
		});
		deviceAdapter.notifyDataSetChanged();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_device_info);

		findViewById();
		runViewProc();
	}
}
