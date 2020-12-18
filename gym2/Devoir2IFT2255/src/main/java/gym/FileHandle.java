package gym;

import java.io.InputStream;

//https://youtu.be/4rBeFDnw_oo
public class FileHandle {

	//fonction pour chercher un Fichier
	public static InputStream inputStreamFromFile(String path) {
		try {
			InputStream inputStream = FileHandle.class.getResourceAsStream(path);
			return inputStream;
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
}
