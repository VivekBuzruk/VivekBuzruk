package coin.sarvatech.misc;

//Slightly modified version of original from https://math.hws.edu/eck/cs124/javanotes3/index.html
//
/*
   Command Line argument 1 - a text file for input
   Command Line argument 2 - a file for output.  
   Steps -
   1. Read all the words from the input file. // A "word" is defined to be any sequence of letters.
   2. Convert Words to lower case.  // "The" and "the" will count as the same word.
   3. Without repetition, write list of all the words in alphabetical order found in input file to the output file, with
   one word per line. 
*/

import java.io.*;
import java.util.ArrayList;

public class WordsInFile2 {

	static ArrayList<String> words; 

	static int wordCount; // The number of words currently stored in the array.

	static String inpFileName = null;
	static BufferedReader bufferedReader = null;
	static BufferedWriter bufferedWriter = null;
	static String outFileName = null;

	private static int init(String[] args) {
		if (args.length != 2) {
			System.err.println("Usage: WordsInFile inputTextFile wordsOutFile");
			return -1;
		}
		inpFileName = args[0];
		outFileName = args[1];
		return args.length;
	}

	public static void main(String[] args) {

		words = new ArrayList<String>();
		wordCount = 0;

		int numArgs = init(args);

		if (numArgs == -1) {
			System.out.println("Insufficient Information; Exiting");
			System.exit(1);
		}
		if (analyze_input() == -1) {
			System.exit(1);
		}

		printWordList();
	} // end main()

	static int analyze_input() {
		/*
		 * Read all the words from the input file and insert them into array of words.
		 * Handle I/O errors => print an error message and terminate the program.
		 */
		try (FileReader fr = new FileReader(inpFileName)) {
			int content = fr.read();
			while (content != -1) {
				if (!Character.isLetter((char) content)) {
					content = fr.read();
					continue;
				}
				StringBuffer newWord = new StringBuffer();

				newWord.append((char) content);
				while ((content = fr.read()) != -1 && Character.isLetter(content)) {
					newWord.append((char) content);
				}
				insertWord(newWord.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("############# Exiting : Error Reading input file ###############");
			return -1;
		}
		System.out.println("=========== Successfully created Wordlist ============");

		return 1;

	}

	static void insertWord(String w) {
		// Insert the word w into the array of words, unless it already
		// appears there. All the words in the array are in lower case,
		// and w is converted to lower case before it is processed.
		// Note - Words in the array are kept in alphabetical order.

		int pos = 0; // Position in the array where w belongs.

		w = w.toLowerCase();

		/*
		 * Find the position in the array where w belongs, after all the words that
		 * precede w alphabetically. If a copy of w already occupies that position, then
		 * it is not necessary to insert w, so return immediately.
		 */

		boolean wordFound = false;
		while (pos < wordCount) {
			String posWord = words.get(pos);
			int compareVal = posWord.compareTo(w);
			if (compareVal >= 0) {
				wordFound = compareVal == 0;
				break;
			}
			pos++;
		}

		if (pos < wordCount && wordFound)
			return;

		/*
		 * Put w into its correct position in the array. Move any words that come after
		 * w up one space in the array to make room for w.
		 */
		if (pos < 0 || pos > words.size()) {
			words.add(w);  // .set can also raise exception
		} else {
			words.add(pos, w);
		}
		wordCount++;

	} // end insertWord()

	static int printWordList() {
		/* Write all the words from the list to the output file. */
		try (FileWriter fw = new FileWriter(outFileName)) {

			for (int i = 0; i < wordCount; i++) {
				fw.write(words.get(i) + System.lineSeparator());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("############# Exiting : Error Writing words to outfile ###############");
			return -1;
		}

		System.out.println("=========== Successfully written words to outfile ============");

		return 1;
	}
}
