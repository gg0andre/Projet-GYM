package gym;

import java.util.ArrayList;
import java.util.Random;

public class RepertoireDesServices {
	private ArrayList<Service> listeServices;
	private CentreDeDonnees parent;
	
	public RepertoireDesServices( CentreDeDonnees cdd ) {
		listeServices = new ArrayList<Service>();
		parent = cdd;
		
		//Testing service for prototyping
		
		 Service test = new Service("11/11/2011","11/11/2011","11/11/2011","04:20",parent.gdc.getProfessionnel(0),"1234567","No comments", 10, 4.20f, new boolean[] {true,false,false,true,false,false,false});
		 test.newInscription(parent.gdc.getMember(0), 0);
	     listeServices.add(test);	     
	}
	
	private boolean newService() {
		// Verify prof.
		ConsoleUI.printUserPrompt("Entrez le numero du professionnel");
    	String num = ConsoleUI.getInput();    	
    	
    	Professionnel p = parent.gdc.findProfessionnelByNumber(num);
        if( p == null )         	
        	return true;  
        
        // Get input and create service
		ConsoleUI.printUserPrompt("Date de debut (mm/jj/aaaa)");
        String option = ConsoleUI.getInput();
        String debut = option;
        
        ConsoleUI.printUserPrompt("Date de fin (mm/jj/aaaa)");
        option = ConsoleUI.getInput();
        String fin = option;
        
        ConsoleUI.printUserPrompt("Heure de service (HH:sss)");
        option = ConsoleUI.getInput();
        String time = option;
        
        ConsoleUI.printUserPrompt("Capacite (personnes)");
        option = ConsoleUI.getInput();
        String cap = option;
        
        ConsoleUI.printUserPrompt("Jours de service, 7 jours, (O)ui ou (N)on, Format ONNNNOO");
        option = ConsoleUI.getInput();
        String rec = option;
        
        ConsoleUI.printUserPrompt("Frais de service ($)");
        option = ConsoleUI.getInput();
        String frais = option;
        
        ConsoleUI.printUserPrompt("Commentaire");
        option = ConsoleUI.getInput();        
        String com = option;
        
        // Generate unique code
        String codeService = generateServiceCode();
        
        // Convert numeric types
        int capacity = Integer.parseInt(cap);
        float fees = Float.parseFloat(frais);
        
        // Parse recurrence/schedule string
        boolean[] schedule = new boolean[7];        
       
        for(int i = 0; i < 7; i++)
        	schedule[i] = rec.charAt(i) == 'O' ? true : false;
        
        Service newServ = new Service("11/11/2011",debut,fin,time,p,codeService,com, capacity,fees,schedule);
        listeServices.add(newServ);
		
        ConsoleUI.printFeedback("Service cree : "+codeService); // Feedback pour l'agent
		return true;
	}
	
	private boolean modifierService() {
		boolean reset = false;
    	boolean flag = false;
    	
    	ConsoleUI.printUserPrompt("Entrez le numero du service");
    	String num = ConsoleUI.getInput();    	
    	
    	
        for (Service currentS: listeServices) {
                if (currentS.getServiceCode().equals(num)) {
                	do {
            	    	ConsoleUI.printMenu(
            	    			"Champs à modifier",
            		             new String[]{
            		             "Time Stamp",
            		             "Date de debut (mm/jj/aaaa)",
            		             "Date de fin (mm/jj/aaaa)",
            		             "Heure de service (HH:sss)",
            		             "Professionnel",
            		             "Code de service",
            		             "Commentaires",
            		             "Capacité (personnes)",
            		             "Frais de service ($)",
            	    			 "Jours de service, 7 jours, (O)ui ou (N)on, Format ONNNNOO",
            		 	         "Retour"});      
            	        
            	    	 // Input loop
            	        boolean repeat = false;
            	        do {
            	        	// User input
            	            ConsoleUI.printUserPrompt("Votre choix");           
            	            String option =  ConsoleUI.getInput();           
            	                    	
            		        switch(option) {
            		        	case "1": reset = currentS.setTimeStamp(); break;
            		        	case "2": reset = currentS.setStartDate(); break;
            		        	case "3": reset = currentS.setEndDate(); break;	
            		        	case "4": reset = currentS.setOpeningHour(); break;	
            		        	case "5": reset = currentS.setProNum(); break;
            		        	case "6": reset = currentS.setServiceCode(); break;
            		        	case "7": reset = currentS.setComments(); break;
            		        	case "8": reset = currentS.setCapacity(); break;
            		        	case "9": reset = currentS.setServiceFees(); break;
            		        	case "10": reset = currentS.setRecurrences(); break;
            		        	case "11": return true; // Back to last menu
            		        	default: ConsoleUI.printError("Input invalide"); repeat = true; break; //Error and loop around
            		        }
            	        } while (repeat);
                	} while (reset);
                    
                };
        };
        if(!flag)
        	ConsoleUI.printError("Numero de service invalide");
        
    	
    	//Return false by default
        return false;
	}
	
