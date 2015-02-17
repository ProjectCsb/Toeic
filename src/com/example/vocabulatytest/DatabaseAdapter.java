package com.example.vocabulatytest;


import android.content.Context;

public class DatabaseAdapter {
	/*
	 *  DOAデザインパターンにのっとって作成
	 *  データベースアクセスをカプセル化するための中間クラス
	 */
	
	/*
	 * 	メンバー
	 */
	QuestionSQLiteOpenHelper mHelper;
	
	/*
	 * コンストラクタ
	 */
	public DatabaseAdapter(Context context){
			mHelper = new QuestionSQLiteOpenHelper(context);
			
	}
	
	
	
	
	

}
