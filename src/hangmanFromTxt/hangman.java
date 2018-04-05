/*
 * This code modify the hangman method 
 * it read the word from a text file and use it in the game
 */
package hangmanFromTxt;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class hangman 
{
	//start a new game if user want to continue
	public static void main(String[] arges) throws FileNotFoundException
	  {
		  
	      boolean newGame = true;
	      while(newGame == true)
	      {
	         newGame = actualGame();
	      }

	   }
	
	   //the actual game that allow user to guess word
	   public static boolean actualGame() throws FileNotFoundException
	   {
		  //get a word from word bank
	      String currentWord = guessWord();
	      //replace the word with *
	      String guess = replaceWord(currentWord);
//	      System.out.print( "The word to be guessed : " + currentWord  + " ");
	      
	      //this count error and check if game 
	      int error = 0;
	      boolean gameOver = false;
	      
	      while (gameOver==false)
	      {
	    	 //allow user to input a character and check if the word is true and continue the game
	         char letterGuessed = humanInput(guess);
	         String currentGuess = checkWord(currentWord,guess,letterGuessed);
	         if(currentGuess.charAt(0)== 'T')
	         {
	            error+=1;
	            currentGuess =  splitWord(currentGuess);
	         }
	         guess = currentGuess;
	         gameOver = isGameOver(currentWord, guess);
	      }
	      char continueGame = continueGame(currentWord, error);
	      if(continueGame == 'y')
	      {
	         return true;
	      }
	      else
	      {
	         System.out.print("Thank you for playing!");
	         return false;
	      }

	   }
	   
	   //read from a file to get word for word bank
	   public static String[] wordBank() throws FileNotFoundException
	   {

			String[] wordBank = null ;
			java.io.File file = new java.io.File("C:\\Users\\Rong\\Desktop\\self written code\\hangmanFromTxt\\hangman.txt");
			try
			(
					Scanner input = new Scanner(file);
			)
			{
				while(input.hasNext())
				{
					String nextLine = input.nextLine();
					wordBank = nextLine.split(" ");
				}
			}
	      return wordBank;
	   }
	   
	   //get a random word from the word bank
	   public static String guessWord() throws FileNotFoundException
	   {
	      String[] wordBank = wordBank();
	      int i = (int) (Math.random()*wordBank.length);
	      return wordBank[i];

	   }
	   
	   //replace all word with * so that user can guess
	   public static String replaceWord(String word)
	   {
	      String guessWord = "";
	      for (int i = 0; i < word.length(); i++ )
	      {
	         guessWord = guessWord + "*";
	      }
	      return guessWord;
	   }
	   
	   //read the input from the user
	   public static char humanInput(String guess)
	   {
	      Scanner input = new Scanner(System.in);
	      System.out.print("\n(Guess) Enter a letter in word " + guess + " >");
	      char letter = input.nextLine().charAt(0);

	      return letter;
	   }
	   
	   //check if the actual word contain the word and if it is replace
	   //the guess word and return that word
	   public static String checkWord(String word,String guess,char letter)
	   {

	      if(word.indexOf(letter) != -1)
	      {
	         if(guess.indexOf(letter)== -1)
	         {
	            int index = word.indexOf(letter);
	            while (index >= 0) 
	            {
	               StringBuilder newGuess = new StringBuilder(guess);
	               newGuess.setCharAt(index,letter);
	               guess = newGuess.toString();
	               index = word.indexOf(letter, index + 1);
	            }
	            return guess;


	         }
	         else
	         {
	            System.out.print("You have already guessed " + letter + ".\n");
	            return guess;
	         }
	      }
	      else
	      {
	         System.out.print(letter + " is not in the word.\n");
	         String errorSignal = "T" + guess ;
	         return errorSignal;

	      }

	   }
	   public static String splitWord(String word)
	   {
	      return word.substring(1, word.length());

	   }
	   public static boolean isGameOver(String word, String guess)
	   {
	      if (word .equals(guess))
	      {
	         return true;
	      }
	      else
	      {
	         return false;
	      }
	   }
	   public static char continueGame(String word, int time)
	   {
	      System.out.print("\nThe word is " + word 
	            + ". You missed " + time + " time.");
	      Scanner input = new Scanner(System.in);
	      System.out.print("\nDo you want to guess another word? Enter y or n>");
	      char letter = input.nextLine().charAt(0);

	      return letter;
	   }

}
