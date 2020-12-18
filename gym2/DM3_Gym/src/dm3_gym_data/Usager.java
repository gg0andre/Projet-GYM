package dm3_gym_data;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public abstract class Usager {	
    private String prenom, nom, adresse, ville, codePostal, provTerr, numeroDeTel, email, userID; 
    private Instant dateDeNaissance;
       
    private static ArrayList<String> ID_LIST = new ArrayList<String>();
    public static final int ID_LENGTH = 9, NAME_LENGTH = 25, ADRESSE_LENGTH = 25, VILLE_LENGTH = 14, CODE_POSTAL_LENGTH = 6, NUMTEL_LENGTH = 14;
   
    public Usager(String prenom, String nom, String adresse, String ville, String codePostal, String provTerr, String numeroDeTel, String email,Instant dateDeNaissance) {
        this.prenom = prenom;
        this.nom = nom;
        this.dateDeNaissance = dateDeNaissance;
        this.adresse = adresse;
        this.ville = ville; 
        this.codePostal = codePostal;
        this.provTerr = provTerr;
        this.numeroDeTel = numeroDeTel;
        this.email = email;
        generateUserID();
    }

    private void generateUserID() {
    	Random rand = new Random();
        int num = rand.nextInt(999999999);
        String currentID = Integer.toString(num);
        //Add padding if needed
        if(currentID.length() != ID_LENGTH) {
        	String padding = "";
        	for(int i = 0; i < ID_LENGTH - currentID.length(); i++) {
        		padding += "0";
        	}
        	currentID = padding + currentID;        	
        }
        
        boolean valid = true;
        for (String ID : ID_LIST) {
                if (ID.equals(currentID)) {
                       valid = false;
                       break;
                };
        };
        if(valid) {
        	userID = currentID;
        	ID_LIST.add(userID);
        }
        else
        	generateUserID(); // Tail recursion
    }
    
    public String getFullname() {
    	return nom +","+prenom;
    }
    
	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Instant getDateDeNaissance() {
		return dateDeNaissance;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getProvTerr() {
		return provTerr;
	}

	public void setProvTerr(String provTerr) {
		this.provTerr = provTerr;
	}

	public String getNumeroDeTel() {
		return numeroDeTel;
	}

	public void setNumeroDeTel(String numeroDeTel) {
		this.numeroDeTel = numeroDeTel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}  
	
	public String getUserID() {
		return userID;
	}
}
