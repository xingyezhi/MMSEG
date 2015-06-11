package com.mmseg.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class CharNode {
private int freq=0;
private int maxLen = 0;	
private CharTree tree = new CharTree();
private int wordNum = 0;

//��Ӵʻ�
public void addWordTail(char[] wordTail) {
	tree.add(wordTail);
	wordNum++;
	if(wordTail.length > maxLen) {
		maxLen = wordTail.length;
	}
}
//����offset�����ƥ�������
public int maxMatch(char[] sen, int wordTailOffset) {
	return tree.maxMatch(sen, wordTailOffset);
}
//��������ƥ�������
public ArrayList<Integer> maxMatch(ArrayList<Integer> tailLens, char[] sen, int wordTailOffset) {
	return tree.maxMatch(tailLens, sen, wordTailOffset);
}
//����offset��tailLen�����Ƿ�ƥ��
public boolean match(char[] sen, int offset, int tailLen) {
	return tree.match(sen, offset, tailLen);
}


public int getFreq() {
	return freq;
}
public void setFreq(int freq) {
	this.freq = freq;
}
public int getMaxLen() {
	return maxLen;
}
public void setMaxLen(int maxLen) {
	this.maxLen = maxLen;
}
public CharTree getTree() {
	return tree;
}
public void setTree(CharTree tree) {
	this.tree = tree;
}
public int getWordNum() {
	return wordNum;
}
public void setWordNum(int wordNum) {
	this.wordNum = wordNum;
}


//���Ľڵ���
class TreeNode{
	char key;
	Map<Character, TreeNode> subNodes;
	boolean isLeaf;
	public TreeNode(char key) {
		this.key = key;
		subNodes = new HashMap<Character, TreeNode>();
	}
	
	public void add(char k, TreeNode sub) {
		subNodes.put(k, sub);
	}
	
	public TreeNode subNode(char k) {
		return subNodes.get(k);
	}
	public boolean isLeaf() {
		return isLeaf;
	}
}

//����
class CharTree{
TreeNode head = new TreeNode(' ');
//��ӵ�����
	public void add(char[] w) {
		if(w.length < 1) {
			return;
		}
		TreeNode p = head;
		for(int i=0; i<w.length; i++) {
			TreeNode n = p.subNode(w[i]);
			if(n == null) {
				n = new TreeNode(w[i]);
				p.add(w[i], n);
			}
			p = n;
		}
		p.isLeaf = true;
	}
//����sen��offset�����ƥ�������
	public int maxMatch(char[] sen, int offset) {
		int idx = offset - 1;
		TreeNode node = head;
		for(int i=offset; i<sen.length; i++) {
			
			node = node.subNode(sen[i]);
			if(node != null) {
				if(node.isLeaf()) {
					
					idx = i; 
				}
			} else {
				break;
			}
		}
		return idx - offset + 1;
	}
//��������ƥ���ƫ����	
	public ArrayList<Integer> maxMatch(ArrayList<Integer> tailLens, char[] sen, int offset) {
		TreeNode node = head;
		for(int i=offset; i<sen.length; i++) {
			node = node.subNode(sen[i]);
			if(node != null) {
				if(node.isLeaf()) {
					tailLens.add(i-offset+1); 
				}
			} else {
				break;
			}
		}
		return tailLens;
	}
//�鿴sen��offset���len���ַ��Ƿ�ƥ��	
	public boolean match(char[] sen, int offset, int len) {
		TreeNode node = head;
		for(int i=0; i<len; i++) {
			node = node.subNode(sen[offset+i]);
			if(node == null) {
				return false;
			}
		}
		return node.isLeaf();
	}
}
}
