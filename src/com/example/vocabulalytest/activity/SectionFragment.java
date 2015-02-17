package com.example.vocabulalytest.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.vocabulatytest.R;

/**
 * Created by yoshida keisuke on 2015/02/11.
 */
public class SectionFragment extends Fragment {

    private View mView;
    private SectionAdapter mSectionAdapter;
    private ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.layout_section, container, false);
        mSectionAdapter = new SectionAdapter(getActivity());
        mListView = (ListView) mView.findViewById(R.id.section_item_list_view);
        mListView.setAdapter(mSectionAdapter);

        mListView.setOnItemClickListener(new ButtonSectionListener());
        return mView;
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
    public void onPause() {
        // TODO 自動生成されたメソッド・スタブ
        super.onPause();
    }

    @Override
    public void onResume() {
        // TODO 自動生成されたメソッド・スタブ
        super.onResume();
    }

    private void startSelectTest(int section) {
        Activity activity = getActivity();
        if (activity instanceof OnClickStartListener) {
            OnClickStartListener listener = (OnClickStartListener) activity;
            listener.startTest(this, section);
        }
    }



    private class ButtonSectionListener implements OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // TODO 自動生成されたメソッド・スタブ
            ListView listView = (ListView) parent;
            // クリックされたアイテムを取得します

            //Toast.makeText(getActivity(), String.valueOf(position+1), Toast.LENGTH_LONG).show();
            startSelectTest(position+1);
        }


    }


}
