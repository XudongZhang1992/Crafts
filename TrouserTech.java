/*
 * This is the class that abstract the calculation of trousers manufacture parameters.
 * 
 * Created by Xudong Zhang and Ran Cui on 2017/12/13.
*/

public class TrouserTech {

    // these input sizes are in Chinese "chi", except for foot
    private double length; 
    private double crotch;
    private double foot; // this is in "cun"
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
        this.length = length_;
        this.crotch = crotch_;
        this.foot = foot_;
        this.ass = ass_;
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