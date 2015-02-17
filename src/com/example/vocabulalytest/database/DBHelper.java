package com.example.vocabulalytest.database;

/**
 * Created by kinpira on 2015/02/04.
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private static final String DB_NAME = "toeic";
	private static final String TABLE_NAME_WORD = "toeicWord";
	private static final String TABLE_NAME_SECTION = "toeicSection";
	private boolean needCopy = false;
	private Context mContext;
	private File databathPath;

	/** 単語テーブルの作成用SQL */
	private static final String CREATE_WORDTABLE_SQL = "" + "create table "
			+ TABLE_NAME_WORD + " (" + "WORD text primary key," + "MEAN text,"
			+ "EXAMPLE text," + "POS text," + "SECTION int,"
			+ "NUMBEROFTIMES int," + "NUMBEROFCORRECT int," + "ERROR real"
			+ ")";

	/** セクションテーブルの作成用SQL */
	private static final String CREATE_SECTIONTABLE_SQL = "" + "create table "
			+ TABLE_NAME_SECTION + " (" + "SECTION int primary key,"
			+ "CORRECT int," + "MAXCORRECT int" + ")";

	/** テーブルの削除用SQL */
	private static final String DROP_WORDTABLE_SQL = "drop table if exists "
			+ TABLE_NAME_WORD;
	private static final String DROP_SECTIONTABLE_SQL = "drop table if exists "
			+ TABLE_NAME_SECTION;

	/**
	 * コンストラクタ（必須）
	 * 
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 */
	public DBHelper(Context context, CursorFactory factory, int version) {

		super(context, DB_NAME, factory, version);
		mContext =context;
		databathPath = mContext.getDatabasePath(DB_NAME);
	}

	/**
	 * テーブルの生成（必須）
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		/*db.execSQL(CREATE_WORDTABLE_SQL);
		db.execSQL(CREATE_SECTIONTABLE_SQL);*/

		needCopy = true;
	}

	/**
	 * テーブルの再作成（必須）
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		/*db.execSQL(DROP_WORDTABLE_SQL);
		db.execSQL(CREATE_WORDTABLE_SQL);
		db.execSQL(DROP_SECTIONTABLE_SQL);
		db.execSQL(CREATE_SECTIONTABLE_SQL);*/
	}

	@Override
	public synchronized SQLiteDatabase getReadableDatabase() {
		SQLiteDatabase database = super.getReadableDatabase();
		if (needCopy) {
			try {
				database = copyDatabase(database);
			} catch (IOException e) {
			}
		}
		return database;
	}

	@Override
	public synchronized SQLiteDatabase getWritableDatabase() {
		SQLiteDatabase database = super.getWritableDatabase();
		if (needCopy) {
			try {
				database = copyDatabase(database);
			} catch (IOException e) {
			}
		}
		return database;
	}

	private SQLiteDatabase copyDatabase(SQLiteDatabase database)
			throws IOException {
		// dbがひらきっぱなしなので、書き換えできるように閉じる
		database.close();

		// コピー！
		InputStream input = mContext.getAssets().open("toeic_data");
		OutputStream output = new FileOutputStream(this.databathPath);
		copy(input, output);

		needCopy = false;
		// dbを閉じたので、また開く
		return super.getWritableDatabase();
	}

	// CopyUtilsからのコピペ
	private static int copy(InputStream input, OutputStream output)
			throws IOException {
		byte[] buffer = new byte[1024 * 4];
		int count = 0;
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
			count += n;
		}
		return count;
	}

}