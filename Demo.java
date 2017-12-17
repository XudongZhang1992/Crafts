/*
 * A demo for the TrousersTech class. Just put these two files in the
 * same directory and run this file.
 * 
 * Created by Xudong Zhang and Ran Cui on 2017/12/13.
*/

public class Demo {
    public static void main(String[] args) {
        TrouserTech tt = new TrouserTech(3.1, 0.95, 4.2, 2.8, 5.0, 4.0, 2, 1, true, false, false);
        System.out.format("New TrousersTech object created.%nLength is: %.3f%n", tt.getLength());
    }
}