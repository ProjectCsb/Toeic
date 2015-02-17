package com.example.vocabulalytest.activity;

import android.support.v4.app.Fragment;

public interface OnClickStartListener {
	
	public void startTest(Fragment fragment,int mode);
	public void startMenu(Fragment fragment);
	public void startResult(Fragment fragment);
	public void startSection(Fragment fragment);
	public void startWeek(Fragment fragment);

}
