package com.colorfuldays;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.colorfuldays.adapter.CommonAdapter;
import com.colorfuldays.adapter.ViewHolder;
import com.colorfuldays.db.Attlog;
import com.colorfuldays.db.DbManager;
import com.colorfuldays.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class AttlogFragment extends Fragment {

	final int SAS_PASSWD = 0;
	final int SAS_FINGER = 1;
	final int SAS_CARD = 2;
	final int SAS_PIN = 3;
	final int SAS_PIN_AND_CARD = 5;
	final int SAS_PIN_AND_FP = 9;
	final int SAS_FP_AND_PWD = 10;
	final int SAS_FP_AND_CARD = 12;
	final int SAS_PWD_AND_CARD = 6;
	final int SAS_PIN_AND_PWD_AND_CARD = 7;
	final int SAS_FP_AND_PWD_AND_CARD = 14;
	final int SAS_PIN_AND_FP_AND_PWD = 11;
	final int SAS_PIN_AND_FP_AND_CARD = 13;
	final int SAS_PIN_AND_FP_AND_PWD_AND_CARD = 15;
	final int SAS_FACE = 16;
	final int SAS_FACE_AND_PWD = 18;
	final int SAS_FACE_AND_CARD = 20;
	final int SAS_FACE_AND_FP = 24;
	final int SAS_FACE_AND_FP_AND_PWD = 26;
	final int SAS_FACE_AND_FP_AND_CARD = 28;
	ListView lvAttlog = null;
	CommonAdapter<Attlog> attlogAdapter;
	private List<Attlog> attlogDatas = new ArrayList<>();

	public AttlogFragment() {
		// LogUtils.isDebug = true;
		// LogUtils.TAG = "AttlogFragment";
	}

	private void initDatas() {

		if (Integer.valueOf(MainMenu.getUserPrivilege()) > 0) {
			attlogDatas = DbManager.queryAttlogs("ALL");
		} else {
			attlogDatas = DbManager.queryAttlogs(MainMenu.getUserPin());
		}
	}

	private String getVerifyTypeName(int VerifyType) {

		switch (VerifyType) {
			case SAS_PASSWD:
				return "Pwd";
			case SAS_FINGER:
				return "FP";
			case SAS_CARD:
				return "Card";
			case SAS_PIN:
				return "PIN";
			case SAS_PIN_AND_CARD:
				return "PIN&Card";
			case SAS_PIN_AND_FP:
				return "PIN&FP";
			case SAS_FP_AND_PWD:
				return "FP&Pwd";
			case SAS_FP_AND_CARD:
				return "FP&Card";
			case SAS_PWD_AND_CARD:
				return "Pwd&Card";
			case SAS_PIN_AND_PWD_AND_CARD:
				return "PIN&Pwd&Card";
			case SAS_FP_AND_PWD_AND_CARD:
				return "Fp&Pwd&Card";
			case SAS_PIN_AND_FP_AND_PWD:
				return "PIN&FP&Pwd";
			case SAS_PIN_AND_FP_AND_CARD:
				return "PIN&FP&Card";
			case SAS_PIN_AND_FP_AND_PWD_AND_CARD:
				return "PIN&FP&Pwd&Card";

			case SAS_FACE:
				return "Face";
			case SAS_FACE_AND_PWD:
				return "Face&Pwd";
			case SAS_FACE_AND_CARD:
				return "Face&Card";
			case SAS_FACE_AND_FP:
				return "Face&FP";
			case SAS_FACE_AND_FP_AND_PWD:
				return "Face&FP&Pwd";
			case SAS_FACE_AND_FP_AND_CARD:
				return "Face&FP&Card";
			default:
				return null;
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		LogUtils.i(" <--AttlogFragment--> onCreate");
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
							 @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		LogUtils.i(" <--AttlogFragment--> onCreateView");

		initDatas();
		View viewAttlog = inflater.inflate(R.layout.attlog_fragment, container, false);
		lvAttlog = (ListView) viewAttlog.findViewById(R.id.lvAttlog);
		lvAttlog.setAdapter(attlogAdapter = new CommonAdapter<Attlog>(
				getActivity(), attlogDatas, R.layout.attlog_listview_cell) {

			@Override
			public void convert(ViewHolder helper, Attlog item) {
				helper.setText(R.id.tvUserPIN, "UserPin: " + item.getUserPIN());
				helper.setText(R.id.tvVerifyType, "VFType: "
						+ getVerifyTypeName(item.getVerifyType()));
				helper.setText(R.id.tvVerifyStatus, "Status: " + String.valueOf(item.getStatus()));
				helper.setText(R.id.tvWorkcode, "WorkCode: " + item.getWorkcode());
				helper.setText(R.id.tvVerifyTime, "Time: " + item.getDateTime());
			}

		});

		attlogAdapter.notifyDataSetChanged();
		return viewAttlog;
	}
}
