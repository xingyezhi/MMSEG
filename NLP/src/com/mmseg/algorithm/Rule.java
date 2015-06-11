package com.mmseg.algorithm;

import java.util.ArrayList;
import java.util.List;

public class Rule {
//返回长度最长的串
public List<Chunk> getMaxMatch(List<Chunk> list){
	int maxLength=0;
	ArrayList<Chunk>result=new ArrayList<Chunk>();
	for(Chunk c:list){
		if(c.getTotalLength()>maxLength){
			maxLength=c.getTotalLength();
			result.clear();
			result.add(c);
		}else if(c.getTotalLength()==maxLength){
			result.add(c);
		}
	}
	return result;
}
//返回平均数最大的串
public List<Chunk> getLargestAver(List<Chunk> list){
	ArrayList<Chunk> result=new ArrayList<Chunk>();
	double maxAver=0;
	for(Chunk c:list){
		if(c.getAverage()>maxAver){
			maxAver=c.getAverage();
			result.clear();
			result.add(c);
		}else if(c.getAverage()==maxAver){
			result.add(c);
		}
	}
	return result;
}
//返回方差最小的串
public List<Chunk> getSmallestVar(List<Chunk> list){
	ArrayList<Chunk>result=new ArrayList<Chunk>();
	double minVar;
	if(list.size()<1)
		return null;
	result.add(list.get(0));
	minVar=list.get(0).getStandardAver();
	for(int i=1;i<list.size();i++){
		if(list.get(i).getStandardAver()<minVar){
			minVar=list.get(i).getStandardAver();
			result.clear();
			result.add(list.get(i));
		}else if(list.get(i).getStandardAver()==minVar)
			result.add(list.get(i));
	}
	return result;
}
//返回度最大的串
public List<Chunk> getMaxDegree(List<Chunk> list,Dictionary dic){
	ArrayList<Chunk> result=new ArrayList<Chunk>();
	int maxDegree=0;
	for(Chunk c:list){
		if(c.getDegree(dic)>maxDegree){
			maxDegree=c.getDegree(dic);
			result.clear();
			result.add(c);
		}else if(c.getAverage()==maxDegree){
			result.add(c);
		}
	}
	return result;
}
//综合规则
public Chunk getResult(List<Chunk>l, Dictionary dic){
	List<Chunk> result;
	result=getMaxMatch(l);//规则1
	if(result.size()==1)
		return result.get(0);
	result=getLargestAver(result);//规则2
	if(result.size()==1)
		return result.get(0);
	result=getSmallestVar(result);//规则3
	if(result.size()==1)
		return result.get(0);
	result=getMaxDegree(result,dic);//规则4
	return result.get(0);
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
