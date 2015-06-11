package com.mmseg.algorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;


public class Dictionary {
	private File dicPath;	//词库目录
	private  HashMap<Character, CharNode> dict;

//列出目录下所有words开头的字典	
	protected File[] listWordsFiles() {
		return dicPath.listFiles(new FilenameFilter() {

			public boolean accept(File dir, String name) {

				return name.startsWith("words") && name.endsWith(".dic");
			}

		});
	}
//截取第一个字符以后的字符数组
private static char[] tail(String str) {
	char[] cs = new char[str.length()-1];
	str.getChars(1, str.length(), cs, 0);
	return cs;
}
//加载字典到内存
	public void loadDictionary(String dir){
		dict=new HashMap<Character,CharNode>();
		dicPath=new File(dir);
		File chars=new File(dir,"chars.dic");
		BufferedReader r;
		try{
		r=new BufferedReader(new InputStreamReader (new FileInputStream(chars),"UTF-8"));
		String line;
		while((line=r.readLine())!=null){
			if(line == null || line.startsWith("#")) {
				continue;
			}
			String []w=line.split(" ");
			if(w.length<2) continue;
			CharNode cn = new CharNode();
			cn.setFreq((int)(Math.log(Integer.parseInt(w[1]))*100));
			dict.put(w[0].charAt(0), cn);			
		}
		r.close();
		File[] fileList=listWordsFiles();
		for(int i=0;i<fileList.length;i++){
			r=new BufferedReader(new InputStreamReader (new FileInputStream(fileList[i]),
					"UTF-8"));
			while((line=r.readLine())!=null){
				if(line == null || line.startsWith("#")) {
					continue;
				}
				if(line.length()<2)
					continue;
				//System.out.println(line);
				CharNode cn = dict.get(line.charAt(0));
				if(cn == null) {
					cn = new CharNode();
					dict.put(line.charAt(0), cn);

				}
				cn.addWordTail(tail(line));
			}
			r.close();
		}
		}catch(Exception e){e.printStackTrace();}
	}
//查看offset后的len之后是否匹配
public boolean match(CharNode node,char[]sen,int offset,int len){
	return node.match(sen, offset+1, len);
}
//返回offset的最大匹配
	public int maxMatch(char[] sen, int offset) {
		CharNode node = dict.get(sen[offset]);
		if(node==null)
			return 0;
		else
		return node.maxMatch( sen, offset+1);
	}

//返回所有匹配
	public ArrayList<Integer> maxMatch(CharNode node, char[] sen, int offset) {
		ArrayList<Integer>tailLens=new ArrayList<Integer>();
		tailLens.add(0);
		if(node != null) {
			return node.maxMatch(tailLens, sen, offset+1);
		}
		return tailLens;
	}

public File getDicPath() {
		return dicPath;
	}
	public void setDicPath(File dicPath) {
		this.dicPath = dicPath;
	}
	public HashMap<Character, CharNode> getDict() {
		return dict;
	}
	public void setDict(HashMap<Character, CharNode> dict) {
		this.dict = dict;
	}
public static void main(String []args){
	Dictionary dic=new Dictionary();
	//dic.loadDictionary();
	
}
	
}
