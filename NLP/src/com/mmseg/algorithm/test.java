package com.mmseg.algorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class test {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
//   test t=new test();
//  // System.out.println(t.isChinese('¡£'));
//   System.out.println(Character.isWhitespace('e'));
//  // System.out.println(Character.isDigit('I'));
		
		File chars=new File("dict.txt");
		BufferedReader r;
		r=new BufferedReader(new InputStreamReader (new FileInputStream(chars),"UTF-8"));
		File f=new File("words-my.dic");
		PrintStream p=new PrintStream(new FileOutputStream(f));
		String line;
		while((line=r.readLine())!=null){
			String []list=line.split(" ");
			System.out.println(list[0]);
			p.print(list[0]+"\n");
		}
		p.close();
		r.close();
	}

public boolean isChinese(char c){
	if((c >= 0x4e00)&&(c <= 0x9fbb))
	   return true;  
	else
		return false;
}
}
