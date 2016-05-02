package com.mob41.slidetext;

public class Slidetext {
	
	private final String text;
	
	private final int max;
	
	/**
	 * Creates a new slide text instance.
	 * 
	 * @param text The String that you want to process slide text.
	 * @param maxLettersPerFrame The amount of slots of text in each frame.
	 */
	public Slidetext(String text, int maxLettersPerFrame){
		this.text = text;
		this.max = maxLettersPerFrame;
	}

	public String[] getFrames(){
		return getFrames(true, true);
	}
	
	public String[] getFrames(boolean blanks){
		return getFrames(blanks, blanks);
	}
	
	public String[] getFrames(boolean blanksOntheLeft, boolean blanksOntheRight){
		String text = this.text;
		if (blanksOntheLeft){
			for (int i = 0; i < max; i++){
				text = " " + text;
			}
		}
		if (blanksOntheRight){
			for (int i = 0; i < max; i++){
				text = text + " ";
			}
		}
		int slides = text.length() - 1;
		String[] arr = new String[slides];
		for (int i = 0; i < slides; i++){
			if ((i + max) < text.length()){
				arr[i] = text.substring(i, i + max - 1);
			} else {
				arr[i] = text.substring(i, text.length());
			}
		}
		return arr;
	}
	
}
