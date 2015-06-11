package com.mmseg.algorithm;

import java.util.ArrayList;

public class Chunk {
ArrayList<String>words;
int offset;
//���캯��
public Chunk(ArrayList<String>words,int offset){
	this.words=new ArrayList<String>();
	for(int i=0;i<words.size();i++)
		this.words.add(words.get(i));
	this.offset=offset;
}
//��ӡ��Chunk�ṹ
public void print(){
	for(int i=0;i<words.size();i++)
		System.out.print(words.get(i)+"|");
	System.out.println(" "+offset);
}


//�����ܳ���
public int getTotalLength(){
	int length=0;
	for(String s:words)
		length+=s.length();
	return length;
}
//����ƽ����
public double getAverage(){
	return (float)getTotalLength()/words.size();
}
//�����׼��
public double getStandardAver(){
	double aver=getAverage();
	double sum=0;
	for (String w:words)
		sum += Math.pow(w.length()-aver, 2);
	return sum/words.size();
}
//�������ɶ�
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
//����ƫ��
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
