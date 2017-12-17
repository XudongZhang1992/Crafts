/*
 * This is the class that abstract the calculation of trousers manufacture parameters.
 * 
 * Note that all the numerics are in Chinese "cun".
 * 
 * Created by Xudong Zhang and Ran Cui on 2017/12/13.
*/
import java.lang.IllegalArgumentException;

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

        // a sample is made with 100 rorations * 50 needles
        this.rhoRotation = 100 / sampleL;
        this.rhoNeedle = 50 / sampleW;

        this.machineType = machineT;
        this.footType = footT;
        this.addElastic = addE;
        this.isMale = isM;
        this.isBellyIn = isBI;
    }

    public double getLength() {
        return this.length;
    }
}