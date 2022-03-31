package coin.sarvatech.glowingBulbs.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GlowingBulbs_IO {
	
	public static BufferedReader createBufferedReader(String fileName) {
		BufferedReader br = null;
		   try {
		     br = new BufferedReader(new FileReader(fileName));
		     return br;
		   } catch(FileNotFoundException e) {
		       System.err.println("Not found file " + fileName);
		   }
		   return null;
		}

	public static BufferedWriter createBufferedWriter(String fileName) {
		BufferedWriter bw = null;
		   try {
		     bw = new BufferedWriter(new FileWriter(fileName));
		     return bw;
		   } catch(FileNotFoundException e) {
		       System.err.println("File not Found " + fileName);
		   } catch (IOException e) {
		       System.err.println("Unable to write " + fileName);			   
		   }
		   return null;
		}
    
	public static int getNumTests(BufferedReader bufferedReader) {
		String line = null;
		int numTests = 0;
		
		try {
			if ((line = bufferedReader.readLine()) != null) {
				Scanner scanner = new Scanner(line);
				if (!scanner.hasNextInt()) {
					return -1;
				}
				numTests = scanner.nextInt();
				scanner.close();
				return numTests;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public static int getTestCase(final int NUM_SWITCHES, BufferedReader bufferedReader, StringBuffer switchIndicators) {  // returns K'th Bulb expected to be on or -1
		try {
			String line1 = bufferedReader.readLine();
			
			if (line1 == null) {
				return -1; // Input Error
			}
			line1 = line1.trim();
			if (line1.length() != NUM_SWITCHES) {
				return -1; // Input Error
			}

			String line2 = bufferedReader.readLine();
			Scanner scanner = new Scanner(line2);
			if (!scanner.hasNextInt()) {
				return -1; // Input Error
			}
			int kthBulb = scanner.nextInt();

			switchIndicators.append(line1);
			return kthBulb;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return -1;
		}
		
	}
	
	public static int putKthBulbIndex(BufferedWriter bufferedWriter, int kthBulbIx, int testIx) {
		try {
			bufferedWriter.write("K'th Glowing Bulb Index for test number " + testIx + " = " + kthBulbIx);
			bufferedWriter.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		return 0;
	}
}
