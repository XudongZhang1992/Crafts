/*
 * This is the class that abstract the calculation of trousers manufacture parameters.
 * 
 * Note that all the numerics are in Chinese "cun".
 * 
 * Created by Xudong Zhang and Ran Cui on 2017/12/13.
*/
import java.lang.IllegalArgumentException;
import java.util.ArrayList;

public class TrouserTech {
    // some fixed length in "cun"
    public static final double WAIST_LENGTH = 1.0;
    
    // num of needles to add when making crotch, depending on gender
    public static final int MALE_ADD_NEEDLE = 4;
    public static final int FEMALE_ADD_NEEDLE = 6;

    // all in Chinese "cun" for calculation simplicity
    // vertical
    private double length; 
    private double crotch;
    // horizontal
    private double foot;
    private double ass;

    private double rhoRotation; // how many rotations == 1 "cun"?
    private double rhoNeedle; // how many needles == 1 "cun"?

    private int machineType; // 1: "six" 2: "seven" 3: "nine" 4: "twelve"
    private int footType; // 1: "huo kou" 2: "1*1" 3: "2*1"    

    private boolean addElastic;
    private boolean isMale;
    private boolean isBellyIn;

    public TrouserTech(double length_, double crotch_, double foot_, double ass_,
                       double sampleL, double sampleW, int machineT, int footT, 
                       boolean addE, boolean isM, boolean isBI) {
        if (machineT < 1 || machineT > 4 || footT < 1 || footT > 3) {
            throw new IllegalArgumentException("No corresponding type code!");
        }

        // length, crotch and ass are in "chi", hence * 10
        this.length = length_ * 10;
        this.crotch = crotch_ * 10;
        this.foot = foot_;
        this.ass = ass_ * 10;

        // a sample is made with 100 rotations * 50 needles
        this.rhoRotation = 100 / sampleL;
        this.rhoNeedle = 50 / sampleW;

        this.machineType = machineT;
        this.footType = footT;
        this.addElastic = addE;
        this.isMale = isM;
        this.isBellyIn = isBI;
    }

    /* This function is the general version of calculating craft parameters, when varying
     * needles number involved. It helps calculating how needles are added or subtracted.
     * 
     * Input is (totalRotations, numNeedlesStart, numNeedlesEnd, +/-step). All are positive.
     * And step can only be 1 or 2 due to craft reason.
     * 
     * Output is (rotations, numAddingNeedles, stages). For example: input (37, 2.5, 11.3, 1).
     * [4, -1, 8]    
     * [5, -1, 1]
     * 
     * [4, -1, 8] reads as "4 rotations with current needles, THEN subtract 1 needle. The
     * process repeats 8 times." 
     * 
     * Note that after the last stage ([5, -1, 1] in this case), no +/- needles needed as
     * it's been the end of the process, even if it says -1 needle.
     * 
     * 
     * (int, double, double, int) -> ArrayList<int[]>
    */
    public static ArrayList<int[]> change_needle(int rotations, 
            double start_needle, double end_needle, int step_needle) {

        // step_needle should be 1 or 2
        if (step_needle != 1 && step_needle != 2) {
            throw new IllegalArgumentException("Needle step can only be 1 or 2!");
        }
        if (rotations <= 0 || start_needle < 0 || end_needle < 0) {
            throw new IllegalArgumentException("Parameters should be positive!");
        }

        // whether + or - needles in this process
        boolean addingNeedle = (end_needle - start_needle) > 0;

        // the data structure to be returned
        ArrayList<int[]> res = new ArrayList<int[]>();

        // extract integer part
        int start_int = (int) start_needle;
        int end_int = (int) end_needle;

        // a make-up for the lost of precision when converting needles to int
        // only happen when step_needle == 1, and precision really lost
        // cannot perform on step_needle == 2 as that needs to handle the exact division issue 
        if (step_needle == 1 && (start_int != start_needle || end_int != end_needle)) {
            if ((start_needle - start_int) - (end_needle - end_int) > 0) {
                ++start_int;
            } else if ((start_needle - start_int) - (end_needle - end_int) < 0) {
                ++end_int;
            } else {
                // when the decimal parts are equal
                if (addingNeedle) ++end_int;
                else ++start_int;
            }
        }
        
        // whether I can exactly divide needles by step_needle
        boolean noRemainder = ((end_int - start_int) % step_needle) == 0;
        // when remider found (and == 1), increment the one with larger decimal part
        // so that we can exactly divide
        if (!noRemainder) {
            if ((start_needle - start_int) - (end_needle - end_int) >= 0) {
                ++start_int;
            } else {
                ++end_int;
            }
        } // can definitely exactly divide them now

        // num of stages is ((times of + or - needles) + 1)
        int numStage = Math.abs(end_int - start_int) / step_needle + 1;
        int rotation1 = (int) (rotations / numStage);
        int numStage1 = numStage - (int) (rotations % numStage);

        // meaningless when (rotations % numStage) == 0
        int rotation2 = rotation1 + 1;
        int numStage2 = numStage - numStage1;

        res.add(new int[]{rotation1, addingNeedle? step_needle: step_needle * (-1), numStage1});
        // if the remainder == 0, no need to perform rotation2
        if ((rotations % numStage) != 0) {
            res.add(new int[]{rotation2, addingNeedle? step_needle: step_needle * (-1), numStage2});
        }

        return res;
    }
}