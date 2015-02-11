package com.example.kinpira.sqtest;

/**
 * Created by kinpira on 2015/02/05.
 *
 *          toeicWordTable
 */
public class DBWordEntity {

    /**
     * データベースの各要素
     */

    private String word;
    private String mean;
    private String pos;
    private String exam;
    private int sec;
    private int nt;
    private int nc;
    private double ero;

    DBWordEntity(){
        word = null;mean = null;pos = null;exam = null;sec = 0;nt = 0;nc = 0;ero = 0.0;
    }

    // 書き込みメソッド群
    public void setWord(String str){word = str;}
    public void setMean(String str){mean = str;}
    public void setPOS(String str){pos = str;}
    public void setExam(String str){exam = str;}
    public void setSection(int num){sec = num;}
    public void setNumberOfTimes(int num){nt = num;}
    public void setNumberOfCorrect(int num){nc = num;}
    public void setError(double num){ero = num;}

    // 読み込みメソッド群
    public String getWord(){return word;}
    public String getMean(){return mean;}
    public String getPOS(){return pos;}
    public String getExam(){return exam;}
    public int getSection(){return sec;}
    public int getNumberOfTimes(){return nt;}
    public int getNumberOfCorrect(){return nc;}
    public double getError(){return ero;}
}
