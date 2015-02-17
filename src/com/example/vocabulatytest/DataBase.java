package com.example.vocabulatytest;



import java.util.ArrayList;
import java.util.Random;


import android.content.Context;
import android.util.Log;

public class DataBase {
	
	private OperatorCSV op;
	private Random seed;
	
	private Question ques;
	private ArrayList<String> word,mean,exam;
	
	public DataBase(Context context){
		
		op = new OperatorCSV(context);
		//op.writeCSV("aaa");
		seed = new Random();
		
		word = new ArrayList<String>();
		mean = new ArrayList<String>();
		exam = new ArrayList<String>();
		initDataBase();
		
	}
	
	public ArrayList<String> getList(){
		return op.readList();
	}
	
	public ArrayList<String> getWord(){
		return word;
	}
	
	public ArrayList<String> getMeaning(){
		return mean;
	}
	
	public ArrayList<String> getExample(){
		return exam;
	}
	
	public Question getQuestion(){
		initQuestion();
		return ques;
	}
	
	private void initDataBase(){
		for(String str: op.readList()){
			String sp[] = str.split(",");
			word.add(sp[0]);
			mean.add(sp[1]);
			exam.add(sp[2]);
		}
		
	}
	
	private void initQuestion(){
		
		ArrayList<String> b = (ArrayList<String>) getList().clone(); 
		ArrayList<String> qb = new ArrayList<String>();

		for(int i = 0; i < 4; i++){
			int rnd = seed.nextInt(b.size());
			qb.add(b.get(rnd));
			b.remove(rnd);
		}
	
		int c = seed.nextInt(4);
		Log.d("rand",String.valueOf(c));
		String ex = qb.get(c).split(",")[2];
		ArrayList<String> wb = new ArrayList<String>();
		ArrayList<String> mb = new ArrayList<String>();
		for(String str : qb){
			wb.add(str.split(",")[0]);
			mb.add(str.split(",")[1]);
		}
		
		ques = new Question(wb,mb,c,ex);
		
	}
	
}
