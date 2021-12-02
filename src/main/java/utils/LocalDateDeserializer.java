package utils;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 * Esta clase incluye el adaptador para poder deserrializar un objeto con una 
 * variable localDate.
 * @author Andrei Nicolae Depner
 */
public class LocalDateDeserializer implements JsonDeserializer <LocalDate>{
	@Override
	public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		return LocalDate.parse(json.getAsString().split("T")[0],
	            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
}
