package com.doers.utility;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class WordCountTask implements Callable{
	
	String text;
	
	public WordCountTask(String text){
		this.text = text;
	}
	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		Map<String, Integer> myWords=new HashMap<String, Integer>();		
		String[] words =  text.split(" ");
		for(String word: words){
			if(myWords.containsKey(word))
				myWords.put(word, myWords.get(word)+1);
			else
				myWords.put(word, 1);			
		}
		return myWords;
	}

}
