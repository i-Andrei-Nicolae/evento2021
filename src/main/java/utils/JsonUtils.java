package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class JsonUtils {
	public static int i = 1;
	public static <T> List<T> ObtenerDatosFicheroInternet(String datosWeb, Class<T> clase, String nombreArray) {
		List<T> lista = new ArrayList<>();
		try {
			JsonParser.parseString(readUrl(datosWeb)).getAsJsonObject().getAsJsonArray(nombreArray)
					.forEach(tl -> lista.add(devolverObjetoGson(tl.toString(), clase)));
		} catch (Exception e) {
			System.out.println("Hola");
			e.printStackTrace();
		}
		return lista;
	}
	
	public static <T, U> U devolverObjetoGsonConArrayGenerico(String obj, Class<T> claseArray, Class<U> clase) {
		Gson g = new Gson().newBuilder().
				registerTypeAdapter(LocalDate.class, new LocalDateDeserializer()).create();
		
		return g.fromJson(obj, TypeToken.getParameterized(clase, claseArray).getType());
	}

	public static <T, U> T devolverObjetoGson(String obj, Class<T> clase) {
		Gson g = new Gson().newBuilder().registerTypeAdapter(LocalDate.class, new LocalDateDeserializer()).create();
		
		return g.fromJson(obj, clase);
	}
	
	public static <T> List<T> devolverArrayObjeto(List<T> obj) {
		return new Gson().fromJson(new Gson().toJson(obj), new TypeToken<ArrayList<T>>(){}.getType());
	}
	
	public static String readUrl(String web) {
		try {
			URLConnection uc = new URL(web).openConnection();
			uc.setRequestProperty("User-Agent", "PostmanRuntime/7.20.1");
			uc.connect();
			
			return new BufferedReader(new InputStreamReader(uc.getInputStream(), StandardCharsets.UTF_8)).lines()
					.collect(Collectors.joining());
		} catch (Exception e) {
			System.out.println("No se ha podido la leer la URL: " + web);
		}
		return null;
	}

	public static String readUrl(String web, String token) {
		try {
			URL url = new URL(web);
			URLConnection uc = url.openConnection();
			uc.setRequestProperty("User-Agent", "PostmanRuntime/7.20.1");
			uc.setRequestProperty("X-Auth-Token", token);
			uc.connect();

			return new BufferedReader(new InputStreamReader(uc.getInputStream(), StandardCharsets.UTF_8)).lines()
					.collect(Collectors.joining());
		} catch (Exception e) {
			System.out.println("No se ha podido la leer la URL: " + web);
		}
		return null;
	}
}
