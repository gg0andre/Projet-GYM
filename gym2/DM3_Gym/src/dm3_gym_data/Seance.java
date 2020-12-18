package dm3_gym_data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Seance {	
	private Service parent;
	private String seanceID;	
	private LocalDate day;
	private ArrayList<Inscription> inscrits;
	private int capacity;
		
	public Seance( Service s, int index , LocalDate day) {
		parent = s;		
		inscrits = new ArrayList<Inscription>();
		this.day = day;
		capacity = s.getCapacity();
		
		//Generer code de seance
		String firstPart = s.getServiceID(); //Code du service
		String secondPart = index < 10 ? "0"+index : ""+index; //Numero de seance
		String lastPart = parent.getProNum().substring(Usager.ID_LENGTH-2); //Deux derniers chiffre du code de prof
		seanceID = firstPart+secondPart+lastPart;	
	}
	
	public boolean inscription(Membre m) {
		if(alreadySubscribed(m)) return false;
		if(capacity > 0) {
			inscrits.add(new Inscription(this,day,parent.getProNum(),seanceID,m.getUserID()));
			//decrement capacity
			capacity--;
			return true;
		}
		return false;
	}
	
	public boolean confirm(Membre m) {
		for(Inscription ins:inscrits) {
			if(ins.getNumMembre().equals(m.getUserID())) {
				//If already confirmed, return false
				if (ins.isConfirmed())
					return false;
				//Else, make confirmation
				else {
					ins.setConfirmed(true);
					return true;
				}
			}				
		}
		
		//Default return
		return false;
	}
	
	public boolean alreadySubscribed(Membre m) {
		for(Inscription ins:inscrits) {
			if(ins.getNumMembre().equals(m.getUserID()))
				return true;
		}
		//Default return value
		return false;
	}
	
	public ArrayList<Inscription> getInscriptions(){
		return inscrits;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public String getSeanceID() {
		return seanceID;
	}
	
	public LocalDate getDay() {
		return day;
	}
	
	public LocalDateTime getDayAndTime(){
		return day.atTime(parent.getServiceHour(),parent.getServiceMin());
	}
}
