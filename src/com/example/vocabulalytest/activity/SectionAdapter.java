package com.example.vocabulalytest.activity;

import java.util.ArrayList;
import java.util.List;

import android.database.sqlite.SQLiteDatabase;
import com.example.vocabulalytest.database.DBDAO;
import com.example.vocabulalytest.database.DBHelper;
import com.example.vocabulalytest.database.DBSectionEntity;
import com.example.vocabulatytest.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by yoshida keisuke on 2015/02/11.
 */
public class SectionAdapter extends BaseAdapter {

	/*
	 * TODO データベースに問い合わせてビューを作成し、getViewで返却させる
	 */
	
	public int SectionNumber;
	private LayoutInflater mInflater;
	private ArrayList<View> mSectionItem;
	private ArrayList<String> mMax;
	private ArrayList<String> mPre;
	
	static class ViewHolder{
		TextView max;
		TextView pre;
		TextView sectionNumber;
	}

	public SectionAdapter(Context context){
		super();
		mInflater= LayoutInflater.from(context);
		mMax = new ArrayList<String>();
		mPre = new ArrayList<String>();
		getSectionData(context);
	}

	@Override
	public int getCount() {
		return mMax.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup viewGroup) {
		ViewHolder holder;
		if(convertView ==null){
			convertView = mInflater.inflate(R.layout.layout_section_item, null);
			holder = new ViewHolder();
			holder.max = (TextView) convertView.findViewById(R.id.section_max_score);
			holder.pre = (TextView) convertView.findViewById(R.id.section_previous_score);
			holder.sectionNumber = (TextView) convertView.findViewById(R.id.section_number);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.max.setText(mMax.get(position));
		holder.pre.setText(mPre.get(position));
		holder.sectionNumber.setText(String.valueOf(position+1));
		
		return convertView;
	}
	
	private void getSectionData(Context context){
		//TODO データベースにアクセスして
//		while(true){
			/*
			 * mMax,mPreをうめる
			 */
			//break;
		//}
        DBHelper helper = new DBHelper(context,null,1);
        SQLiteDatabase db = helper.getReadableDatabase();
        DBDAO dao = new DBDAO(db);
        List<DBSectionEntity> listSec = dao.findSection();
        for(DBSectionEntity ent : listSec){
            mMax.add(String.valueOf(ent.getMaxCorrect()));
            mPre.add(String.valueOf(ent.getCorrect()));

        }
        helper.close();

	}
}
