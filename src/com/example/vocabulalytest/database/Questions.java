package com.example.vocabulalytest.database;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by kinpira on 2015/02/11.
 */
public class Questions {

    private ArrayList<DBWordEntity> q;
    private int c;

    Questions(){

    }

    public void setQuestion(ArrayList<DBWordEntity> list){
        q = list;
    }

    public void setCorrect(int num){
        c = num;
    }

    public ArrayList<DBWordEntity> getQuestion(){
        return q;
    }

    public int getCorrect(){
        return c;
    }
}
