package gym;

public class Service {	
	private String timeStamp, startDate, endDate, openingHour, proNum, serviceCode, comments;
	private int capacity;
	private float serviceFees;
	private boolean[] recurrences;
	
	private Seance[] listeSeances;
	
	public Service( String timeStamp, String startDate, String endDate, String openingHour,
					Professionnel p, String serviceCode, String comments, int capacity, float serviceFees, boolean[] recurrences) {
		this.timeStamp = timeStamp;
		this.startDate = startDate;
		this.endDate = endDate;
		this.openingHour = openingHour;
		this.proNum = p.numeroProfessionnel;
		this.serviceCode = serviceCode;
		this.comments = comments;
		this.capacity = capacity;
		this.serviceFees = serviceFees;
		this.recurrences = recurrences;
		
		listeSeances = new Seance[7];
		
		//Initialize array
		for(int i = 0; i < 7; i++) {
			if( recurrences[i] ) {
				listeSeances[i] = new Seance(this,ConsoleUI.DAYS[i]);
			}
		}
		
		//Self-insert
		p.addService(this);
	}
	
	public void newInscription(Membre m, int selected) {
		listeSeances[selected].addMember(m);
	}

	
	public String getScheduleString() {
		String s = "";
		for(int i = 0; i < 7; i++) {
			if(recurrences[i]) {
				// Add a separator if it's not the first day
				if( s.length() > 0 )
					s += "-"+ConsoleUI.DAYS[i];
				else
					s += ConsoleUI.DAYS[i];
			}
		}
		
		return s;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public boolean setTimeStamp() {
		 ConsoleUI.printUserPrompt("Time stamp actuel: " + timeStamp + " Nouveau time stamp: ");        
	        String option =  ConsoleUI.getInput();
	        timeStamp = option;
	        return true;
	}

	public String getStartDate() {
		return startDate;
	}

	public boolean setStartDate() {
		 ConsoleUI.printUserPrompt("Date de debut actuelle: " + startDate + " Nouvelle date de debut (mm/jj/aaaa): ");        
	        String option =  ConsoleUI.getInput();
	        startDate = option;
	        return true;
	}

	public String getEndDate() {
		return endDate;
	}

	public boolean setEndDate() {
		 ConsoleUI.printUserPrompt("Date de fin actuelle: " + endDate + " Nouvelle date de fin (mm/jj/aaaa): ");        
	        String option =  ConsoleUI.getInput();
	        endDate = option;
	        return true;
	}

	public String getOpeningHour() {
		return openingHour;
	}

	public boolean setOpeningHour() {
		 ConsoleUI.printUserPrompt("Heure de service (HH:sss) actuelle: " + openingHour + "Nouvelle Heure de service (HH:sss): ");        
	        String option =  ConsoleUI.getInput();
	        openingHour = option;
	        return true;
	}

	public String getProNum() {
		return proNum;
	}

	public boolean setProNum() {
		 ConsoleUI.printUserPrompt("Numero de professionnel actuel: " + proNum + "Nouveau numero de professionnel ");        
	        String option =  ConsoleUI.getInput();
	        proNum = option;
	        return true;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public boolean setServiceCode() {
		 ConsoleUI.printUserPrompt("Code de service actuel: " + serviceCode + "Nouveau code de service ");        
	        String option =  ConsoleUI.getInput();
	        serviceCode = option;
	        return true;
	}

	public String getComments() {
		return comments;
	}

	public boolean setComments() {
		 ConsoleUI.printUserPrompt("Commentaires actuels: " + comments + "Nouveaux commentaires ");        
	        String option =  ConsoleUI.getInput();
	        comments = option;
	        return true;
	}

	public int getCapacity() {
		return capacity;
	}

	public boolean setCapacity() {
		 ConsoleUI.printUserPrompt("Capacite actuelle: " + capacity + "Nouvelle capacite (personnes)");        
	        String option =  ConsoleUI.getInput();
	        capacity = Integer.parseInt(option);
	        return true;
	}

	public float getServiceFees() {
		return serviceFees;
	}

	public boolean setServiceFees() {
		 ConsoleUI.printUserPrompt("Frais de service actuels: " + serviceFees + "Nouveaux frais de service ($)");        
	        String option =  ConsoleUI.getInput();
	        serviceFees = Float.parseFloat(option);
	        return true;
	}

	public boolean[] getRecurrences() {
		return recurrences;
	}

	public boolean setRecurrences() {
		 ConsoleUI.printUserPrompt("Jours de service actuels: " + recurrences + "Nouveaux jours de service, 7 jours, (O)ui ou (N)on, Format ONNNNOO: ");        
	        String option =  ConsoleUI.getInput();
	       // Parse recurrence/schedule string
	        recurrences = new boolean[7];    
	        for(int i = 0; i < 7; i++)
	        	recurrences[i] = option.charAt(i) == 'O' ? true : false;
	        return true;
	}

	public Seance[] getListeSeances() {
		return listeSeances;
	}

	public void setListeSeances(Seance[] listeSeances) {
		this.listeSeances = listeSeances;
	}

	public boolean hasSeance(int selected) {		
		return recurrences[selected];
	}
}
