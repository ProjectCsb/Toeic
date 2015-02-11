package com.example.kinpira.sqtest;
import java.util.List;
/**
 * Created by kinpira on 2015/02/11.
 */
public class Questions {

    private List<DBWordEntity> q;
    private int c;

    Questions(){

    }

    public void setQuestion(List<DBWordEntity> list){
        q = list;
    }

    public void setCorrect(int num){
        c = num;
    }

    public List<DBWordEntity> getQuestion(){
        return q;
    }

    public int getCorrect(){
        return c;
    }
}
