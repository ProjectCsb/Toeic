package com.example.vocabulatytest;

import java.util.ArrayList;


public class Question {
	
	private ArrayList<String> word;
	private ArrayList<String> mean;
	private int correctNum;
	private String exam;	
	
	Question(ArrayList<String> w,ArrayList<String> m,int c,String s){
		word = w;
		mean = m;
		correctNum = c;
		exam = s;
	}
	
	public ArrayList<String> getWord(){
		return word;
	}
	
	public ArrayList<String> getMeaning(){
		return mean;
	}
	
	public int getCorrectNum(){
		return correctNum;
	}
	
	public String getExample(){
		return exam;
	}
}
