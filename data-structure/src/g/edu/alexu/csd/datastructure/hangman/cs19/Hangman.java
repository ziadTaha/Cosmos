package g.edu.alexu.csd.datastructure.hangman.cs19;

import java.io.File;
import java.util.Random;
import java.util.Scanner;

import eg.edu.alexu.csd.datastructure.hangman.IHangman;
/**
 * @author ziad
 *
 */
public class Hangman implements IHangman {
	/**
	 * @serialField no.of
	 *                  words in dictionary
	 */
	private int n = 0;
	/**
	 * @serialField array
	 *                  of words
	 */
	private String[] dWords;
	/**
	 * @serialField scanner
	 */
	private Scanner x;
	/**
	 * @serialField the
	 *                  chosen word
	 */
	private String secretWord = null;
	/**
	 * @serialField array
	 *                  of guesses
	 */
	char[] guess;
	/**
	 * @serialField no.of
	 *                  wrong guesses
	 */
	int wrong = 0;
	/**
	 * @serialField max
	 *                  no.of wrong guesses
	 */
	Integer maxW = null;
	/**
	 * @serialField just
	 *                  a flag
	 */
	int flag = 0;

	/**
	 * Set the dictionary Note: You
	 * should implement another function which will
	 * read a file into array then call
	 * this function to set the dictionary. words:
	 * an array of words
	 */

	public void readFileDictionary() {
		try {
			x = new Scanner(new File("word.txt"));
		} catch (Exception e) {
			System.out.println("error!!");
		}
		while (x.hasNext()) {
			dWords[n] = x.next();
			n++;
		}

	}

	@Override
	public void setDictionary(final String[] words) {
		// TODO Auto-generated method stub
		if (words == null) {
			this.dWords = null;
		} else {
			this.dWords = words;
		}
		n = words.length;

	}

	@Override
	public String selectRandomSecretWord() {
		// TODO Auto-generated method stub
		if (n > 0) {
			Random r = new Random();
			int m = r.nextInt(n);
			secretWord = dWords[m];
			guess = new char[secretWord.length()];
			for (int i = 0; i < secretWord.length(); i++) {
				if (secretWord.charAt(i) == ' ') {
					guess[i] = ' ';
				} else {
					guess[i] = '-';
				}
			}
			return secretWord;
		} else {
			return null;
		}
	}

	@Override
	public String guess(final Character c) throws Exception {
		// TODO Auto-generated method stub
		int i, j = 0;
		if (secretWord.isEmpty()) {
			throw new Exception();
		} else if (secretWord.charAt(0) == ' ') {
			throw new Exception();
		} else if (this.maxW == null) {
			throw new Exception();
		}
		for (i = 0; i < secretWord.length(); i++) {
			if (guess[i] != ' ' && c != null) {
				if (c == secretWord.charAt(i)
|| Character.toUpperCase(c) == secretWord.charAt(i)
|| Character.toLowerCase(c) == secretWord.charAt(i)) {
					guess[i] = secretWord.charAt(i);
					j++;
				}
			}
		}

		// throw new Exception();
		// return "error";
		if (j == 0) {
			wrong++;
		}
		if (wrong >= maxW) {
			return null;
		} else {
			return String.valueOf(guess);
		}
		// System.out.println("Error!!");

	}

	@Override
	public void setMaxWrongGuesses(final Integer max) {
		if (max == null) {
			this.maxW = 1;
		} else {
			this.maxW = max;
		}
		// TODO Auto-generated method stub

	}
}
