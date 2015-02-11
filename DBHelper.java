package com.example.kinpira.sqtest;

/**
 * Created by kinpira on 2015/02/04.
 */

        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteDatabase.CursorFactory;
        import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "toeic";
    private static final String TABLE_NAME_WORD = "toeicWord";
    private static final String TABLE_NAME_SECTION = "toeicSection";

    /**  単語テーブルの作成用SQL */
    private static final String CREATE_WORDTABLE_SQL = "" +
            "create table "+TABLE_NAME_WORD+" (" +
            "WORD text primary key," +
            "MEAN text," +
            "EXAMPLE text," +
            "POS text," +
            "SECTION int," +
            "NUMBEROFTIMES int," +
            "NUMBEROFCORRECT int," +
            "ERROR real" +
            ")";

    /**  セクションテーブルの作成用SQL */
    private static final String CREATE_SECTIONTABLE_SQL = "" +
            "create table "+TABLE_NAME_SECTION+" (" +
            "SECTION int primary key," +
            "CORRECT int," +
            "MAXCORRECT int" +
            ")";

    /** テーブルの削除用SQL */
    private static final String DROP_WORDTABLE_SQL = "drop table if exists " + TABLE_NAME_WORD;
    private static final String DROP_SECTIONTABLE_SQL = "drop table if exists " + TABLE_NAME_SECTION;

    /**
     * コンストラクタ（必須）
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public DBHelper(
            Context context,
            CursorFactory factory,
            int version) {

        super(context, DB_NAME, factory, version);
    }

    /**
     * テーブルの生成（必須）
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_WORDTABLE_SQL);
        db.execSQL(CREATE_SECTIONTABLE_SQL);
    }

    /**
     * テーブルの再作成（必須）
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_WORDTABLE_SQL);
        db.execSQL(CREATE_WORDTABLE_SQL);
        db.execSQL(DROP_SECTIONTABLE_SQL);
        db.execSQL(CREATE_SECTIONTABLE_SQL);
    }

}