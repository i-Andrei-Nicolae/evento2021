package day1;

import utils.FileUtils;

import java.util.Arrays;
import java.util.List;

public class SonarSweep {
    public static void prueba() {
        List<String> data = FileUtils.returnFileLines8("src/main/java/day1/input.txt");
        //List<String> data = Arrays.asList("199", "200", "208", "210", "200", "207", "240", "269", "260", "263");

        //Part1
        /*
        for (int i = 0, count = 0; i<data.size(); i++) {
            if (i == 0) System.out.println(data.get(i) + "(N/A - no previous measurement)");
            else {
                Integer n1 = Integer.parseInt(data.get(i-1));
                Integer n2 = Integer.parseInt(data.get(i));
                System.out.println(n1 < n2? n2 + "(increased)" + ++count: n2 + "(decreased)");
            }
        }
        */
        //Part2

        for (int i = 0, count = 0; i<data.size()-2; i++) {
            //conjunto 1
            Integer n1 = Integer.parseInt(data.get(i));
            Integer n2 = i+1 < data.size()? Integer.parseInt(data.get(i+1)):0;
            Integer n3 = i+2 < data.size()? Integer.parseInt(data.get(i+2)):0;

            if (i == 0) {
                System.out.println((n1 + n2 + n3) + "(N/A - no previous measurement)");
            }
            else {
                Integer n4 = i-1 < data.size()? Integer.parseInt(data.get(i-1)):0;
                Integer n5 = i < data.size()? Integer.parseInt(data.get(i)):0;
                Integer n6 = i+1 < data.size()? Integer.parseInt(data.get(i+1)):0;

                if ((n4 + n5 + n6) == (n1 + n2 + n3))
                    System.out.println((n1 + n2 + n3) + "(not change)");
                else
                    System.out.println((n4 + n5 + n6) < (n1 + n2 + n3)?
                        (n1 + n2 + n3) + "(increased)" + ++count:
                        (n1 + n2 + n3) + "(decreased)");
            }
        }
    }
}
