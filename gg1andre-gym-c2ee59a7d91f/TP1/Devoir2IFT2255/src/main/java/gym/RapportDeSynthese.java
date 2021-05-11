package gym;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;
import org.json.JSONArray;


public class RapportDeSynthese {

    public RapportDeSynthese () {
    }

    public void createFichier() {
        ConsoleUI.printUserPrompt("Path du fichier : ");
        String option = ConsoleUI.getInput();
        String path = option;
        
        //File file = new File("RapportDeSynthese.json);
        
        try {
        	String contents = new String((Files.readAllBytes(Paths.get(path))));
        	JSONArray o = new JSONArray(contents);
        	
        	for (int i=0; i < o.length(); i++) {
        		System.out.println(o.get(i));	//imprimer les informations du fichierTEF a l'ecran
        	}
        	
        	
        	
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
    }

}
