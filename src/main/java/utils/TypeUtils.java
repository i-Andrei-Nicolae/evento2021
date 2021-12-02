package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TypeUtils {
	//private static Scanner sc = new Scanner(System.in);
	
	public static LocalDate formFecha(String fecha, String del) {
		try {
			return LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy" + del + "MM" + del + "dd"));
		} catch (DateTimeParseException e) {
			System.out.println("La fecha no es correcta");
		}
		return null;
	}
		
	public static LocalDate formFecha(String fecha) {
		return formFecha(fecha, "-");
	}
	
	public static Integer formInt(String entero) {
		try {
			return Integer.parseInt(entero);
		} catch(NumberFormatException e) {
			//System.out.println("Error el dato no es un entero");
		}
		return null;
	}
	
	public static float formFloat(String numero) {
		try {
			return Float.parseFloat(numero.replace(',', '.'));
		} catch(NumberFormatException e) {
			System.out.println("Error el dato no es un decimal");
		}
		return 0f;
	}
}
