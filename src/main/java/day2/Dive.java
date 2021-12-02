package day2;

import utils.FileUtils;

public class Dive {
    public static Integer x = 0;
    public static Integer y = 0;
    public static Integer aim = 0;

    public static void prueba() {
        x = 0;
        y = 0;
        FileUtils.returnFileLines8("src/main/java/day2/input.txt").forEach(e->{
            String[] datos = e.split(" ");
            if (datos[0].equals("forward")) {
                x +=Integer.parseInt(datos[1]);
                y += (Integer.parseInt(datos[1]) * aim);
            }
            if (datos[0].equals("down")) aim+=Integer.parseInt(datos[1]);
            if (datos[0].equals("up")) aim-= Integer.parseInt(datos[1]);
        });

        System.out.println("pos "+ (x*y));
    }
}
