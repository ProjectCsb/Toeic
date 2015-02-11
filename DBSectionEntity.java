package com.example.kinpira.sqtest;

/**
 * Created by kinpira on 2015/02/05.
 *
 *          toeicSectionTable
 */
public class DBSectionEntity {

    /**
     * データベースの各要素
     */

    private int sec;
    private int cor;
    private int mcor;

    DBSectionEntity(){
        sec = 0;cor = 0;mcor = 0;
    }

    // 書き込みメソッド群
    public void setSection(int num){sec = num;}
    public void setCorrect(int num){cor = num;}
    public void setMaxCorrect(int num){mcor = num;}

    // 読み込みメソッド群
    public int getSection(){return sec;}
    public int getCorrect(){return cor;}
    public double getMaxCorrect(){return mcor;}
}
