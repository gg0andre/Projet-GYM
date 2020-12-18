package dm3_gym_data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class Service {	
	private LocalDateTime creationTime;
	private LocalDate startDate, endDate; 
	private String nom, proNum, comments, serviceID;	
	private int capacity, serviceHour, serviceMin;	
	private float serviceFees;
	private boolean[] recurrences;	
	private ArrayList<Seance> listeSeances;
	
	public static final int MAX_CAPACITY = 30, ID_LENGTH = 3, NAME_LENGTH = 25, COMMENT_LENGTH = 100;
	public static final float  MIN_FEE = 5, MAX_FEE = 100;
	
	private static ArrayList<String> ID_LIST = new ArrayList<String>();
	
	public Service(String nom, LocalDate startDate, LocalDate endDate, int hour, int min ,Professionnel p, String comments, int capacity, float serviceFees, boolean[] recurrences) {	
		//Time
		this.nom = nom;
		creationTime = LocalDateTime.now();
		this.startDate = startDate;
		this.endDate   = endDate;		
		this.serviceHour = hour;
		this.serviceMin = min;		
		this.proNum = p.getUserID();		
		this.comments = comments;
		this.capacity = capacity;
		this.serviceFees = serviceFees;
		this.recurrences = recurrences;
		listeSeances = new ArrayList<Seance>();				
		generateServiceID();
		//Self-insert
		p.addService(this);
		
		//Instancier les seances
		LocalDate currentTime = startDate;		
		int counter = 0;
		while(currentTime.isBefore(endDate) || currentTime.isEqual(endDate)) {			
			int currentDay = currentTime.getDayOfWeek().getValue();
			System.out.println("Day : "+currentDay);
			//Check if seance is given that day
			if(recurrences[currentDay-1]) {
				
				//Add seance given that day
				counter++;
				if(counter <= 99) {
					Seance currentSeance = new Seance(this,counter,currentTime);
					listeSeances.add(currentSeance);
				}
				else {
					break; //Le nombre de seance a 2 chiffres, donc on limite à 99
				}
			}
			
			//Increment timer
			currentTime = currentTime.plusDays(1);
		}	
		
		System.out.println("Num Seances : "+listeSeances.size());
	}
		
	private void generateServiceID() {
	  	Random rand = new Random();
        int num = rand.nextInt(999);
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
        	serviceID = currentID;
        	ID_LIST.add(serviceID);
        }
        else
        	generateServiceID(); // Tail recursion
	}

	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public Seance getSeanceByID(String id) {
		for (Seance currentS: listeSeances) {
            if (currentS.getSeanceID().equals(id)) {                    
            	return currentS;
            };
	    };
	    
	    // Default return value
	    return null;
	}
	
	public ArrayList<Seance> getListeSeances(){
		return listeSeances;
	}
	
	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public String getProNum() {
		return proNum;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}


	public LocalDate getEndDate() {
		return endDate;
	}


	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	public String getServiceID() {
		return serviceID;
	}


	public int getServiceHour() {
		return serviceHour;
	}


	public void setServiceHour(int serviceHour) {
		this.serviceHour = serviceHour;
	}


	public int getServiceMin() {
		return serviceMin;
	}


	public void setServiceMin(int serviceMin) {
		this.serviceMin = serviceMin;
	}


	public float getServiceFees() {
		return serviceFees;
	}


	public void setServiceFees(float serviceFees) {
		this.serviceFees = serviceFees;
	}


	public boolean[] getRecurrences() {
		return recurrences;
	}


	public void setRecurrences(boolean[] recurrences) {
		this.recurrences = recurrences;
	}


	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public int getCapacity() {
		return capacity;
	}
}
