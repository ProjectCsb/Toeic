package com.example.vocabulalytest.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.vocabulatytest.R;

public class ResultFragment extends Fragment {
	View mView;
	TextView mMother;
	TextView mSon;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onActivityCreated(savedInstanceState);
		mSon.setText(getArguments().getString("son"));
		mMother.setText(getArguments().getString("mother"));
		
		
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
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		mView = inflater.inflate(R.layout.layout_result, container, false);
		mMother = (TextView) mView.findViewById(R.id.slash_mather);
		mSon = (TextView) mView.findViewById(R.id.slash_son);
		
		Button toMenuButton = (Button) mView.findViewById(R.id.go_to_menu);
		toMenuButton.setOnClickListener(new ButtonListener());
		return mView;
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
	
	private void requireMenu(){
		Activity activity = getActivity();
		if(activity instanceof OnClickStartListener){
			OnClickStartListener listener = (OnClickStartListener)activity;
			listener.startMenu(this);
		}
	}
	
	class ButtonListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO 自動生成されたメソッド・スタブ
			requireMenu();
		}
		
	}

}
