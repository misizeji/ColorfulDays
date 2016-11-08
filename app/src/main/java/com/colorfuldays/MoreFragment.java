package com.colorfuldays;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.colorfuldays.adapter.CommonAdapter;
import com.colorfuldays.adapter.ViewHolder;
import com.colorfuldays.utils.LogUtils;

import java.util.ArrayList;

public class MoreFragment extends Fragment {

	ListView lvMoreInfo = null;

	ArrayList<MoreListCellData> moreListDatas = new ArrayList<>();
	private CommonAdapter<MoreListCellData> moreAdapter;

	public MoreFragment() {
		// LogUtils.isDebug = true;
		// LogUtils.TAG = "MoreFragment";
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		LogUtils.i("<-- MoreFragment --> onCreate");

		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
							 @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		LogUtils.i("<-- MoreFragment --> onCreateView");

		View viewMoreInfo = inflater.inflate(R.layout.more_fragment, container, false);
		lvMoreInfo = (ListView) viewMoreInfo.findViewById(R.id.lvMoreInfo);

		moreListDatas.add(new MoreListCellData(getActivity(),
				R.drawable.more_login_user, "User Info", new Intent(
				getActivity(), UserInfoActivity.class)));
		moreListDatas.add(new MoreListCellData(getActivity(),
				R.drawable.more_device_params, "Device Info", new Intent(
				getActivity(), DeviceInfoActivity.class)));
		moreListDatas.add(new MoreListCellData(getActivity(),
				R.drawable.more_app_settings, "Setup", new Intent(
				getActivity(), SetupActivity.class)));

		lvMoreInfo.setAdapter(moreAdapter = new CommonAdapter<MoreListCellData>(
				getActivity(), moreListDatas, R.layout.more_listview_cell) {

			@Override
			public void convert(ViewHolder helper, MoreListCellData Items) {
				helper.setImageResource(R.id.ivItemImage, Items.getResId());
				helper.setText(R.id.tvItemName, Items.getResName());
			}
		});

		lvMoreInfo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				MoreListCellData data = moreAdapter.getItem(position);
				data.startActivity();
			}
		});
		return viewMoreInfo;
	}
}
