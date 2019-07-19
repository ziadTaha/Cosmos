package g.edu.alexu.csd.datastructure.hangman.cs19;

/**
 * @author ahmed hamdy
 *
 */
public class Test {
	/**
	 *
	 * @param args
	 *            main method param
	 * @throws Exception
	 *             dictionary or secret word null
	 */
	public static void main(final String[] args) throws Exception {
		Hangman hangman = new Hangman();
		// Read from file here
		// String dictionary[] = readFile();
		String[] dictionary = new String[] {"EGYPT"};
		hangman.setDictionary(dictionary);
		hangman.selectRandomSecretWord();
		hangman.setMaxWrongGuesses(1);
		System.out.println(hangman.guess('y'));
	}

}
