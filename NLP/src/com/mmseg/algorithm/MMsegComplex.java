package com.mmseg.algorithm;

import java.util.ArrayList;
import java.util.List;

public class MMsegComplex {


private  Rule rule=new Rule();

//分析字符串，返回分词结果
public List<String> analysis(String text,Dictionary dict){
	ArrayList<String> result=new ArrayList<String>();//保存最后分词的结果
	char []sen=text.toCharArray();
	for(int i=0;i<sen.length;i++){
		if(isDigit(sen[i])){//如果是数字开头
			StringBuilder sb=new StringBuilder();
			while(i<sen.length&&(isDigit(sen[i])||sen[i]=='.')){
				sb.append(sen[i]);
				i++;
			}
			i-=1;
			result.add(sb.toString());
			continue;
		}else if(isLetter(sen[i])){//如果是字母开头
			StringBuilder sb=new StringBuilder();
			while(i<sen.length&&isLetter(sen[i])){
				sb.append(sen[i]);
				i++;
			}
			i-=1;
			result.add(sb.toString());
			continue;
			
		}else if(Character.isWhitespace(sen[i]))//如果是空格
			continue;
		ArrayList<String>words=new ArrayList<String>();
		List<Chunk> clist=new ArrayList<Chunk>();
		CharNode firstNode=dict.getDict().get(sen[i]);
		if(firstNode==null){
			result.add(sen[i]+"");
		    continue;	
		}
		ArrayList<Integer> first=dict.maxMatch(firstNode, sen, i);//返回所有第一个字的所有匹配
		for(int j=0;j<first.size();j++){
			int secondBegin=first.get(j)+i+1;
			words.add(getString(sen,i,secondBegin));
			if(secondBegin<sen.length){
			CharNode secondNode=dict.getDict().get(sen[secondBegin]);
			ArrayList<Integer>second=dict.maxMatch(secondNode, sen, secondBegin);//返回第二个字符所有匹配
			for(int k=0;k<second.size();k++){
				int thirdBegin=secondBegin+second.get(k)+1;
				words.add(getString(sen,secondBegin,thirdBegin));
				if(thirdBegin<sen.length){
				int thirdEnd=dict.maxMatch(sen, thirdBegin)+thirdBegin;//第三个字的最大匹配
				words.add(getString(sen,thirdBegin,thirdEnd+1));
				clist.add(new Chunk(words,first.get(j)));
				words.remove(words.lastIndexOf(getString(sen,thirdBegin,thirdEnd+1)));//出栈
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

//将字符数组转换为字符串
public String getString(char[]arr,int begin,int end){
	StringBuilder result=new StringBuilder();
	for(int i=begin;i<end;i++)
		result.append(arr[i]);
	return result.toString();
}
//判断是否中文
public boolean isChinese(char c){
	if((c >= 0x4e00)&&(c <= 0x9fbb))
	   return true;  
	else
		return false;
}
//判断是否为字母a-z，A-Z
public boolean isLetter(char c){
	return ('a'<=c&&c<='z')||('A'<=c&&c<='Z');
}
//判断是否为数字
public boolean isDigit(char c){
	return '0'<=c&&c<='9';
}

public static void main(String []args){
//	MMsegComplex mmseg=new MMsegComplex();
//	Dictionary dict=new Dictionary();
//	//dict.loadDictionary();
//	List<String> result=mmseg.analysis("首先我们要在网页中加上常用的几个标签文本，比如姓名，电子邮件，内容以及提交按钮，然后我们针对这些字段文本增加和修改样式就可以",dict);
//
//	for(int i=0;i<result.size();i++)
//		System.out.print(result.get(i)+"|");
//	System.out.println();	
}

}
