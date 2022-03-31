package coin.sarvatech.misc;

// Slightly modified version of original from https://math.hws.edu/eck/cs124/javanotes3/index.html
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

public class WordsInFile1 {

   static String[] words;  // An array to hold the words from the file.
                           //   Note that the array will be expanded as 
                           //   necessary, in the insertWord() subroutine.
   
   static int wordCount;   // The number of words currently stored in
                           //   the array.

   static String inpFileName = null;
   static BufferedReader bufferedReader = null;
   static BufferedWriter bufferedWriter = null;
   static String outFileName = null;
 
	private static BufferedReader createBufferedReader(String fileName) {
		BufferedReader br = null;
		   try {
		     br = new BufferedReader(new FileReader(fileName));
		     return br;
		   } catch(FileNotFoundException e) {
		       System.err.println("Not found file" + fileName);
		   }
		   return null;
		}

	private static BufferedWriter createBufferedWriter(String fileName) {
		BufferedWriter bw = null;
		   try {
		     bw = new BufferedWriter(new FileWriter(fileName));
		     return bw;
		   } catch(FileNotFoundException e) {
		       System.err.println("File not Found" + fileName);
		   } catch (IOException e) {
		       System.err.println("Unable to write" + fileName);			   
		   }
		   return null;
		}
	
   private static int processArgs(String[] args) {
	   if (args.length != 2) {
		   System.err.println("Usage: WordsInFile inputTextFile wordsOutFile");
		   return -1;
	   }
	   inpFileName = args[0];
       outFileName = args[1];  
	   return args.length;
   }
   
   private static int getWords() {
        try (FileReader fr = new FileReader(inpFileName))
        {
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
            return -1;
        }
        System.out.println("=========== Successfully created Wordlist ============");
     
        /* Write all the words from the list to the ouput stream. */        
        try (FileWriter fw = new FileWriter(outFileName)) {

            for (int i = 0; i < wordCount; i++)       {
            	fw.write(words[i] + System.lineSeparator());
            }        	
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        System.out.println("=========== Successfully written words to outfile ============");
        
		return 1;
   }

   public static void main(String[] args) {
   
      words = new String[10];  // Start with space for 10 words.
      wordCount = 0;           // Currently, there are no words in the array.
      
      if (processArgs(args) == -1) {
    	  return;
      }
      getWords();
   
   } // end main()
   

   static void insertWord(String w) {
           // Insert the word w into the array of words, unless it already
           // appears there.  All the words in the array are in lower case,
           // and w is converted to lower case before it is processed.
           // Words in the array are kept in alphabetical order.
           // If the array has grown too big to hold w, then it is doubled
           // in size.

      int pos = 0;  // This will be the position in the array where w belongs.

      w = w.toLowerCase();
      
      /* Find the position in the array where w belongs, after all the
         words that precede w alphabetically.  If a copy of w already
         occupies that position, then it is not necessary to insert
         w, so return immediately. */

      while (pos < wordCount && words[pos].compareTo(w) < 0)
         pos++;
      if (pos < wordCount && words[pos].equals(w))
         return;
         
      /* If the array is full, make a new array that is twice as 
          big, copy all the words from the old array to the new,
          and set the variable, words, to refer to the new array. */

      if (wordCount == words.length) {
         String[] newWords = new String[words.length*2];
         System.arraycopy(words,0,newWords,0,wordCount);
         words = newWords;
      }
      
      // Push array values to create space
      for (int i = wordCount; i > pos; i--)
         words[i] = words[i-1];
      
      // Add word
      words[pos] = w;
      wordCount++;

   }  // end insertWord()
   
   
} 