package gym;
import java.util.ArrayList;
import java.util.Random;

public class GestionDeComptes {
	private ArrayList<Professionnel> listeProfessionnels; 
    private ArrayList<Membre> listeMembres;
    
    // Constructor
    public GestionDeComptes() {
    	listeProfessionnels = new ArrayList<Professionnel>();
        listeMembres = new ArrayList<Membre>();
        
      // Below are test members for prototyping purposes
        Membre a = new Membre("Meg", "Dandurand", "08/27/1999",
                "750 rue du Parc", "9119119111", "megane.dandurand@umontreal.ca",
                100, 150, "Suspendu", "111111111");
        listeMembres.add(a);    
        
        Membre c = new Membre("Meg", "Dandurand", "08/27/1999",
                "750 rue du Parc", "9119119111", "megane.dandurand@umontreal.ca",
                100, 150, "Valide", "333333333");
        listeMembres.add(c);   
        
        Professionnel p = new Professionnel("Kevin", "Mwa", "04/19/1997", "420 Blaze Street", "(514) 420-1337", "kevin@gym.tp", "222222222");
        listeProfessionnels.add(p);
    }

	public boolean newMember(){	
            
            ConsoleUI.printUserPrompt("First Name");
            String option = ConsoleUI.getInput();
            String prenom = option;
            
            ConsoleUI.printUserPrompt("Name");
            option = ConsoleUI.getInput();
            String nom = option;
            
            ConsoleUI.printUserPrompt("Adresse");
            option = ConsoleUI.getInput();
            String adresse = option;
            
            ConsoleUI.printUserPrompt("Email");
            option = ConsoleUI.getInput();
            String email = option;
                   
            float fraisAdhesionMensuel = 100; // Make constant at some point
            float fraisAPayer = fraisAdhesionMensuel;
            String statutMembre = "Suspendu";
            String numeroMembre = generateNumberMember();
            Membre nouveau = new Membre(prenom, nom, "mm/jj/aaaa", adresse, "xxxxxxxxxx", email, fraisAdhesionMensuel, fraisAPayer, statutMembre, numeroMembre);
            nouveau.setDateDeNaissance();
            nouveau.setNumeroDeTel();
            
            listeMembres.add(nouveau);
            ConsoleUI.printFeedback("Compte membre cree : "+numeroMembre); // Feedback pour l'agent
            return true;
    }

    public boolean newProfessionnel(){    	
	     ConsoleUI.printUserPrompt("First Name");
	     String option = ConsoleUI.getInput();
	     String prenom = option;
	     
	     ConsoleUI.printUserPrompt("Name");
	     option = ConsoleUI.getInput();
	     String nom = option;
	     
	     ConsoleUI.printUserPrompt("Adresse");
	     option = ConsoleUI.getInput();
	     String adresse = option;
	     
	     ConsoleUI.printUserPrompt("Email");
	     option = ConsoleUI.getInput();
	     String email = option;
                 
	    String numeroProfessionnel = generateNumberProf();
	    Professionnel nouveau = new Professionnel(prenom, nom, "mm/jj/aaaa", adresse, "xxxxxxxxxx", email, numeroProfessionnel);
	    nouveau.setDateDeNaissance();
	    nouveau.setNumeroDeTel();
	    
	    listeProfessionnels.add(nouveau);
	    ConsoleUI.printFeedback("Compte professionnel cree : "+numeroProfessionnel); // Feedback pour l'agent
	    return true;
    }
    
    public boolean supprimerCompteMembre(){
    	    boolean flag = false;
        	ConsoleUI.printUserPrompt("Please write the member's unique code: ");
            String num = ConsoleUI.getInput();
                  for (Membre currentM: listeMembres) {
                       if (currentM.numeroMembre.equals(num)) {
                           ConsoleUI.printFeedback("Membre " +num+ " supprime");
                           listeMembres.remove(currentM);
                           flag = true;
                           break;
                       };
                   };
                   if(!flag)
                   	ConsoleUI.printError("Numero de membre invalide");
                  return true;
    }
    
