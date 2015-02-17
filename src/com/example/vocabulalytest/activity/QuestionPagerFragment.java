package com.example.vocabulalytest.activity;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vocabulalytest.database.DBDAO;
import com.example.vocabulalytest.database.DBHelper;
import com.example.vocabulalytest.database.DBSectionEntity;
import com.example.vocabulalytest.database.DBWordEntity;
import com.example.vocabulatytest.R;

import java.util.ArrayList;

public class QuestionPagerFragment extends Fragment implements
        QuestionFragmentInterface {

    final private int QUESTION_SIZE = 10;
    View mView;
    ViewPager mViewPager;
    QuestionFragmentAdapter mPagerAdapter;
    ArrayList<QuestionFragment> mQuestionList;
    int mQuestionNum;
    int mRightAnswer;
    int mMode;
    Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO 自動生成されたメソッド・スタブ
        super.onCreate(savedInstanceState);

        mContext = getActivity();

        if (getArguments() != null && getArguments().containsKey("mode")) {
            mMode = getArguments().getInt("mode");
        } else {
            mMode = 0;
        }

        // 問題の取得
        mQuestionNum = 1;
        mRightAnswer = 0;
        mQuestionList = new ArrayList<QuestionFragment>();
        for (int i = 0; i < QUESTION_SIZE; i++) {
            QuestionFragment fragment = new QuestionFragment();
            Bundle bundle = new Bundle();
            bundle.putString("num", String.valueOf(i + 1));
            bundle.putInt("mode", mMode);
            fragment.setArguments(bundle);
            mQuestionList.add(fragment);
        }

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO 自動生成されたメソッド・スタブ
        mView = inflater.inflate(R.layout.layout_question_fragment, container,
                false);
        mViewPager = (ViewPager) mView.findViewById(R.id.pager);
        mPagerAdapter = new QuestionFragmentAdapter(getFragmentManager());
        mViewPager.setOnPageChangeListener(new OnChangeListener());
        mPagerAdapter.addFragment(mQuestionList.get(0));

        mViewPager.setAdapter(mPagerAdapter);


        mViewPager.setPageTransformer(true, new DepthPageTransformer());
        setRetainInstance(true);
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

    @Override
    public void nextQuestion(boolean right) {
        // TODO 自動生成されたメソッド・スタブ
        // QuestionFragmentから呼ばれる
        // 操作
        /*
         * mPagerAdapter.addFragment(new QuestionFragment());
		 * mPagerAdapter.removeFragment(); mPagerAdapter.notifyDataSetChanged();
		 */
        if (right) {
            mRightAnswer++;
        }
        if (mQuestionNum < QUESTION_SIZE) {
            mPagerAdapter.addFragment(mQuestionList.get(mQuestionNum));
            mPagerAdapter.notifyDataSetChanged();

            mQuestionNum++;
        } else {
            if (mMode > 0) {
                sendSectionData(mRightAnswer);
            }
            ResultFragment resFragment = new ResultFragment();
            Bundle bundle = new Bundle();
            bundle.putString("son", String.valueOf(mRightAnswer));
            bundle.putString("mother", String.valueOf(QUESTION_SIZE));
            resFragment.setArguments(bundle);
            mPagerAdapter.addFragment(resFragment);
            mPagerAdapter.notifyDataSetChanged();

        }

    }


    public class OnChangeListener implements ViewPager.OnPageChangeListener {

        @Override
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
            //mPagerAdapter.removeFragment();
            //mPagerAdapter.notifyDataSetChanged();
            ArrayList<Fragment> list = new ArrayList<Fragment>();
            list.addAll(mPagerAdapter.getList());
            list.remove(0);

            mPagerAdapter = new QuestionFragmentAdapter(getFragmentManager(), list);

            mViewPager.setAdapter(mPagerAdapter);


        }

    }

    private void sendSectionData(int cor) {
        DBHelper helper = new DBHelper(getActivity(), null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();
        DBDAO dao = new DBDAO(db);
        DBSectionEntity dbentity = dao.findSectionNum(mMode).get(0);
        if (cor > dbentity.getMaxCorrect()) {
            dao.updateSectionMaxCorrect(mMode, cor);

        }
        dao.updateSectionCorrect(mMode, cor);
        helper.close();

    }

    public class DepthPageTransformer implements ViewPager.PageTransformer {

        private static final float MIN_ALPHA_POSITION = 0.70f;
        private static final float MIN_ALPHA = 1.0f;

        @Override
        public void transformPage(View view, float position) {
            // TODO 自動生成されたメソッド・スタブ

            int pageWidth = view.getWidth();

			/*if (position == 0f) {
                view.setTag(0);
			} else if (position == 1f) {
				view.setTag(1);
			}*/

            if (position < -1) {
                view.setAlpha(0);
            } else if (position <= 0) {
                view.setAlpha(1);
                view.setTranslationX(0);
            } else if (position <= 1) {
                //Object tag = (Integer) view.getTag();


                view.setTranslationX(pageWidth * -position);
				/*if (tag == null || ((Integer) tag) != 0) {

					view.setTranslationX(pageWidth * -position);
				} else {

					view.setTranslationX(0);
				}*/

				/*float alpha = MIN_ALPHA;
				if (position < MIN_ALPHA_POSITION) {
					alpha = 1 - (position / MIN_ALPHA_POSITION)
							* (1 - MIN_ALPHA);
				}
				view.setAlpha(alpha);*/

            } else {

                view.setAlpha(0);
            }

        }

    }

}
