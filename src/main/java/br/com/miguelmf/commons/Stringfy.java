package br.com.miguelmf.commons;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Stringfy is a utility class that contain methods capable of stringfying
 * a object instance, using reflecion;
 *
 * @author Miguel Fontes
 */
public class Stringfy {


    /**
     * Stringfy the object using a list of it's attributes enclosed on curly braces
     *
     *    Entity { attribute1: value, attribute2: value }
     *
     * @return a String representation of the Object
     */
    public static String curly(Object object) {
        return object.getClass().getSimpleName() + " { " + computeListOfFieldNames(object) + " }";
    }

    private static String computeListOfFieldNames(Object object) {
        return Arrays.stream(object.getClass().getDeclaredFields())
            .map(field -> field.getName() + ": " + getFieldValue(field, object))
            .collect(Collectors.joining(", "));
    }

	private static String getFieldValue(Field field, Object object) {
		try {
            field.setAccessible(true);
			return field.get(object).toString();
		} catch (IllegalArgumentException e) {
            e.printStackTrace();
            return "";
		} catch (IllegalAccessException e) {
            e.printStackTrace();
            return "";
		}
	}


}