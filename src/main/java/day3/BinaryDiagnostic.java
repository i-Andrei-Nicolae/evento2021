package day3;

import utils.FileUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BinaryDiagnostic {
    private static String gammaRate = "";
    private static String epsilonRate = "";
    private static String classGenOxy = "";
    private static String classDepCO2 = "";

    private static Integer countBit = 0;
    private static final Integer count = 12;
    private static Integer i = 0;

    public static void calcularGama() {
        for (i = 0; i<count-1; i++) {
            countBit = 0;
            FileUtils.returnFileLines8("src/main/java/day3/input.txt").forEach(e -> {
                String [] data = e.split("");
                if (data[i].equals("1")) countBit++;
                if (data[i].equals("0")) countBit--;
            });

            if (countBit > 0) gammaRate += "1";
            else gammaRate += "0";
        }

        Arrays.asList(gammaRate.split("")).forEach(e-> {
            if (e.equals("1")) epsilonRate+="0";
            if (e.equals("0")) epsilonRate+="1";
        });

        System.out.println(gammaRate + "*" + epsilonRate + "= calcular con la calculadora es mas facil");
    }

    public static void oxygen2(List<String> datos) {
        int count1Bit, count0Bit;

        for (i = 0; i<count-1; i++) {
            count1Bit = (int) datos.stream().filter(e -> e.matches(classGenOxy + ".*")).map(e -> e.split("")[i]).filter(e -> e.equals("1")).count();
            count0Bit = (int) datos.stream().filter(e -> e.matches(classGenOxy + ".*")).map(e -> e.split("")[i]).filter(e -> e.equals("0")).count();

            if (datos.stream().filter(e->e.matches(classGenOxy + ".*")).toArray().length > 1) {
                if (count1Bit > count0Bit) classGenOxy += "1";
                else classGenOxy += "0";
            }
        }

        for (i = 0; i<count-1; i++) {
            count1Bit = (int) datos.stream().filter(e -> e.matches(classDepCO2 + ".*")).map(e -> e.split("")[i]).filter(e -> e.equals("1")).count();
            count0Bit = (int) datos.stream().filter(e -> e.matches(classDepCO2 + ".*")).map(e -> e.split("")[i]).filter(e -> e.equals("0")).count();

            if (datos.stream().filter(e->e.matches(classDepCO2 + ".*")).toArray().length > 1) {
                if (count1Bit >= count0Bit) classDepCO2 += "0";
                else classDepCO2 += "1";
            }
        }

        System.out.println("Calcular con la calculadora es mas facil");
        System.out.println("Gen Oxy");
        datos.stream().filter(e-> e.matches(classGenOxy + ".*")).forEach(System.out::println);
        System.out.println("CO2");
        datos.stream().filter(e-> e.matches(classDepCO2 + ".*")).forEach(System.out::println);
    }

    public static void calcularOxygen() {
        //oxygen2(Arrays.asList("00100","11110", "10110", "10111", "10101", "01111", "00111", "11100", "10000", "11001", "00010", "01010"));
        oxygen2(FileUtils.returnFileLines8("src/main/java/day3/input.txt"));
    }
}
