package gym;

import java.io.File;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

public class FichierTEF implements Runnable {


    @Override
    public void run() {
        //System.out.println("Hi");
        //converitr date en String
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");
        String dateToString = formatter.format(date);


        String s = "FichierTEF" + dateToString + ".json";
        try {
            File file = new File(s);
            if (file.createNewFile()) {
                this.createFichier(s);
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            ConsoleUI.printError("An error occurred.");
            e.printStackTrace();
        }

    }

    private void createFichier(String s) {
        try {
            FileWriter fw = new FileWriter(s);
            
            
            //https://stackoverflow.com/questions/16982056/how-to-get-the-date-7-days-earlier-date-from-current-date-in-java
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime then = now.minusDays(7);
            
            //code pour tester la creation du fichierTEF
            JSONObject professionnelDetails = new JSONObject();
            professionnelDetails.put("Numero", 100000000);
            professionnelDetails.put("Nom", "Andre-Viet Tran");
            professionnelDetails.put("Montant", 100.00);
            
            JSONObject professionnelObject = new JSONObject();
            professionnelObject.put("professionnel", professionnelDetails);
            
            JSONObject professionnelDetails2 = new JSONObject();
            professionnelDetails2.put("Numero", 123456789);
            professionnelDetails2.put("Nom", "ABC def GHI");
            professionnelDetails2.put("Montant", 123.45);
            
            JSONObject professionnelObject2 = new JSONObject();
            professionnelObject2.put("professionnel", professionnelDetails2);
            
            JSONArray professionnelList = new JSONArray();
            professionnelList.put(professionnelObject);
            professionnelList.put(professionnelObject2);
            
            fw.write(professionnelList.toString(4));
            fw.flush();
			
            /**
            ArrayList<Service> arrlist = RepertoireDeService.listService;
            JSONArray professionnelList = new JSONArray();
            
            for(Service service : arrlist) {
            	for(Seance seance : arrlist.get(service).listSeance) {
            		if (seance.dateDeSeance < now && seance.dateDeSeance > then) {
            			JSONObject obj = new JSONObject();
            			obj.put("Numero", service.numeroProfessionnel);
            			obj.put("Nom", service.nomProfessionnel);
            			
            			int montant = seance.MembresInscrits.size() * service.fraisDuService;
            			obj.put("Montant", montant);
            			
            			JSONObject profesionnelObject = new JSONObject();
            			profesionnelObject.put("profesionnel", obj);
            			
            			professionnelList.put(profesionnelObject);
            		}
            	}
            }	
			*/
            //fw.write(professionnelList.toString(1));
            //fw.flush();
            
            fw.close();
            System.out.println("FichierTEF.json creer.");
        } catch (IOException e) {
            ConsoleUI.printError("An error occurred.");
            e.printStackTrace();
        }
    }
}
