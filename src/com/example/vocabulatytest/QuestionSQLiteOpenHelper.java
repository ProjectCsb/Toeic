package com.example.vocabulatytest;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class QuestionSQLiteOpenHelper extends SQLiteOpenHelper {

	/*
	 * メンバ
	 */
	final static private String DB_NAME = "QuestionTable.db";
	final static private String TABLE_QUESTION = "question_table";
	final static private String TABLE_SEARCH = "question_search";
	final static private String TABLE_SECTION = "question_section";
	final static private int DB_VERSION = 1;

	final static private String CREATE_TABLE1 = "CREATE TABLE IF NOT EXIST "
			+ TABLE_QUESTION + "ここはやーてぃが書いてくれてる";
	final static private String CREATE_TABLE2 = "CREATE TABLE IF NOT EXIST "
			+ TABLE_SEARCH+ "";
	final static private String CREATE_TABLE3="CREATE TABLE IF NOT EXIST "
			+ TABLE_SECTION+"";

	/*
	 * コンストラクタ
	 */

	public QuestionSQLiteOpenHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	public QuestionSQLiteOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public QuestionSQLiteOpenHelper(Context context, String name,
			CursorFactory factory, int version,
			DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO 自動生成されたメソッド・スタブ
		// 初回の呼び出し
		db.execSQL(CREATE_TABLE1);
		db.execSQL(CREATE_TABLE2);
		db.execSQL(CREATE_TABLE3);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 自動生成されたメソッド・スタブ
		// データベースのバージョンが上がったら呼ばれる
	}

}
