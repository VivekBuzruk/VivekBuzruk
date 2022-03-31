package coin.sarvatech.glowingBulbs;

import java.io.*;

import coin.sarvatech.glowingBulbs.utils.GlowingBulbs_IO;
import coin.sarvatech.glowingBulbs.utils.GlowingBulbs_IterativeFinder;

public class GlowingBulbK_Finder4 {
	static String inFileName = "testdata/bulbSeq.csv";
	static String outFileName = "out/KthBulb.csv";
	final static int NUM_SWITCHES = 40;
	static boolean printTimeTaken = false;

	private static void printUsage(String prompt) {
		System.out.println(prompt + "Usage: GlowingBulbsFinder  [--timeTaken] [--inpFile inFilename] [--outFile outFilename]");		
	}
	private static int commandArgs(String[] args) {
		// check to see if the String array is empty
		if (args.length == 0) {
			System.out.println("No commandline arguments passed, Continuing with Default files!");
			printUsage("For Information - ");
			return 0;
		}

		// For each String in the String array
		// print out the String.
		boolean inputFileProcessed = false;
		boolean outputFileProcessed = false;
		for (int i = 0; i < args.length; i++) {
			System.out.println("Argument " + i + " => " + args[i]);
			
			if (args[i].compareTo("--timeTaken") == 0) {
				if (printTimeTaken) {
					System.out.println("Option --timeTaken already set ");
				} else {
					printTimeTaken = true;
				}
			} else if (!inputFileProcessed && args[i].compareTo("--inpFile") == 0) {
				if (i + 1 < args.length) {
					inFileName = new String(args[i+1]);
					i++;
					inputFileProcessed = true;
				} else {
					printUsage("");
					return -1;					
				}
			} else if (!outputFileProcessed && args[i].compareTo("--outFile") == 0) {
				if (i + 1 < args.length) {
					outFileName = new String(args[i+1]);
					i++;
					outputFileProcessed = true;
				} else {
					printUsage("");
					return -1;					
				}
			} else {
				printUsage("");
				return -1;					
			}
		}
		return 1;
	}

	public static void main(String[] args) {
		
		int argsProcessed = commandArgs(args);
		
		if (argsProcessed == -1) {
			System.exit(1);
		}
		System.out.println("Input file to be used = " + inFileName);
		System.out.println("Output file to be used = " + outFileName);
		BufferedReader bufferedReader = GlowingBulbs_IO.createBufferedReader(inFileName);
		if (bufferedReader == null) {
			System.out.println("Unable to open file " + inFileName + " for Reading; Exiting");
			return;
		}

		BufferedWriter bufferedWriter = GlowingBulbs_IO.createBufferedWriter(outFileName);
		if (bufferedWriter == null) {
			System.out.println("Unable to open file " + outFileName + " for Writing; Exiting");
			return;
		}

		int numTests = 0;

		numTests = GlowingBulbs_IO.getNumTests(bufferedReader);
		for (int testIx = 0; testIx < numTests; testIx++) {
			StringBuffer switchIndicators = new StringBuffer();

			int kthBulb = GlowingBulbs_IO.getTestCase(NUM_SWITCHES, bufferedReader, switchIndicators);

			if (kthBulb == -1) {
				System.out.println("Invalid input, No K'th Bulb specified. Exiting.");
				return;
			}

			System.out.println("Given Switch Positions = " + switchIndicators.toString());
			System.out.println("Find Kth Glowing Bulb, where K = " + kthBulb);

			int kthBulbIx = GlowingBulbs_IterativeFinder.getKthBulbVal(NUM_SWITCHES, 
					                                                   switchIndicators.toString(), kthBulb, printTimeTaken);
			System.out.println("*** Selected BulbVal = " + Integer.toString(kthBulbIx));

			GlowingBulbs_IO.putKthBulbIndex(bufferedWriter, kthBulbIx, testIx + 1);
		}

		try {
			bufferedReader.close();
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
