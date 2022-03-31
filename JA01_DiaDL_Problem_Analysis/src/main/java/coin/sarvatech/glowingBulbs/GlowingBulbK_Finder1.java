package coin.sarvatech.glowingBulbs;

import java.io.*;
import java.util.Scanner;
import java.util.regex.*;

public class GlowingBulbK_Finder1 {
	final static int MAX_NUM_SWITCHES = 40;

	public static void main(String[] args) {
		String switchesString1 = "0110000000000000000000000000000000000000";
		int KthBulb1 = 5;
		int expectedOut1 = 8;
		String switchesString2 = "0010000000000000000000000000000000000000";
		int KthBulb2 = 5;
		int expectedOut2 = 15;
		String switchesString3 = "0100000000100000001000100000101000001000";
		int KthBulb3 = 16807;
		int expectedOut3 = 26866;
		String switchesString4 = "0000000000100000000000000000000000001000";
		int KthBulb4 = 13001;
		int expectedOut4 = 112585;
		
		int result = 0;

		System.out.println("Given Input String = " + switchesString1 + ", Find Bulb Glowing = " + KthBulb1 + "! Expected = " + expectedOut1);
		result = getKthBulbGlowing(MAX_NUM_SWITCHES, switchesString1, KthBulb1);
		System.out.println("Answer - Index of bulb glowing = " + result);
		System.out.println("=============");
		System.out.println("Given Input String = " + switchesString2 + ", Find Bulb Glowing = " + KthBulb2 + "! Expected = " + expectedOut2);
		result =  getKthBulbGlowing(MAX_NUM_SWITCHES, switchesString2, KthBulb2);
		System.out.println("Answer - Index of bulb glowing = " + result);
		System.out.println("=============");
		System.out.println("Given Input String = " + switchesString3 + ", Find Bulb Glowing = " + KthBulb3 + "! Expected = " + expectedOut3);
		result = getKthBulbGlowing(MAX_NUM_SWITCHES, switchesString3, KthBulb3);
		System.out.println("Answer - Index of bulb glowing = " + result);
		System.out.println("=============");
		System.out.println("Given Input String = " + switchesString4 + ", Find Bulb Glowing = " + KthBulb4 + "! Expected = " + expectedOut4);
		result = getKthBulbGlowing(MAX_NUM_SWITCHES, switchesString4, KthBulb4);
		System.out.println("Answer - Index of bulb glowing = " + result);
	}

    static boolean isBulbIndexFactor(int bulbIndex, int switchedOns[], int switchedOnIx) {
    	for (int i = 0; i < switchedOnIx; i++) {
    		if (bulbIndex % switchedOns[i] == 0) {
    			return true;
    		}
    	}
    	return false;
    }

	static int getKthBulbGlowing(int max_num_switches, String switchIndicators, int kthBulb) {
		int switchedOns[] = new int[max_num_switches];
		int switchedOnIx = 0;


		for (int k = 0; k < max_num_switches; k++) {
			if (switchIndicators.charAt(k) == '1') {
				switchedOns[switchedOnIx] = k + 1;
				switchedOnIx++;
			}
		}
		if (switchedOnIx == 0) { // Violation - "At least one switch is on"
			return -1;
		}
        // starting time
        long startTime = System.currentTimeMillis();
        
		int glowingBulbIndex = 0; 
		for (int bulbIndex = 2; bulbIndex < Math.pow(10, 15); bulbIndex++) { // Start with 1'st Prime
			if (isBulbIndexFactor(bulbIndex, switchedOns, switchedOnIx)) {
				glowingBulbIndex++;
				if (glowingBulbIndex == kthBulb) {
					// ending time
			        long endTime = System.currentTimeMillis();
			        System.out.println("### Time Taken - " + (endTime - startTime) + "ms");
					return bulbIndex;
				}
			}
		}
		return -1;   // Not found
	}
}

