package com.doers.model;

public class TextData {
	
	private int id;
	private String text;
	
	public TextData(){		
	}	
	
	public TextData(String text){
		this.text=text;
	}
	
	public TextData(int id, String text){
		this.id=id;
		this.text=text;
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
