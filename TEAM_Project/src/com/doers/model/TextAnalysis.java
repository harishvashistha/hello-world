package com.doers.model;

import java.util.Date;
import java.util.Map;

public class TextAnalysis {
	
	private int id;
	private String text;
	private Date start_time;
	private Date end_time;
	private String status;
	private Map<String, Integer> word_freq;
	private Map<Character,Integer> vowel_freq;
	private String sentiments;	
	private String taskCompletion;
	
	public String getTaskCompletion() {
		return taskCompletion;
	}
	public void setTaskCompletion(String taskCompletion) {
		this.taskCompletion = taskCompletion;
	}
	public TextAnalysis(){
		
	}
	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Map<String, Integer> getWord_freq() {
		return word_freq;
	}

	public void setWord_freq(Map<String, Integer> word_freq) {
		this.word_freq = word_freq;
	}

	public Map<Character, Integer> getVowel_freq() {
		return vowel_freq;
	}

	public void setVowel_freq(Map<Character, Integer> vowel_freq) {
		this.vowel_freq = vowel_freq;
	}

	public String getSentiments() {
		return sentiments;
	}

	public void setSentiments(String sentiments) {
		this.sentiments = sentiments;
	}	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}	
}
