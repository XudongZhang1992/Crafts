/*
 * A demo for the TrousersTech class. Just put these two files in the
 * same directory and run this file.
 * 
 * Created by Xudong Zhang and Ran Cui on 2017/12/13.
*/
import java.util.ArrayList;
import java.util.Arrays;

public class Demo {
    public static void main(String[] args) {
        // a realistic example of TrouserTech object
        TrouserTech tt = new TrouserTech(3.1, 0.95, 4.2, 2.8, 5.0, 4.0, 2, 1, true, false, false);
        
        // test the static function change_needle
        ArrayList<int[]> temp = TrouserTech.change_needle(37, 11.3, 2.5, 1);
        for (int[] elem: temp) {
            System.out.println(Arrays.toString(elem));
        }
    }
}