    public boolean modifierCompteMembre(){
    	boolean reset = false;
    	boolean flag = false;
    	ConsoleUI.printUserPrompt("Entrez le numero du client");
    	String num = ConsoleUI.getInput();    	
    	
    	
        for (Membre currentM: listeMembres) {
                if (currentM.numeroMembre.equals(num)) {
                	do {
            	    	ConsoleUI.printMenu("Champs à modifier",
            		             new String[]{
            	           		 "Prénom",
            	           		 "Nom",
            	           		 "Date de naissance",
            	           		 "Adresse",
            	           		 "Numéro de téléphone",
            	           		 "Email",
            	           		 "Frais d'adhésion mensuel",
            	           		 "Frais à payer",
            	           		 "Statut du membre",
            	           		 "Retour"});      
            	        
            	    	 // Input loop
            	        boolean repeat = false;
            	        do {
            	        	// User input
            	            ConsoleUI.printUserPrompt("Votre choix");           
            	            String option =  ConsoleUI.getInput();           
            	                    	
            		        switch(option) {
            		        	case "1": reset = currentM.setPrenom(); break;
            		        	case "2": reset = currentM.setNom(); break;
            		        	case "3": reset = currentM.setDateDeNaissance(); break;	
            		        	case "4": reset = currentM.setAdresse(); break;	
            		        	case "5": reset = currentM.setNumeroDeTel(); break;
            		        	case "6": reset = currentM.setEmail(); break;
            		        	case "7": reset = currentM.setFraisAdhesionMensuel(); break;
            		        	case "8": reset = currentM.setFraisAPayer(); break;
            		        	case "9": reset = currentM.setStatutMembre(); break;
            		        	case "10": return true; // Back to last menu
            		        	default: ConsoleUI.printError("Input invalide"); repeat = true; break; //Error and loop around
            		        }
            	        } while (repeat);
                	} while (reset);
                    
                };
        };
        if(!flag)
        	ConsoleUI.printError("Numero de membre invalide");
        
    	
    	//Return false by default
        return false;
    }
    
    public boolean verifierMembre(){ 
    	ConsoleUI.printUserPrompt("Entrez le numero du client");
    	String num = ConsoleUI.getInput();    	
    	
        verifierMembre(num);
        
        return true;            
    }
    
    public boolean verifierMembre( String num ) {
    	for (Membre currentM: listeMembres) {
            if (currentM.numeroMembre.equals(num)) {
                    ConsoleUI.printFeedback(currentM.statutMembre);
                    return true;
            };
	    };
	    
	    // Default return value
	    ConsoleUI.printError("Numero de membre invalide");
	    return false;
    }

    public Membre findMemberByNumber( String num ) {
    	for (Membre currentM: listeMembres) {
            if (currentM.numeroMembre.equals(num)) {
                    ConsoleUI.printFeedback(currentM.statutMembre);
                    return currentM;
            };
	    };
	    
	    // Default return value
	    ConsoleUI.printError("Numero de membre invalide");
	    return null;
    }
    
    public Professionnel findProfessionnelByNumber( String num ) {
    	for (Professionnel currentP: listeProfessionnels) {
            if (currentP.numeroProfessionnel.equals(num)) {
                    ConsoleUI.printFeedback("Valide");
                    return currentP;
            };
	    };
	    
	    // Default return value
	    ConsoleUI.printError("Numero de professionnel invalide");
	    return null;
    }
    
    public String generateNumberMember(){
            Random rand = new Random();
            int num = rand.nextInt(900000000) + 100000000;
            String currentNum = Integer.toString(num);
            boolean valid = true;
            for (Membre currentM: listeMembres) {
                    if (currentM.numeroMembre.equals(currentNum)) {
                           valid = false;
                           break;
                    };
            };
            if(valid)
            	return currentNum;
            else
            	return generateNumberMember(); // Tail recursion
    }

    public String generateNumberProf(){
            Random rand = new Random();
            int num = rand.nextInt(900000000) + 100000000;
            String currentNum = Integer.toString(num);
            boolean valid = true;
            for (Professionnel currentP: listeProfessionnels) {
                if (currentP.numeroProfessionnel.equals(currentNum)) {
                	  valid = false;
                      break;
                };
            };
            if(valid)
            	return currentNum;
            else
            	return generateNumberMember(); // Tail recursion
    }
    
