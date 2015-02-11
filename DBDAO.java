package com.example.kinpira.sqtest;

/**
 * Created by kinpira on 2015/02/04.
 */
import java.util.ArrayList;
import java.util.List;
//Debug
import java.util.Random;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBDAO {

    //  テーブルの定数
    private static final String TABLE_NAME_WORD = "toeicWord";
    private static final String TABLE_NAME_SECTION = "toeicSection";

    //  テーブルの列定数
    private static final String COLUMN_WORD = "WORD";
    private static final String COLUMN_MEAN = "MEAN";
    private static final String COLUMN_EXAMPLE = "EXAMPLE";
    private static final String COLUMN_POS= "POS";
    private static final String COLUMN_NUMBEROFTIMES = "NUMBEROFTIMES";
    private static final String COLUMN_NUMBEROFCORRECT = "NUMBEROFCORRECT";
    //  セクションテーブルの列定数
    private static final String COLUMN_SECTION = "SECTION";
    private static final String COLUMN_CORRECT = "CORRECT";
    private static final String COLUMN_MAXCORRECT = "MAXCORRECT";

    //    多分使うことはない（null）で代用できる
    //    private static final String[] WORDCOLUMNS = {COLUMN_WORD,COLUMN_MEAN,COLUMN_EXAMPLE,COLUMN_POS,COLUMN_NUMBEROFTIMES,COLUMN_NUMBEROFCORRECT};
    //    private static final String[] SECTIONCOLUMNS = {COLUMN_SECTION,COLUMN_CORRECT,COLUMN_MAXCORRECT};

    // SQLiteDatabase
    private SQLiteDatabase db;

    /**
     * コンストラクタ
     * @param db
     */
    public DBDAO(SQLiteDatabase db) {
        this.db = db;
    }

    //データベースにクエリを飛ばし帰ってきた値をtoeicWord配列に代入
    private List<DBWordEntity> inputWordEntity(Cursor c){

        List<DBWordEntity> entityList = new  ArrayList<DBWordEntity>();

        while(c.moveToNext()) {
            DBWordEntity entity = new DBWordEntity();
            entity.setWord(c.getString(0));
            entity.setMean(c.getString(1));
            entity.setExam(c.getString(2));
            entity.setPOS(c.getString(3));
            entity.setSection(c.getInt(4));
            entity.setNumberOfTimes(c.getInt(5));
            entity.setNumberOfCorrect(c.getInt(6));
            entityList.add(entity);
        }

        return entityList;
    }

    private List<DBSectionEntity> inputSectionEntity(Cursor c){

        List<DBSectionEntity> entityList = new  ArrayList<DBSectionEntity>();

        while(c.moveToNext()) {
            DBSectionEntity entity = new DBSectionEntity();
            entity.setSection(c.getInt(0));
            entity.setCorrect(c.getInt(1));
            entity.setMaxCorrect(c.getInt(2));
            entityList.add(entity);
        }

        return entityList;

    }
    //-----------------------------------ここからデータの読み込みメソッド群------------------------------

    /**
     * 単語のデータの取得   ----------------①
     * @return
     */
    public List<DBWordEntity> findWord() {

        Cursor cursor = db.query(
                TABLE_NAME_WORD,
                null,
                null,
                null,
                null,
                null,
                null);

        return inputWordEntity(cursor);
    }

    /**
     * 任意の単語データの取得   ----------------①
     * @return
     */
    public List<DBWordEntity> findAnyWord(String find) {

        String select = COLUMN_WORD+" like ?";
        String[] selectArg = {find+"%"};
        Cursor cursor = db.query(
                TABLE_NAME_WORD,
                null,
                select,
                selectArg,
                null,
                null,
                null);

        return inputWordEntity(cursor);
    }
    /**
     * セクションから単語のデータの取得   ----------------①
     * @return
     */
    public List<DBWordEntity> findSectionWord(int find) {

        String select = COLUMN_SECTION + "=?";
        String[] selectArg = {String.valueOf(find)};
        Cursor cursor = db.query(
                TABLE_NAME_WORD,
                null,
                select,
                selectArg,
                null,
                null,
                null);

        return inputWordEntity(cursor);
    }


    /**
     * 正解数がfind以上の単語からデータの取得   ----------------①
     * @param find 任意の整数値
     * @param flg true:任意の整数値以上のデータを読み込む false:逆
     * @return
     */
    public List<DBWordEntity> findCorrectWord(int find,boolean flg) {

        String select = COLUMN_NUMBEROFCORRECT;
        if(flg){
            select += ">?";
        }else{
            select += "<?";
        }

        String[] selectArg = {String.valueOf(find)};
        Cursor cursor = db.query(
                TABLE_NAME_WORD,
                null,
                select,
                selectArg,
                null,
                null,
                null);

        return inputWordEntity(cursor);
    }

    /**
     * セクションテーブルのデータの取得   ----------------①
     * @return
     */
    public List<DBSectionEntity> findSection() {

        Cursor cursor = db.query(
                TABLE_NAME_SECTION,
                null,
                null,
                null,
                null,
                null,
                null);

        return inputSectionEntity(cursor);
    }

    /**
     * セクションテーブルの正解数以上のデータの取得   ----------------①
     * @param find 任意の実数値
     * @param flg true:任意の実数値以上のデータを読み込む false:逆
     * @return
     */
    public List<DBSectionEntity> findSectionCorrect(int find,boolean flg) {

        String select = COLUMN_CORRECT;
        if(flg){
            select += ">?";
        }else{
            select += "<?";
        }
        String[] selectArg = {String.valueOf(find)};

        Cursor cursor = db.query(
                TABLE_NAME_SECTION,
                null,
                select,
                selectArg,
                null,
                null,
                null);

        return inputSectionEntity(cursor);
    }

    //-----------------------------------ここまで----------------------------------------------------


    //-----------------------------------ここからデータ書き込みメソッド群--------------------------------

    /**
     * 単語テーブル上の”正解数”を更新する
     * @param str 更新する単語
     * @param num 更新する数値
     */

    public void updateNumberOfCorrect(String str,int num){

        String select = COLUMN_WORD + " = " +"'"+ str+"'";

        ContentValues values = new ContentValues();
        values.put(COLUMN_NUMBEROFCORRECT,num);

        db.update(TABLE_NAME_WORD,values,select,null);
    }

    /**
     * 単語テーブル上の”回答数”を更新する
     * @param str 更新する単語
     * @param num 更新する数値
     */

    public void updateNumberOfTimes(String str,int num){

        String select = COLUMN_WORD + " = " +"'"+ str+"'";

        ContentValues values = new ContentValues();
        values.put(COLUMN_NUMBEROFTIMES,num);

        db.update(TABLE_NAME_WORD,values,select,null);
    }

    /**
     * セクションテーブル上の”正解数”を更新する
     * @param sec 更新するセクション
     * @param num 更新する数値
     */

    public void updateSectionCorrect(int sec,int num){

        String select = COLUMN_SECTION + " = " + String.valueOf(sec);

        ContentValues values = new ContentValues();
        values.put(COLUMN_CORRECT,num);

        db.update(TABLE_NAME_SECTION,values,select,null);
    }

    /**
     * セクションテーブル上の”最大正解数”を更新する
     * @param sec 更新するセクション
     * @param num 更新する数値
     */

    public void updateSectionMaxCorrect(int sec,int num){

        String select = COLUMN_SECTION + " = " + String.valueOf(sec);

        ContentValues values = new ContentValues();
        values.put(COLUMN_MAXCORRECT,num);

        db.update(TABLE_NAME_SECTION,values,select,null);
    }


    //-----------------------------------------ここまで----------------------------------------------


    //------------------------------------ここからデバッグ用メソッド群----------------------------------


    /**
     * データの入力
     */

    public void insertDB(String word){
        ContentValues values = new ContentValues();
        values.put(COLUMN_WORD,word);

        Random r = new Random();
        values.put(COLUMN_SECTION,r.nextInt(10));
        values.put(COLUMN_NUMBEROFTIMES,r.nextInt(50));
        values.put(COLUMN_NUMBEROFCORRECT,r.nextInt(100));
        values.put(COLUMN_EXAMPLE,"exam");
        values.put(COLUMN_MEAN,"mean");
        values.put(COLUMN_POS,"pos");

        db.insert(TABLE_NAME_WORD,null,values);

    }

    /**
     * 全データの削除
     */

    public void deleteAll(){
        db.delete(TABLE_NAME_WORD,null,null);
        db.delete(TABLE_NAME_SECTION,null,null);

    }

}
