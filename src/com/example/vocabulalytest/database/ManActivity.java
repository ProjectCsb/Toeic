package com.example.vocabulalytest.database;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vocabulatytest.R;

public class ManActivity extends Activity {

    private DBDAO dao;
    private Select s;
    private LinearLayout showData;
    private LinearLayout showCorrect;
    private EditText dataEdit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man);

        // SQLiteの準備
        DBHelper helper = new DBHelper(this, null, 1);
        SQLiteDatabase db = helper.getReadableDatabase();
        dao = new DBDAO(db);
        s = new Select(dao);
        // データ入力欄の初期設定
        dataEdit = (EditText)findViewById(R.id.dataEdit);
        dataEdit.setOnKeyListener(new AddressBarOnKeyListener());

        // データ出力Viewグループの取得
        showData = (LinearLayout)findViewById(R.id.showData);
        showCorrect = (LinearLayout)findViewById(R.id.showCorrect);

        dao.updateNumberOfTimes("huugu",1);

        // 表示データの更新
        changeData();
    }


    /**
     * 追加ボタンのクリックイベント
     * @param view
     */
    public void addData(View view) {

        // データの追加
        dao.insertDB(dataEdit.getText().toString());

        // 入力欄のクリア
        dataEdit.setText(null);

        // 表示データの更新
        changeData();
    }

    public void deleteData(View view){
        dao.deleteAll();
        changeData();
    }


    /**
     * 表示データの更新
     */
    private void changeData() {

        // 表示中のデータを一旦すべてクリアする。
        showData.removeAllViews();
        showCorrect.removeAllViews();

        // DBからすべてのデータを取得する。
        List<DBWordEntity> entityList = dao.findWord();

        // データを表示領域に追加する
        for(DBWordEntity entity: entityList) {
            TextView textView = new TextView(this);
            textView.setText(entity.getWord()
                    + "： " + entity.getMean()
                    + "： " + entity.getPOS()
                    + "： " + entity.getExam()
                    + "： " + entity.getSection()
                    + "： " + entity.getNumberOfCorrect()
                    + "： " + entity.getNumberOfTimes()
                    + "： " + entity.getError());
            showData.addView(textView);
        }

       // List<DBWordEntity> entity2List = dao.findError(0.5,false);

        /*for(DBWordEntity entity: entity2List) {

        }*/

//        Questions q = new Questions();
//        q = s.AllQuestions();
//        for(DBWordEntity entity:q.getQuestion()){
//            TextView text2View = new TextView(this);
//            text2View.setText(entity.getWord()
//                    + "： " + entity.getMean()
//                    + "： " + entity.getPOS()
//                    + "： " + entity.getExam()
//                    + "： " + entity.getSection()
//                    + "： " + entity.getNumberOfCorrect()
//                    + "： " + entity.getNumberOfTimes()
//                    + "： " + entity.getError()
//                    + "： " + q.getCorrect());
//            showCorrect.addView(text2View);
//        }
    }


    // ソフトキーボードの「確定」が押された時にソフトキーボードを消す
    private class AddressBarOnKeyListener implements OnKeyListener {

        public boolean onKey(View view, int keyCode, KeyEvent event) {

            //EnterKeyが押されたかを判定
            if (event.getAction() == KeyEvent.ACTION_DOWN
                    && keyCode == KeyEvent.KEYCODE_ENTER) {

                //ソフトキーボードを閉じる
                InputMethodManager inputMethodManager =
                        (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);

                return true;
            }

            return false;
        }
    }
}
