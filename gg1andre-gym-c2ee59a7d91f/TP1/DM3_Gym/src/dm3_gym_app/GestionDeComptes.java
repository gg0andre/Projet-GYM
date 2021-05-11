package dm3_gym_app;
import java.time.Instant;
import java.util.ArrayList;

import dm3_gym_data.Membre;
import dm3_gym_data.Professionnel;
import dm3_gym_data.Usager;

public class GestionDeComptes {
	private ArrayList<Professionnel> listeProfessionnels; 
    private ArrayList<Membre> listeMembres;
    
    private final float DEFAULT_MONTHLY_FEE = 15.0f;
    
    // Constructor
    public GestionDeComptes() {
    	listeProfessionnels = new ArrayList<Professionnel>();
        listeMembres = new ArrayList<Membre>();
    }

	public Membre nouveauMembre(String prenom, String nom, String adresse, String ville, String codePostal, String provTerr, String numeroDeTel, String email,
    		Instant dateDeNaissance){	
         
		Membre nouveauMembre = new Membre(prenom, nom, adresse, ville, codePostal, provTerr, numeroDeTel, email,dateDeNaissance,DEFAULT_MONTHLY_FEE);		
		listeMembres.add(nouveauMembre);	
		return nouveauMembre;
    }

    public Professionnel nouveauProfessionnel(String prenom, String nom, String adresse, String ville, String codePostal, String provTerr, String numeroDeTel, String email,
    		Instant dateDeNaissance){    	
	     
    	Professionnel nouveauPro = new Professionnel(prenom, nom, adresse, ville, codePostal, provTerr, numeroDeTel, email,dateDeNaissance);		
		listeProfessionnels.add(nouveauPro);	
		return nouveauPro;
    }
        
    public void modifierCompteUsager(Usager current, String prenom, String nom, String adresse, String ville, String codePostal, String provTerr, String numeroDeTel, String email){
    	current.setPrenom(prenom);
    	current.setNom(nom);
    	current.setAdresse(adresse);
    	current.setVille(ville);
    	current.setCodePostal(codePostal);
    	current.setProvTerr(provTerr);
    	current.setNumeroDeTel(numeroDeTel);
    	current.setEmail(email);
    }
        
    public void supprimerCompteMembre(Membre m){
    	listeMembres.remove(m);
    }
 
    public Usager getUserByID( String id ) {
    	Usager found = getMemberByID(id);
    	if( found == null )
    		return getProfessionnalByID(id);
    	else
    		return found;    	
    }
    
    public Membre getMemberByID( String id ) {
    	for (Membre currentM: listeMembres) {
            if (currentM.getUserID().equals(id)) {                    
            	return currentM;
            };
	    };
	    
	    // Default return value
	    return null;
    }
    
    public Professionnel getProfessionnalByID( String id ) {    	
    	for (Professionnel currentP: listeProfessionnels) {
            if (currentP.getUserID().equals(id)) {                    
            	return currentP;
            };
	    };
	    
	    // Default return value
	    return null;
    }
    
    public Usager getUserByMail( String mail ) {
    	Usager found = getMemberByMail(mail);
    	if( found == null )
    		return getProfessionnalByMail(mail);
    	else
    		return found;    	
    }
    
    public Membre getMemberByMail( String mail ) {
    	for (Membre currentM: listeMembres) {
            if (currentM.getEmail().equals(mail)) {                    
            	return currentM;
            };
	    };
	    
	    // Default return value
	    return null;
    }
    
    public Professionnel getProfessionnalByMail( String mail ) {    	
    	for (Professionnel currentP: listeProfessionnels) {
            if (currentP.getEmail().equals(mail)) {                    
            	return currentP;
            };
	    };
	    
	    // Default return value
	    return null;
    }
    
    public void paiementDeFrais( Membre m ) {
    	//En attendant, simplement vider les frais
    	m.setFraisAPayer(0f);
    }
        
    public void renouvelerAbonnement( Membre m ) {
    	//En attendant, simplement rendre compte valide
    	m.setStatutMembre(true);
    }    
}
