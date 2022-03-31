package coin.sarvatech.glowingBulbs.utils;

public class GlowingBulbs_IterativeFinder {
	private static int min(int counterArr[], int numCounters) {
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

	private static void increment(int curSelVal, int switchedOns[], int switchCounter[], int numCounters) {
		if (numCounters <= 0) {
			return; // Error
		}
		for (int i = 0; i < numCounters; i++) {
			if (switchCounter[i] == curSelVal) {
				switchCounter[i] += switchedOns[i];
			}
		}
	}

	private static int numOnSwitches(final int NUM_SWITCHES, String switchIndicators) {
		int switchedOnIx = 0;

		for (int i = 0; i < NUM_SWITCHES; i++) {
			if (switchIndicators.charAt(i) == '1') {
				switchedOnIx++;
			}
		}
		return switchedOnIx;
	}

	public static int getKthBulbVal(final int NUM_SWITCHES, final String switchIndicators, final int kthBulb, final boolean printTimeTaken) {
		int switchedOns[] = null;
		int switchCounter[] = null;
		int switchedOnIx = 0;
		int curSelVal = 0;
		long startTime = System.currentTimeMillis();
		final int totalSwitchedON = numOnSwitches(NUM_SWITCHES, switchIndicators);

		assert (totalSwitchedON != 0);

		switchedOns = new int[totalSwitchedON];
		switchCounter = new int[totalSwitchedON];

		for (int k = 0; k < NUM_SWITCHES; k++) {
			if (switchIndicators.charAt(k) == '1') {
				switchedOns[switchedOnIx] = k + 1;
				switchCounter[switchedOnIx] = k + 1;
				switchedOnIx++;
			}
		}
		for (int curBulb = 0; curBulb < kthBulb; curBulb++) {
			curSelVal = min(switchCounter, switchedOnIx);
			increment(curSelVal, switchedOns, switchCounter, switchedOnIx);
		}
		long endTime = System.currentTimeMillis();
		if (printTimeTaken) {
			System.out.println("### Time Taken - " + (endTime - startTime) + "ms");
		}
		return curSelVal;
	}

}
