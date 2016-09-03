/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;


public class Hangman extends ConsoleProgram {

	private HangmanLexicon hngLxcn = new HangmanLexicon();
	private String input = "";
	private final int NUMBER_OF_TRIES = 8;
	private boolean flag;
	private char chrInput;
	private String secretWord;
	private char chrSecretWord;
	private String newSecretWord;
	private int wrongGuess;
	private int triesLeft;
	private boolean winChecker;
	private String temp;

	public void run() {

		setup();
		
		while ((triesLeft > 0) && (winChecker == false)){
			
		displayStatus();
		play();
		checkWin();
			
		}
		
		checkWinOrLose();
		
	}
	
	private void setup(){
		int wrdNm = rgen.nextInt(0,6);
		secretWord = hngLxcn.getWord(wrdNm).toUpperCase();
		newSecretWord = "";
		for(int i = 0; i < secretWord.length(); i++){
			newSecretWord += "-";
			}
		triesLeft = NUMBER_OF_TRIES;
		winChecker = false;
		//test
		println(secretWord);
	}
	
	private void displayStatus(){		
		println("Welcome to Hangman!");
		println("The word now looks like this: " + newSecretWord);
		println("You have " + triesLeft + " guesses left.");
	}
	
	private void play(){
		
		guessWord();
		checkWord();
		countWrongGuess();

	}
	
	private void guessWord(){
		input = "";
		flag = false;
		while (flag != true){
			input = readLine ("Your guess: ").toUpperCase();
			if(input.length() > 1 || input.length() < 1){
				println("Your guess is illegal.");
				flag = false;
			} else {
			flag = true;
			}
		}
	}
	
	private void checkWord(){
		
		chrInput = input.charAt(0);
		temp = newSecretWord;
		for(int i = 0; i < secretWord.length(); i ++){
			chrSecretWord = secretWord.charAt(i);
			if (chrInput == chrSecretWord){	
				String word1 = newSecretWord.substring(0, i);
				String word2 = newSecretWord.substring(i + 1, newSecretWord.length());		
				newSecretWord = word1 + chrInput + word2;
			}
		}
			
	}
	
	private void countWrongGuess(){
		if (newSecretWord.compareToIgnoreCase(temp) == 0){
			wrongGuess += 1;
			triesLeft = NUMBER_OF_TRIES - wrongGuess;
		} 
	}
	
	private void checkWin(){

		if (newSecretWord.compareToIgnoreCase(secretWord) == 0){
			winChecker = true;
		}
	}
	
	private void checkWinOrLose(){
		if (triesLeft <= 0){
			println("You lose.");
			} else {
			println("You win.");
			}
		
	}
	
	private RandomGenerator rgen = RandomGenerator.getInstance();

}
