package com.mmseg.algorithm;

import java.util.ArrayList;
import java.util.List;

public class MMsegSimple {


public List<String> analysis(String text,Dictionary dict){
	ArrayList<String> result=new ArrayList<String>();
	char []sen=text.toCharArray();
	for(int i=0;i<sen.length;i++){
		if(isDigit(sen[i])){
			StringBuilder sb=new StringBuilder();
			while(i<sen.length&&(isDigit(sen[i])||sen[i]=='.')){
				sb.append(sen[i]);
				i++;
			}
			i-=1;
			result.add(sb.toString());
			continue;
		}else if(isLetter(sen[i])){
			StringBuilder sb=new StringBuilder();
			while(i<sen.length&&isLetter(sen[i])){
				sb.append(sen[i]);
				i++;
			}
			i-=1;
			result.add(sb.toString());
			continue;
			
		}else if(Character.isWhitespace(sen[i]))
			continue;
		int end=dict.maxMatch(sen, i)+i;
		result.add(getString(sen,i,end+1));
		i=end;
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
//判断是否为字母a-z，A-Z
public boolean isLetter(char c){
	return ('a'<=c&&c<='z')||('A'<=c&&c<='Z');
}
//判断是否为数字
public boolean isDigit(char c){
	return '0'<=c&&c<='9';
}
public static void main(String []args){
	MMsegSimple ms=new MMsegSimple();
	Dictionary dict=new Dictionary();
//	dict.loadDictionary();
	List<String> result=ms.analysis("价格和服务",dict);

	for(int i=0;i<result.size();i++)
		System.out.print(result.get(i)+"|");
	System.out.println();	
	
}
}
