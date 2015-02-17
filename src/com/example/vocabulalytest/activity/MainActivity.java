package com.example.vocabulalytest.activity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.vocabulatytest.R;

public class MainActivity extends FragmentActivity implements OnClickStartListener {

    private FragmentManager mFragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*RelativeLayout startView = (RelativeLayout) findViewById(R.layout.activity_main);
        setContentView(startView);*/

        setContentView(R.layout.activity_main);
        //アクションバーを消す
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		/*Button start = (Button) findViewById(R.id.start_button);
		start.setOnClickListener(new StartButtonClickListener());
		*/
        mFragmentManager = getSupportFragmentManager();
        startMenuFragment();
        //startSectionFragment();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        // TODO 自動生成されたメソッド・スタブ
        super.onWindowFocusChanged(hasFocus);

        //TODO 座標を送って,QuestionLayoutFactoryクラスの初期化を行う
    }


    class StartButtonClickListener implements OnClickListener {

        @Override
        public void onClick(View arg0) {
            // TODO 自動生成されたメソッド・スタブ
            Toast.makeText(getApplication(), "click", Toast.LENGTH_SHORT).show();


        }

    }


    private void startVocabulalyTest(int mode) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        //QuestionFragment questionFragment= new QuestionFragment();
        QuestionPagerFragment questionPagerFragment = new QuestionPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("mode", mode);
        questionPagerFragment.setArguments(bundle);


        if (mode > 0) {
            fragmentTransaction.addToBackStack("section");
        } else {
            fragmentTransaction.addToBackStack("menu");
        }
        fragmentTransaction.replace(R.id.fragment_container, questionPagerFragment, "pager");
        fragmentTransaction.commit();
    }

    private void startMenuFragment() {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        MenuFragment menuFragment = new MenuFragment();
        fragmentTransaction.add(R.id.fragment_container, menuFragment, "menu");
        fragmentTransaction.commit();
    }

    private void startResultFragment() {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        ResultFragment resultFragment = new ResultFragment();
        fragmentTransaction.replace(R.id.fragment_container, resultFragment, "result");
        fragmentTransaction.commit();
    }

    private void startSectionFragment() {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        SectionFragment sectionFragment = new SectionFragment();

        fragmentTransaction.addToBackStack("menu");
        fragmentTransaction.replace(R.id.fragment_container, sectionFragment, "section");
        fragmentTransaction.commit();
    }

    private void startWeekFragment() {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        QuestionPagerFragment questionPagerFragment = new QuestionPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("mode", -1);
        questionPagerFragment.setArguments(bundle);
        fragmentTransaction.addToBackStack("menu");
        fragmentTransaction.replace(R.id.fragment_container, questionPagerFragment, "pager");

        fragmentTransaction.commit();
    }


    @Override
    public void startTest(Fragment fragment, int mode) {
        // TODO 自動生成されたメソッド・スタブ
        startVocabulalyTest(mode);
    }

    @Override
    public void startMenu(Fragment fragment) {
        // TODO 自動生成されたメソッド・スタブ
        mFragmentManager.popBackStack();
/*
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        //fragmentTransaction.remove(fragment);
        if (mFragmentManager.findFragmentByTag("pager") != null) {
            //メニューに戻るときは全力で戻る
            fragmentTransaction.remove(mFragmentManager.findFragmentByTag("pager"));
        }
        if (mFragmentManager.findFragmentByTag("section") != null) {

            fragmentTransaction.remove(mFragmentManager.findFragmentByTag("section"));
        }
        if (mFragmentManager.findFragmentByTag("menu") != null) {

            fragmentTransaction.remove(mFragmentManager.findFragmentByTag("menu"));
        }
        fragmentTransaction.commit();

        startMenuFragment();
*/
    }

    @Override
    public void startResult(Fragment fragment) {
        // TODO 自動生成されたメソッド・スタブ
/*		FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
		fragmentTransaction.remove(fragment);
		fragmentTransaction.commit();*/

        startResultFragment();
    }

    @Override
    public void startSection(Fragment fragment) {
        // TODO 素結合なプログラミングをしましょうね
		/*FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
		fragmentTransaction.remove(fragment);
		fragmentTransaction.commit();
*/
        startSectionFragment();

    }

    @Override
    public void startWeek(Fragment fragment) {
        // TODO 自動生成されたメソッド・スタブ
		/*FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
		fragmentTransaction.remove(fragment);
		fragmentTransaction.commit();
		*/
        startWeekFragment();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mFragmentManager.getBackStackEntryCount() != 0) {
                new AlertDialog.Builder(this)
                        .setTitle("戻りますか？")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mFragmentManager.popBackStack();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialogInterface) {
                                mFragmentManager.popBackStack();
                            }
                        })
                        .create()
                        .show();
            } else {
                new AlertDialog.Builder(this)
                        .setTitle("終了しますか？")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialogInterface) {
                                finish();
                            }
                        })
                        .create()
                        .show();

            }
           /* if (mFragmentManager.findFragmentByTag("section") == null){
                mFragmentManager.popBackStack("home",0);
        }else{
                mFragmentManager.popBackStack("secter",0);
            }
           */
            return true;
        }
        return false;


    }
}
