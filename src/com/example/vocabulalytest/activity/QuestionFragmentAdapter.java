package com.example.vocabulalytest.activity;

import java.util.ArrayList;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;

public class QuestionFragmentAdapter extends FragmentStatePagerAdapter{

	ArrayList<Fragment> mQuestionList;
	int currentPosition;
	
	public QuestionFragmentAdapter(FragmentManager fm) {
		super(fm);
		// TODO 自動生成されたコンストラクター・スタブ
		mQuestionList= new ArrayList<Fragment>();
		
	}
	
	public QuestionFragmentAdapter(FragmentManager fm,ArrayList<Fragment> list){
		super(fm);
		mQuestionList = list;
	}

	@Override
	public Fragment getItem(int position) {
		// TODO 自動生成されたメソッド・スタブ
		Fragment flgment = mQuestionList.get(position);
		currentPosition=position;
		
		return flgment;
		
	}

	@Override
	public int getCount() {
		// TODO 自動生成されたメソッド・スタブ
		return mQuestionList.size();
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO 自動生成されたメソッド・スタブ
		super.destroyItem(container, position, object);
	}

	
	
	
	public void addFragment(Fragment question){
		
		mQuestionList.add(question);
	}

	public void removeFragment(){
		mQuestionList.remove(0);
	}
	
	public ArrayList<Fragment> getList(){
		return mQuestionList;
	}

	

	

	/*
	 * OnPageChangeLisnerオーバーライド
	 */
	
	/*@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO 自動生成されたメソッド・スタブ
		
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO 自動生成されたメソッド・スタブ
		if(currentPosition != 0){
			removeFragment();
			notifyDataSetChanged();
		}
		
	}
	*/
	
	
	
	
}
