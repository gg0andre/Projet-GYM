package gym;

import java.io.InputStream;
import java.util.Scanner;

import org.json.JSONObject;

//https://youtu.be/4rBeFDnw_oo

public class JSONUtils {
	//convertir un fichier en un String
	public static String getJSONStringFromFile (String path) {
		try {
			Scanner sc ;
			InputStream in = FileHandle.inputStreamFromFile(path);
			sc = new Scanner(in);
			String json = sc.useDelimiter("\\z").next();
			sc.close();
			in.close();
			return json;
		} catch (Exception e) {
			System.out.println("Error occuried.");
			e.printStackTrace();
			return null;
		}	
	}
	
	//prendre un json file et convertir en json object
	public static JSONObject getJSONObjectFromFile(String path) {
		return new JSONObject(getJSONStringFromFile(path));
	}
	
	//fonction pour verfier si l'objet existe
	public static boolean objectExists(JSONObject jsonObjects, String key) {
		Object o;
		try {
			o = jsonObjects.get(key);
			
		} catch (Exception e) {
			return false;
		}
		return o != null;
	}
}
