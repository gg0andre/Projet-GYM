package gym;

public class CentreDeDonnees {

	public GestionDeComptes gdc;	
	private RepertoireDesServices rds;
	ProcedureComptable pc;
	
	public CentreDeDonnees() {
		// Initialize modules      
		gdc = new GestionDeComptes();
		rds = new RepertoireDesServices(this);
		pc = new ProcedureComptable();

	}  
	
	/*****************************************************************************************************************
	 *  MAIN METHODS 
	 ****************************************************************************************************************/
	public void start() {		
        // Print App header
        ConsoleUI.printAppHeader();       
        // Start App
        openMainMenu();
	}	
    
	/*****************************************************************************************************************
	 *  MENU METHODS 
	 ****************************************************************************************************************/
    private void openMainMenu() {
    	boolean reset = false; // Reset Switch for when we return BACK to this menu from another one
    	do {
	    	 // Print Main Menu
	    	ConsoleUI.printMenu("Menu Principal",
	    			             new String[]{
				            		 "Creer ou Modifier un compte",
				            		 "Verifier un membre",
				            		 "Payer frais",
				            		 "Renouveler Abonnement",
				            		 "Repertoire des Services",
				            		 "Procedure Comptable",
				            		 "Quitter"});       
	         
	        // Input loop
	        boolean repeat = false; // Repeat Switch for invalid inputs
	        do {
	        	// User input
	            ConsoleUI.printUserPrompt("Votre choix");           
	            String option =  ConsoleUI.getInput();  
	            repeat = false; // Reset every loop
	                    	
	            switch(option) {
		        	case "1": reset = gdc.openMemberMenu(); break;
		        	case "2": reset = gdc.verifierMembre(); break;
		        	case "3": reset = gdc.paiementDeFrais(); break; 
		        	case "4": reset = gdc.renouvelerAbonnement(); break;
		        	case "5": reset = rds.openServiceMenu(); break;		        	
		        	case "6": reset = pc.creerRapportDeSynthese(); break; // Add

		        	case "7": ConsoleUI.close(); return; // CLOSE APP
		        	default: ConsoleUI.printError("Input invalide"); repeat = true; break; //Error and loop around
	            }
	        } while (repeat);
    	} while(reset);        
    }
    
    private boolean subMenuTemplate() {
    	boolean reset = false; // Reset Switch for when we return BACK to this menu from another one
    	do {
	    	 // Print Menu
	    	ConsoleUI.printMenu("MENU TITLE",
	    			             new String[]{
				            		 "ITEM1",
				            		 "ITEM2",
				            		 "ITEM3",
				            		 "ITEM4",
				            		 "ITEM5"});       
	         
	        // Input loop
	        boolean repeat = false; // Repeat Switch repeats until user input is valid
	        do {
	        	// User input
	            ConsoleUI.printUserPrompt("Votre choix");           
	            String option =  ConsoleUI.getInput();  
	            repeat = false; // Reset every loop
	                    	
		        switch(option) {
		        	case "1": break; // Use : reset = openSubMenuXYZ(); break; Your submenu returns TRUE if this menu needs to reset (to print again, waiting for more input) and FALSE if the menu needs to end
		        	case "2": break; 
		        	case "3": break;
		        	case "4": break; // If your subMenu has a "Back/Return" option, simply return true to go back to the previous/calling menu		        	
		        	default: ConsoleUI.printError("Input invalide"); repeat = true; break; //Error and loop around
		        }
	        } while (repeat);
    	} while(reset);     
    	
    	//Return false by default
        return false;
    } 
  
}