	private boolean supprimerService(){
	    boolean flag = false;
    	ConsoleUI.printUserPrompt("Entrez le numero du service:");
        String num = ConsoleUI.getInput();
              for (Service currentS: listeServices) {
                   if (currentS.getServiceCode().equals(num)) {
                       ConsoleUI.printFeedback("Service " +num+ " supprime");
                       listeServices.remove(currentS);
                       flag = true;
                       break;
                   };
               };
               if(!flag)
               	ConsoleUI.printError("Numero de service invalide");
              return true;
}
	
	private boolean inscriptionSeance() {
		// Verify client
		ConsoleUI.printUserPrompt("Entrez le numero du client");
    	String num = ConsoleUI.getInput();    	
    	
    	Membre m = parent.gdc.findMemberByNumber(num);
        if( m == null )         	
        	return true;        
        
        // Display list
        displayServiceList();        
		
        // Verify service
        ConsoleUI.printUserPrompt("Choix du client");
        String pick = ConsoleUI.getInput();   
        int idx = Integer.parseInt(pick) - 1;
        Service s = listeServices.get(idx);
        		
        // Select seance
        ConsoleUI.printUserPrompt("Entrez la seance voulue (Ex : Lun)");
    	String day = ConsoleUI.getInput();    	
        int selected = ConsoleUI.dayToIndex(day);
        
        // Check if day input is valid
        if( selected == -1 ) {         	
        	ConsoleUI.printError("Jour invalide : "+day);
        	return true;        	
        }
        else
        {
        	//Check if the seance exists
        	if( s.hasSeance(selected) ) {
	        	ConsoleUI.printFeedback("Membre ("+num+") inscrit au service ("+s.getServiceCode()+") a la seance du ("+day+")");
	        	s.newInscription(m,selected);
	        	return true;
        	}
        	else {
        		ConsoleUI.printError("Le service ("+s.getServiceCode()+") n'a pas de seance le ("+day+")");
            	return true;  
        	}
        }		
	}
	
	private boolean consultationInscriptions() {
		// Verify prof.
		ConsoleUI.printUserPrompt("Entrez le numero du professionnel");
    	String num = ConsoleUI.getInput();    	
    	
    	Professionnel p = parent.gdc.findProfessionnelByNumber(num);
        if( p == null )         	
        	return true;  
        
        // Show professionnal's list of services
        String[] items = new String[p.getServices().size()];
		for(int i = 0; i < p.getServices().size(); i++) {
			Service current = p.getServices().get(i);
			items[i] = current.getServiceCode() + " | " + current.getOpeningHour() + " | " + current.getScheduleString();
		}
		
		ConsoleUI.printMenu("Liste des Services de ("+p.numeroProfessionnel+")", items);
		
        // Get input and select service
		ConsoleUI.printUserPrompt("Service voulu");
        String pick = ConsoleUI.getInput();   
        int idx = Integer.parseInt(pick) - 1;
        Service s = p.getServices().get(idx);
        
        // Select seance
        ConsoleUI.printUserPrompt("Entrez la seance voulue (Ex : Lun)");
    	String day = ConsoleUI.getInput();    	
        int selected = ConsoleUI.dayToIndex(day);
        
        // Check if day input is valid
        if( selected == -1 ) {         	
        	ConsoleUI.printError("Jour invalide : "+day);
        	return true;        	
        }
        else
        {
        	//Check if the seance exists
        	if( s.hasSeance(selected) ) {
	        	//Display list
        		displayAttendeeList(s.getListeSeances()[selected]);
        		return true;
        	}
        	else {
        		ConsoleUI.printError("Le service ("+s.getServiceCode()+") n'a pas de seance le ("+day+")");
            	return true;  
        	}
        }	
	}

