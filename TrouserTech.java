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

    /* This function is the general version of calculating craft parameters.
     * 
    */
    public static ArrayList<int[]> change_needle(int rotations, 
            double start_needle, double end_needle, int step_needle) {
        // arguments are all positive, and step_needle should be 1 or 2
        if (step_needle != 1 && step_needle != 2) {
            throw new IllegalArgumentException("Needle step can only be 1 or 2!");
        }

        // whether I'm + or - needles in this process
        boolean addingNeedle = (end_needle - start_needle) > 0;

        // the data structure to be returned
        ArrayList<int[]> res = new ArrayList<int[]>();

        // extract integer part
        int start_int = (int) start_needle;
        int end_int = (int) end_needle;

        // a make-up for the lose of precision
        // can only perform when step_needle == 1, and precision really lost
        if (step_needle == 1 && (start_int != start_needle || end_int != end_needle)) {
            if ((start_needle - start_int) - (end_needle - end_int) >= 0) {
                ++start_int;
            } else {
                ++end_int;
            }
        }
        
        // whether I can exactly divide needles by step_needle
        boolean noReminder = ((end_int - start_int) % step_needle) == 0;
        // when remider found (and == 1), increment the one with larger decimal part
        // so that can exactly divide
        if (!noReminder) {
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
        int rotation2 = rotation1 + 1;
        int numStage2 = numStage - numStage1 - 1;

        res.add(new int[]{rotation1, addingNeedle? step_needle: step_needle * (-1), numStage1});
        res.add(new int[]{rotation2, addingNeedle? step_needle: step_needle * (-1), numStage2});
        res.add(new int[]{rotation2, 0, 1}); // after this last stage, no + or - needles needed

        return res;
    }
}