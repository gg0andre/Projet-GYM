package dm3_gym_data;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;

public class Professionnel extends Usager {   
    public ArrayList<Service> servicesDonnes;
    
    public Professionnel(String prenom, String nom, String adresse, String ville, String codePostal, 
    		String provTerr, String numeroDeTel, String email, Instant dateDeNaissance) {
    	super(prenom, nom, adresse, ville, codePostal, provTerr, numeroDeTel, email,dateDeNaissance);      
        servicesDonnes = new ArrayList<Service>();
    }
    
    public void addService( Service s ) {
    	servicesDonnes.add(s);
    }
    
    public ArrayList<Service> getServices(){
    	return servicesDonnes;
    }
}
