/*
 * A demo for the TrousersTech class. Just put these two files in the
 * same directory and run this file.
 * 
 * Created by Xudong Zhang and Ran Cui on 2017/12/13.
*/

public class Demo {
    public static void main(String[] args) {
        TrouserTech tt = new TrouserTech(10, 9, 8, 7, 1.3, 2.2, 1, 1, false, false, false);
        System.out.format("New TrousersTech object created.%nLength is: %.3f%n", tt.getLength());
    }
}