    public boolean paiementDeFrais( Membre m ) {
    	if(m.fraisAPayer != 0) {
			 ConsoleUI.printFeedback("Montant a payer: " + m.fraisAPayer);
			 ConsoleUI.printUserPrompt("Paiement effectue avec succes?: veuillez ecrire oui ou non");
		     String paiement = ConsoleUI.getInput();
		     if(paiement.equals("oui")) {
		    	 ConsoleUI.printFeedback("Paiement effectue avec succes");
		    	 m.fraisAPayer = 0;
		    	 m.statutMembre = "Valide";	 
		    	 return true;
		     }
		     else {
		    	 ConsoleUI.printError("Paiement cancelled");
		    	 return false;
		     }
		}
		else {
           ConsoleUI.printError("Aucun paiement du");
           return true;
		}
    }
    
    public boolean paiementDeFrais() {
    	boolean flag = false;
    	ConsoleUI.printFeedback("Passe au paiement");
    	ConsoleUI.printUserPrompt("Entrez le numero du client");
    	String num = ConsoleUI.getInput();    	
    	
        for (Membre currentM: listeMembres) {
                if (currentM.numeroMembre.equals(num)) {
                		if(currentM.fraisAPayer != 0) {
                			 ConsoleUI.printFeedback("Montant a payer: " + currentM.fraisAPayer);
                			 ConsoleUI.printUserPrompt("Paiement effectue avec succes?: veuillez ecrire oui ou non");
                		     String paiement = ConsoleUI.getInput();
                		     if(paiement.equals("oui")) {
                		    	 currentM.fraisAPayer = 0;
                		    	 currentM.statutMembre = "Valide";
                		    	 ConsoleUI.printFeedback("Paiement effectue avec succes");
                		    	 flag = true;
                                 break;
                		     }
                		     else {
                		    	 ConsoleUI.printError("Paiement cancelled");
                		    	 return true;
                		     }
                		}
                		else {
	                        ConsoleUI.printError("Aucun paiement du");
	                        return true;
                		}
                };
        };
        if(!flag)
        	ConsoleUI.printError("Numero de membre invalide");
        
        return true;    
    }
    
    public boolean renouvelerAbonnement() {
    	boolean flag = false;
    	ConsoleUI.printUserPrompt("Entrez le numero du client");
    	String num = ConsoleUI.getInput();    	
    	
        for (Membre currentM: listeMembres) {
                if (currentM.numeroMembre.equals(num)) {
                	if (currentM.statutMembre.equals("Suspendu")) {
                		paiementDeFrais();
                		if(currentM.statutMembre.equals("Valide")) {
                			ConsoleUI.printFeedback("Renouvellement fait avec succes");
                			 flag = true;
                             break;
                		}
                		else {
                			ConsoleUI.printError("Renouvellement non effectue");
                		       return true;   
                		}
                	}
                	ConsoleUI.printError(currentM.statutMembre + " pas de frais de renouvellement à payer.");
                    return true;   
                };
        };
        if(!flag)
        	ConsoleUI.printError("Numero de membre invalide");
        
        return true;    
    }
    
    /*****************************************************************************************************************
	 *  MENU METHODS 
	 ****************************************************************************************************************/
    public boolean openMemberMenu() {
    	boolean reset = false;
    	do {
	    	 // Print Member Menu
	    	ConsoleUI.printMenu("Gestion des membres",
		             new String[]{
	           		 "Creer un compte membre",
	           		 "Creer un compte professionnel",
	           		 "Modifier un compte",
	           		 "Supprimer un compte",
	           		 "Retour"});      
	        
	    	 // Input loop
	        boolean repeat = false;
	        do {
	        	// User input
	            ConsoleUI.printUserPrompt("Votre choix");           
	            String option =  ConsoleUI.getInput();           
	                    	
		        switch(option) {
		        	case "1": reset = newMember(); break;
		        	case "2": reset = newProfessionnel(); break;
		        	case "3": reset = modifierCompteMembre(); break;	
		        	case "4": reset = supprimerCompteMembre(); break;	
		        	case "5": return true; // Back to last menu
		        	default: ConsoleUI.printError("Input invalide"); repeat = true; break; //Error and loop around
		        }
	        } while (repeat);
    	} while (reset);
        
    	//Return false by default
        return false;
    }
    
    public Membre getMember(int index) {
    	return listeMembres.get(index);
    }
    
    public Professionnel getProfessionnel(int index) {
    	return listeProfessionnels.get(index);
    }
}
