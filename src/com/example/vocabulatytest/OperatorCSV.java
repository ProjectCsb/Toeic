package com.example.vocabulatytest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import android.content.Context;
import android.content.res.AssetManager;



public class OperatorCSV {
	
	private File f;
	Context mContext;
	private final String FILENAME = "data.csv";
	OperatorCSV(Context context){
		f = new File(FILENAME);
		mContext = context;
	}
	
	public void writeCSV(String str){
		try {
			FileOutputStream output = mContext.openFileOutput(FILENAME,Context.MODE_PRIVATE );
			BufferedWriter buf = new BufferedWriter(new OutputStreamWriter(output));
			
		
			buf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> readList(){
		
		ArrayList<String> list = new ArrayList<String>();
		
	
		try {
			AssetManager as = mContext.getAssets();
			InputStream ins =as.open(FILENAME);
			//FileInputStream input = new FileInputStream(ins);
			BufferedReader br = new BufferedReader(new InputStreamReader(ins));
			try {
				String str = br.readLine();
				while(str != null){
					list.add(str);
					str = br.readLine();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO �����������ꂽ catch �u���b�N
			e1.printStackTrace();
		}
		
		return list;
	}
	
}
