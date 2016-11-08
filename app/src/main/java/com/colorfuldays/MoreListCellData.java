package com.colorfuldays;

import android.content.Context;
import android.content.Intent;

public class MoreListCellData {

	private String resName = "";
	private int resId = 0;

	private Context context = null;
	private Intent relatedIntent = null;

	public MoreListCellData(Context context, int resId, String resName, Intent relatedIntent) {
		this.resName = resName;
		this.resId = resId;
		this.context = context;
		this.relatedIntent = relatedIntent;
	}

	public int getResId() {
		return this.resId;
	}

	public String getResName() {
		return resName;
	}

	public Context getContext() {
		return context;
	}

	public Intent getRelatedIntent() {
		return relatedIntent;
	}

	public void startActivity() {
		getContext().startActivity(getRelatedIntent());
	}
}
