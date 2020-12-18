package dm3_gym_data;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;
public class Membre extends Usager {	
    private String statutMembre;
    private float fraisAdhesionMensuel, fraisAPayer;
    
    public static final String VALID = "Membre Valide", INVALID = "Membre Suspendu";
    
    public Membre(String prenom, String nom, String adresse, String ville, String codePostal, String provTerr, String numeroDeTel, String email,
    		Instant dateDeNaissance,float fraisAdhesionMensuel) {
         
    	 super(prenom, nom, adresse, ville, codePostal, provTerr, numeroDeTel, email,dateDeNaissance);	                     
	     this.fraisAdhesionMensuel = fraisAdhesionMensuel;
	    
	     //En assumant que les frais sont payés lors de l'inscription, membre valide par défaut et solde commence a zero
	     fraisAPayer = 0;
	     setStatutMembre(true);
    }
    
    public String getStatutMembre() {
    	return statutMembre;
    }
    
    public void setStatutMembre(boolean valid) {
    	statutMembre = valid ? VALID : INVALID;    		
    }    
    
    public float getFraisAdhesionMensuel() {
		return fraisAdhesionMensuel;
	}

	public float getFraisAPayer() {
		return fraisAPayer;
	}

	public void setFraisAdhesionMensuel( Float fees ) {      
        fraisAdhesionMensuel = fees;        
    }
    
    public void setFraisAPayer( Float fees) {        
        fraisAPayer = fees;
    }
}