	private boolean confirmerInscription() {
		// Verify client
		ConsoleUI.printUserPrompt("Entrez le numero du client");
    	String num = ConsoleUI.getInput();    	
    	
    	Membre m = parent.gdc.findMemberByNumber(num);
        if( m == null )         	
        	return true;       
        
        // Display list
        displayServiceList();        
		
        // Verify service
        ConsoleUI.printUserPrompt("Choix du client");
        String pick = ConsoleUI.getInput();   
        int idx = Integer.parseInt(pick) - 1;
        Service s = listeServices.get(idx);
        		
        // Select seance
        ConsoleUI.printUserPrompt("Entrez la seance voulue (Ex : Lun)");
    	String day = ConsoleUI.getInput();    	
        int selected = ConsoleUI.dayToIndex(day);
        
        // Check if day input is valid
        if( selected == -1 ) {         	
        	ConsoleUI.printError("Jour invalide : "+day);
        	return true;        	
        }
        else
        {
        	//Check if the seance exists
        	if( s.hasSeance(selected) ) {
	        	//Check if client is already signed up
        		Seance seance = s.getListeSeances()[selected];
        		if( seance.hasMember(m) ) {
        			// Confirm presence if client pays
        			if ( parent.gdc.paiementDeFrais(m) ) {
        				ConsoleUI.printFeedback("Le client ("+m.numeroMembre+") a confirme sa presence a la seance du ("+day+")");
        				return true;        				
        			}
        			else {
        				ConsoleUI.printError("Le paiement de ("+s.getServiceCode()+") n'a pas ete effectue");
                    	return true;  
        			}
        		}
        		else {
        			ConsoleUI.printError("Le client ("+m.numeroMembre+") n'est pas inscrit a la seance du ("+day+")");
                	return true;
        		}
        	}
        	else {
        		ConsoleUI.printError("Le service ("+s.getServiceCode()+") n'a pas de seance le ("+day+")");
            	return true;  
        	}
        }		
	}
	
	private void displayAttendeeList(Seance s) {
		String[] items = new String[s.getInscrits().size()];
		for(int i = 0; i < s.getInscrits().size(); i++) {
			Membre current = s.getInscrits().get(i);			
			items[i] = current.prenom + " " + current.nom + " | " + current.numeroDeTel;
		}
		
		ConsoleUI.printMenu("Membre inscrits a la seance du ("+s.getJour()+")", items);
	}
	
	private void displayServiceList() {
		String[] items = new String[listeServices.size()];
		for(int i = 0; i < listeServices.size(); i++) {
			Service current = listeServices.get(i);			
			items[i] = current.getServiceCode() + " | " + current.getOpeningHour() + " | " + current.getScheduleString();
		}
		
		ConsoleUI.printMenu("Liste des Services", items);
	}
	
	private Service findServiceByCode( String code ) {		
		for (Service currentS: listeServices) {
            if (currentS.getServiceCode().equals(code)) {
                    ConsoleUI.printFeedback("Service trouve");
                    return currentS;
            };
	    };
	    
	    // Default return value
	    ConsoleUI.printError("Code de service invalide");
	    return null;
	}
	
	public String generateServiceCode(){
        Random rand = new Random();
        int code = rand.nextInt(9000000) + 1000000; // 7 Digits
        String currentCode = Integer.toString(code);
        boolean valid = true;
        for (Service currentS: listeServices) {
                if (currentS.getServiceCode().equals(currentCode)) {
                       valid = false;
                       break;
                };
        };
        if(valid)
        	return currentCode;
        else
        	return generateServiceCode(); // Tail recursion
	}
	
	/*****************************************************************************************************************
	 *  MENU METHODS 
	 ****************************************************************************************************************/
    public boolean openServiceMenu() {
    	boolean reset = false;
    	do {
	    	 // Print Member Menu
	    	ConsoleUI.printMenu("Repertoire des services",
		             new String[]{
	           		 "Inscription a une seance",
	           		 "Confirmation de presence",
	           		 "Creer un service",
	           		 "Modifier un service",
	           		 "Supprimer un service",
	           		 "Consultation d'inscriptions",
	           		 "Retour"});      
	        
	    	 // Input loop
	        boolean repeat = false;
	        do {
	        	// User input
	            ConsoleUI.printUserPrompt("Votre choix");           
	            String option =  ConsoleUI.getInput();           
	                    	
		        switch(option) {
		        	case "1":  reset = inscriptionSeance(); break;
		        	case "2":  reset = confirmerInscription(); break;
		        	case "3":  reset = newService(); break;	
		        	case "4":  reset = modifierService(); break;	
		        	case "5":  reset = supprimerService(); break;	
		        	case "6":  reset = consultationInscriptions(); break;	
		        	case "7": return true; // Back to last menu
		        	default: ConsoleUI.printError("Input invalide"); repeat = true; break; //Error and loop around
		        }
	        } while (repeat);
    	} while (reset);
        
    	//Return false by default
        return false;
    }
}
