package com.mmseg.algorithm;

import java.util.ArrayList;
import java.util.List;

public class MMsegComplex {


private  Rule rule=new Rule();

//�����ַ��������طִʽ��
public List<String> analysis(String text,Dictionary dict){
	ArrayList<String> result=new ArrayList<String>();//�������ִʵĽ��
	char []sen=text.toCharArray();
	for(int i=0;i<sen.length;i++){
		if(isDigit(sen[i])){//��������ֿ�ͷ
			StringBuilder sb=new StringBuilder();
			while(i<sen.length&&(isDigit(sen[i])||sen[i]=='.')){
				sb.append(sen[i]);
				i++;
			}
			i-=1;
			result.add(sb.toString());
			continue;
		}else if(isLetter(sen[i])){//�������ĸ��ͷ
			StringBuilder sb=new StringBuilder();
			while(i<sen.length&&isLetter(sen[i])){
				sb.append(sen[i]);
				i++;
			}
			i-=1;
			result.add(sb.toString());
			continue;
			
		}else if(Character.isWhitespace(sen[i]))//����ǿո�
			continue;
		ArrayList<String>words=new ArrayList<String>();
		List<Chunk> clist=new ArrayList<Chunk>();
		CharNode firstNode=dict.getDict().get(sen[i]);
		if(firstNode==null){
			result.add(sen[i]+"");
		    continue;	
		}
		ArrayList<Integer> first=dict.maxMatch(firstNode, sen, i);//�������е�һ���ֵ�����ƥ��
		for(int j=0;j<first.size();j++){
			int secondBegin=first.get(j)+i+1;
			words.add(getString(sen,i,secondBegin));
			if(secondBegin<sen.length){
			CharNode secondNode=dict.getDict().get(sen[secondBegin]);
			ArrayList<Integer>second=dict.maxMatch(secondNode, sen, secondBegin);//���صڶ����ַ�����ƥ��
			for(int k=0;k<second.size();k++){
				int thirdBegin=secondBegin+second.get(k)+1;
				words.add(getString(sen,secondBegin,thirdBegin));
				if(thirdBegin<sen.length){
				int thirdEnd=dict.maxMatch(sen, thirdBegin)+thirdBegin;//�������ֵ����ƥ��
				words.add(getString(sen,thirdBegin,thirdEnd+1));
				clist.add(new Chunk(words,first.get(j)));
				words.remove(words.lastIndexOf(getString(sen,thirdBegin,thirdEnd+1)));//��ջ
				}else
					clist.add(new Chunk(words,first.get(j)));
				words.remove(words.lastIndexOf(getString(sen,secondBegin,thirdBegin)));	
		}
		    
		}else
			clist.add(new Chunk(words,first.get(j)));
		words.remove(words.lastIndexOf(getString(sen,i,secondBegin)));  
	}
	     Chunk afterRule=rule.getResult(clist, dict);
	     result.add(afterRule.getWords().get(0));
	     i+=afterRule.getOffset();
  }
	return result;

}

//���ַ�����ת��Ϊ�ַ���
public String getString(char[]arr,int begin,int end){
	StringBuilder result=new StringBuilder();
	for(int i=begin;i<end;i++)
		result.append(arr[i]);
	return result.toString();
}
//�ж��Ƿ�����
public boolean isChinese(char c){
	if((c >= 0x4e00)&&(c <= 0x9fbb))
	   return true;  
	else
		return false;
}
//�ж��Ƿ�Ϊ��ĸa-z��A-Z
public boolean isLetter(char c){
	return ('a'<=c&&c<='z')||('A'<=c&&c<='Z');
}
//�ж��Ƿ�Ϊ����
public boolean isDigit(char c){
	return '0'<=c&&c<='9';
}

public static void main(String []args){
//	MMsegComplex mmseg=new MMsegComplex();
//	Dictionary dict=new Dictionary();
//	//dict.loadDictionary();
//	List<String> result=mmseg.analysis("��������Ҫ����ҳ�м��ϳ��õļ�����ǩ�ı������������������ʼ��������Լ��ύ��ť��Ȼ�����������Щ�ֶ��ı����Ӻ��޸���ʽ�Ϳ���",dict);
//
//	for(int i=0;i<result.size();i++)
//		System.out.print(result.get(i)+"|");
//	System.out.println();	
}

}
