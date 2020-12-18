package dm3_gym_app;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

import dm3_gym_data.Inscription;
import dm3_gym_data.Membre;
import dm3_gym_data.Professionnel;
import dm3_gym_data.Seance;
import dm3_gym_data.Service;

public class RepertoireDesServices {
	private ArrayList<Service> listeServices;
	
	public RepertoireDesServices() {
		listeServices = new ArrayList<Service>();		
	}
	
	public Service newService( String nom, LocalDate startDate, LocalDate endDate, int hour, int min,Professionnel p, String comments, int capacity, float serviceFees, boolean[] recurrences) {
		Service nouveauService = new Service( nom, startDate, endDate, hour, min, p, comments, capacity, serviceFees, recurrences);		
		listeServices.add(nouveauService);
		return nouveauService;
	}
	
	public void modifierService(Service s, String nom, int hour, int min,  String comments, int capacity) {
		s.setNom(nom);	
		s.setServiceHour(hour);
		s.setServiceMin(min);
		s.setComments(comments);
		s.setCapacity(capacity);		
	}
	
	public void supprimerService( Service s ){
	   listeServices.remove(s);
	}
	
	public ArrayList<String[]> listeDesSeancesDispos(){
		ArrayList<String[]> liste = new ArrayList<String[]>();
		
		//Check from now
		LocalDateTime currentTime = LocalDateTime.now();
		LocalDate currentDay = LocalDate.now();
			
		//Loop until we go beyond the currentDate
		for(int i = 0; i < listeServices.size(); i++) {
			Service srv = listeServices.get(i);
		
			for(int j = 0; j < srv.getListeSeances().size(); j++) {
				Seance snc = srv.getListeSeances().get(j);
						
				// Same day
				if(currentDay.isEqual(snc.getDay())) {
					
					// Check time and capacity
					LocalDateTime seanceTime = snc.getDayAndTime();
					if(seanceTime.isAfter(currentTime) && snc.getCapacity() > 0 ) {					
						
						//Add to list
						String[] row = new String[4];
						row[0] = snc.getSeanceID();
						row[1] = srv.getNom();
						row[2] = srv.getComments();
						row[3] = j+"@"+i; //Hook to get this exact seance quickly
						liste.add(row);
					}
					
					
				}
				//Break loop if we pass the current day
				else if( snc.getDay().isAfter(currentDay)){
					break;
				}				
			}
		}
		
		
		
		return liste;
	}
	
	public ArrayList<String[]> listeDesInscriptions(Professionnel p){
		ArrayList<String[]> liste = new ArrayList<String[]>();
		
		for(Service srv: p.getServices() ) {
			for(Seance snc: srv.getListeSeances()) {
				for(Inscription ins:snc.getInscriptions()) {
					//Add to list
					String[] row = new String[3];
					row[0] = snc.getSeanceID();
					row[1] = ins.getNumMembre();
					String conf = ins.isConfirmed() ? " [Confirmée]" : " [Non-Confirmée]" ;
					row[2] = snc.getDayAndTime().toString() + conf;					
					liste.add(row);
				}
			}
		}		
		
		return liste;
	}

	public boolean inscriptionSeance(Membre m, int seanceIndex, int serviceIndex) {
		Seance s = listeServices.get(serviceIndex).getListeSeances().get(seanceIndex);
		return s.inscription(m);
	}

	public boolean confirmerPresence( Membre m , Seance s) {
		return s.confirm(m);
	}
				
	public Service findServiceByID( Professionnel pro, String id ) {		
		//Return if service belongs to user
		Service s = findServiceByID(id);
		if(s == null) return null;
		if(s.getProNum().equals(pro.getUserID())) return s;
		
		//Default return
		return null;
	}
	
	private Service findServiceByID( String id ) {		
		for(Service s: listeServices) {
			// If service exists 
			if( s.getServiceID().equals(id) )
				return s;
		}
		
		//Default return
		return null;
	}
	
	public Seance findSeanceByID( String id) {
		if(id == null || id.length() != 7) return null;
		// Le Service correspond au 3 premier chiffres
		String serviceID = id.substring(0, 4);
		Service s = findServiceByID(serviceID);		
		if(s == null) return null;
		
		//Trouve seance
		for(Seance snc: s.getListeSeances()) {
			if(s.getServiceID().equals(id))
				return snc;
		}
		
		//Default return
		return null;
	}
}
