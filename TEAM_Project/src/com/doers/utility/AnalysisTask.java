package com.doers.utility;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import com.doers.model.TextAnalysis;

public class AnalysisTask implements Runnable {

	public static final Set<String> positiveWord = new HashSet<String>();
	public static final Set<String> negativeWord = new HashSet<String>();
	AtomicInteger activity;

	TextAnalysis textAnalysis;
	static{
		String[] mypositve = { "Happy", "amazing" };
		String[] myNegative = { "Cry", "Hate" };
		positiveWord.addAll(Arrays.asList(mypositve));
		negativeWord.addAll(Arrays.asList(myNegative));
	}

	public AnalysisTask(TextAnalysis textAnalysis, int activity) {
		this.textAnalysis = textAnalysis;
		this.activity = new AtomicInteger(activity);		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		textAnalysis.setStart_time(new Date());
		String text = textAnalysis.getText();
		activity.decrementAndGet();
		updateActivity();
		String sentiment = "neutral";
		int count = 0;

		ExecutorService executor = Executors.newFixedThreadPool(2);
		Future<Map<String, Integer>> futWords = executor.submit(new WordCountTask(text));
		Future<Map<Character, Integer>> futVowel = executor.submit(new VowelCountTask(text));
		Map<String, Integer> myWords = new HashMap<String, Integer>();
		Map<Character, Integer> myVowels = new HashMap<Character, Integer>();
		try {
			myWords = futWords.get();
			activity.decrementAndGet();
			updateActivity();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			myVowels = futVowel.get();
			activity.decrementAndGet();
			updateActivity();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		textAnalysis.setWord_freq(myWords);
		textAnalysis.setVowel_freq(myVowels);
		Iterator<String> myItr = (Iterator<String>) myWords.keySet().iterator();
		while (myItr.hasNext()) {
			String word = myItr.next();
			if (positiveWord.contains(word)) {
				count += myWords.get(word) * 1;
			} else if (negativeWord.contains(word)) {
				count += myWords.get(word) * -1;
			}
		}
		if (count > 2)
			sentiment = "positive";
		else if (count < -2)
			sentiment = "negative";

		activity.decrementAndGet();
		updateActivity();
		textAnalysis.setSentiments(sentiment);
		textAnalysis.setEnd_time(new Date());
		if (activity.get() == 0) {
			textAnalysis.setStatus("completed");
		}
	}

	public void updateActivity() {
		textAnalysis.setTaskCompletion((4 - activity.get()) + "/4");
	}

}
