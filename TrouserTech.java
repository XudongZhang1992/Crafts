/*
 * This is the class that abstract the calculation of trousers manufacture parameters.
 * 
 * Note that all the numerics are in Chinese "cun".
 * 
 * Created by Xudong Zhang and Ran Cui on 2017/12/13.
*/

public class TrouserTech {
    // some fixed length in "cun"
    public static final double WAIST_LENGTH = 1.0;
    public static final double CHA_LENGTH = 1.5;

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

    // TODO: comment the mapping of int and words
    private int machineType; // 1:    2:     
    private int footType; // 1:    2:    3:    

    private boolean addElastic;
    private boolean isMale;
    private boolean isBellyIn;

    public TrouserTech(double length_, double crotch_, double foot_, double ass_,
                       double rhoR, double rhoN, int machineT, int footT, 
                       boolean addE, boolean isM, boolean isBI) {
        // length, crotch and ass are in "chi", hence * 10
        this.length = length_ * 10;
        this.crotch = crotch_ * 10;
        this.foot = foot_;
        this.ass = ass_ * 10;
        this.rhoRotation = rhoR;
        this.rhoNeedle = rhoN;
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