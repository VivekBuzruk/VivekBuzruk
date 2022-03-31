package coin.sarvatech.glowingBulbs;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import coin.sarvatech.glowingBulbs.utils.GlowingBulbs_IterativeFinder;

/**
 * Unit test for simple App.
 */
public class GlowingBulbK_Finder4Test 
{
	static final String switchesString1 = "0110000000000000000000000000000000000000";
	static final int KthBulb1 = 5;
	static final int expectedOut1 = 8;
	static final String switchesString2 = "0010000000000000000000000000000000000000";
	static final int KthBulb2 = 5;
	static final int expectedOut2 = 15;
	static final String switchesString3 = "0100000000100000001000100000101000001000";
	static final int KthBulb3 = 16807;
	static final int expectedOut3 = 26866;

    /**
     * Rigorous Test :-)
     */
    @Test
    public void test1()
    {
    	int kthGlowingBulbIx;
    	
    	kthGlowingBulbIx = GlowingBulbs_IterativeFinder.getKthBulbVal(switchesString1.length(), 
    			switchesString1, KthBulb1, true);
        assertTrue( kthGlowingBulbIx ==  expectedOut1);
    }
    @Test
    public void test2()
    {
    	int kthGlowingBulbIx;
    	
    	kthGlowingBulbIx = GlowingBulbs_IterativeFinder.getKthBulbVal(switchesString2.length(), 
    			switchesString2, KthBulb2, true);
        assertTrue( kthGlowingBulbIx ==  expectedOut2);
    }
    @Test
    public void test3()
    {
    	int kthGlowingBulbIx;
    	
    	kthGlowingBulbIx = GlowingBulbs_IterativeFinder.getKthBulbVal(switchesString3.length(), 
    			switchesString3, KthBulb3, true);
        assertTrue( kthGlowingBulbIx ==  expectedOut3);
    }
}
