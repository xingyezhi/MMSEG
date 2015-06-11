package com.mmseg.algorithm;

import java.util.ArrayList;

public class Chunk {
ArrayList<String>words;
int offset;
//构造函数
public Chunk(ArrayList<String>words,int offset){
	this.words=new ArrayList<String>();
	for(int i=0;i<words.size();i++)
		this.words.add(words.get(i));
	this.offset=offset;
}
//打印出Chunk结构
public void print(){
	for(int i=0;i<words.size();i++)
		System.out.print(words.get(i)+"|");
	System.out.println(" "+offset);
}


//计算总长度
public int getTotalLength(){
	int length=0;
	for(String s:words)
		length+=s.length();
	return length;
}
//计算平均数
public double getAverage(){
	return (float)getTotalLength()/words.size();
}
//计算标准差
public double getStandardAver(){
	double aver=getAverage();
	double sum=0;
	for (String w:words)
		sum += Math.pow(w.length()-aver, 2);
	return sum/words.size();
}
//计算自由度
public int getDegree(Dictionary dic){
	int sum=0;
	for(String w:words){
		if(w.length()==1){
			if(dic.getDict().get(w.charAt(0))!=null)
			sum+=dic.getDict().get(w.charAt(0)).getFreq();
			}
	}
	return sum;
}
//返回偏移
public int getOffset(){
	return offset;
}

public ArrayList<String> getWords() {
	return words;
}
public void setWords(ArrayList<String> words) {
	this.words = words;
}
public static void main(String []args){
	
}
}
