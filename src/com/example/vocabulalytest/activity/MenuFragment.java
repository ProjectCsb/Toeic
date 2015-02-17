package com.example.vocabulalytest.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.vocabulatytest.R;

public class MenuFragment extends Fragment {

	View mView;
	Button mButtonRandom;
	Button mButtonSection;
	Button mButtonWeek;
	Activity mActivity;

	public MenuFragment() {

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO 自動生成されたメソッド・スタブ
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);

	}

	@Override
	public void onDestroy() {
		// TODO 自動生成されたメソッド・スタブ
		super.onDestroy();
	}

	@Override
	public void onDestroyView() {
		// TODO 自動生成されたメソッド・スタブ
		super.onDestroyView();
	}

	@Override
	public void onPause() {
		// TODO 自動生成されたメソッド・スタブ
		super.onPause();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ

		mView = inflater.inflate(R.layout.layout_menu, container, false);
		mButtonRandom = (Button) mView.findViewById(R.id.start_button);
		mButtonSection = (Button) mView.findViewById(R.id.section_button);
		mButtonWeek = (Button) mView.findViewById(R.id.week_button);
		ButtonListener lis = new ButtonListener();
		mButtonRandom.setOnClickListener(lis);
		mButtonSection.setOnClickListener(lis);
		mButtonWeek.setOnClickListener(lis);
		return mView;
	}

	private void testStart() {
		Activity activity = getActivity();
		if (activity instanceof OnClickStartListener) {
			OnClickStartListener listener = (OnClickStartListener) activity;
			listener.startTest(this, 0);
		}

	}
	
	private void sectionStart() {
		Activity activity = getActivity();
		if (activity instanceof OnClickStartListener) {
			OnClickStartListener listener = (OnClickStartListener) activity;
			listener.startSection(this);
		}

	}
	
	private void weekStart() {
		Activity activity = getActivity();
		if (activity instanceof OnClickStartListener) {
			OnClickStartListener listener = (OnClickStartListener) activity;
			listener.startWeek(this);
		}

	}

	class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View view) {
			// TODO 自動生成されたメソッド・スタブ
			int id = view.getId();
			switch (id) {
			case R.id.start_button:
				testStart();
				break;
			case R.id.section_button:
				sectionStart();
				break;
			case R.id.week_button:
				weekStart();
				break;
			default:
				break;
			}
		}

	}

}
