package com.example.kinpira.sqtest;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by kinpira on 2015/02/11.
 */
public class Select{

    private DBDAO dao;
    private Random seed;

    Select(DBDAO db){
        dao = db;
        seed = new Random();
    }

    //４つ問題を取ってくる、正解はランダムで決まる

    private Questions select4Question(List<DBWordEntity> list){

        Questions q = new Questions();
        List<DBWordEntity> bf = new ArrayList<DBWordEntity>();

        for(int i = 0;i < 4;i++){
            int rnd = seed.nextInt(list.size());
            bf.add(list.get(rnd));
            list.remove(rnd);
        }

        q.setQuestion(bf);
        q.setCorrect(seed.nextInt(4));

        return q;
    }

    /**
     * データベースすべてから問題を取得する
     * @return Questions
     */
    
    public Questions AllQuestions(){
        List<DBWordEntity> list = dao.findWord();
        return select4Question(list);
    }

    /**
     * 選択したセクションから問題を取得する
     * @param sec 選択するセクション
     * @return Questions
     */

    public Questions SectionQuestions(int sec){
        List<DBWordEntity> list = dao.findSectionWord(sec);
        return select4Question(list);
    }
    /**
     * データベースすべてから問題を取得する
     * @param ero 任意の実数値以下の問題を取得する
     * @return Questions
     */

    public Questions ErrorQuestions(double ero){
        List<DBWordEntity> list = dao.findError(ero,false);
        return select4Question(list);
    }

}
