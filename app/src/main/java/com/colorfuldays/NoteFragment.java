package com.colorfuldays;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NoteFragment extends Fragment {

	private final String TAG = "NoteFragment";

	public NoteFragment() {

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, "onCreate");

		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
							 @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		Log.i(TAG, "onCreateView");

		return inflater.inflate(R.layout.note_fragment, container, false);
	}
}
