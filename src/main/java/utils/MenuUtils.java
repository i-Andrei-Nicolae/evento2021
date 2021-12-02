package utils;

import java.util.List;
import java.util.Scanner;

public class MenuUtils {

	public static void printMenu(List<String> options) {
		for(int i=0; i<options.size();i++) {
			System.out.println((i+1) + ". " + options.get(i));
		}
		System.out.println("0. Exit");
	}
	
	public static void printMenu(String... opciones) {
		int iterador = 1;
		System.out.println();
		for(String option : opciones) {
			System.out.println(iterador++ + ". " + option);		
		}
		System.out.println("0. Salir");
	}
	
	@SuppressWarnings("resource")
	public static String returnOptionMenu(List<String> options) {
		printMenu(options);
		return new Scanner(System.in).nextLine();
	}
}
