package com.doers.utility;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

public class VowelCountTask implements Callable {
	
	String text;
	Set<Character> myVowels=new HashSet();
	Map<Character, Integer> myVowelMap = new HashMap<Character, Integer>();
	
	public VowelCountTask(String text){
		this.text=text;
		Character[] ch={'a','e','i','o','u','A','E','I','O','U'};
		Arrays.asList(ch);		
		myVowels.addAll(Arrays.asList(ch));
	}

	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		char ch;
		for(int i=0;i<text.length();i++){
			ch = text.charAt(i);
			if(myVowels.contains(ch)){
				if(myVowelMap.containsKey(ch)){
					myVowelMap.put(ch, myVowelMap.get(ch)+1);
				}else{
					myVowelMap.put(ch, 1);
				}
				
			}
		}
		return myVowelMap;
	}
	
	

}
