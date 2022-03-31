package coin.sarvatech.glowingBulbs;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;

public class GlowingBulbK_Finder2 {
	final static int NUM_SWITCHES = 40;

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
		result = getKthBulbGlowing(switchesString1, KthBulb1);
		System.out.println("Answer - Index of bulb glowing = " + result);
		System.out.println("=============");
		System.out.println("Given Input String = " + switchesString2 + ", Find Bulb Glowing = " + KthBulb2 + "! Expected = " + expectedOut2);
		result = getKthBulbGlowing(switchesString2, KthBulb2);
		System.out.println("Answer - Index of bulb glowing = " + result);
		System.out.println("=============");
		System.out.println("Given Input String = " + switchesString3 + ", Find Bulb Glowing = " + KthBulb3 + "! Expected = " + expectedOut3);
		result = getKthBulbGlowing(switchesString3, KthBulb3);
		System.out.println("Answer - Index of bulb glowing = " + result);
		System.out.println("=============");
		System.out.println("Given Input String = " + switchesString4 + ", Find Bulb Glowing = " + KthBulb4 + "! Expected = " + expectedOut4);
		result = getKthBulbGlowing(switchesString4, KthBulb4);
		System.out.println("Answer - Index of bulb glowing = " + result);
	}

	static int getNextGlowing(int counterArr[], int numCounters) {
		int selVal = 0;

		if (numCounters <= 0) {
			return -1; // Error
		}
		selVal = counterArr[0];
		for (int i = 1; i < numCounters; i++) {
			if (selVal > counterArr[i]) {
				selVal = counterArr[i];
			}
		}
		return selVal;
	}

	static void updateSwitchCounters(int curSelVal, int switchedOns[], int switchCounter[], int numCounters) {
		if (numCounters <= 0) {
			return; // Error
		}
		for (int i = 0; i < numCounters; i++) {
			if (switchCounter[i] == curSelVal) {
				switchCounter[i] += switchedOns[i];
			}
		}
	}

	static int getNumOnSwitches(String switchIndicators) {
		int switchedOnIx = 0;
		
		for (int i = 0; i < switchIndicators.length(); i++) {
			if (switchIndicators.charAt(i) == '1') {
				switchedOnIx++;
			}
		}
		return switchedOnIx;
	}
	
	static int getKthBulbGlowing(String switchIndicators, int kthBulb) {
		int switchedOns[] = null;
		int switchGlowCounters[] = null;
		int switchedOnIx = 0;
        long startTime = System.currentTimeMillis();
		final int init_size = getNumOnSwitches(switchIndicators);

		if (init_size == 0) { // Violation - "At least one switch is on"
			return -1;
		}
		switchedOns = new int[init_size];
		switchGlowCounters = new int[init_size];
		for (int k = 0; k < switchIndicators.length(); k++) {
			if (switchIndicators.charAt(k) == '1') {
				switchedOns[switchedOnIx] = k + 1;
				switchGlowCounters[switchedOnIx] = k + 1;
				switchedOnIx++;
			}
		}
		int curBulbIndex = 0;
		for (int curBulb = 0; curBulb < kthBulb; curBulb++) {
			curBulbIndex = getNextGlowing(switchGlowCounters, switchedOnIx);
			updateSwitchCounters(curBulbIndex, switchedOns, switchGlowCounters, switchedOnIx);
		}
		// ending time
        long endTime = System.currentTimeMillis();
        System.out.println("### Time Taken - " + (endTime - startTime) + "ms");		
		return curBulbIndex;
	}
}

