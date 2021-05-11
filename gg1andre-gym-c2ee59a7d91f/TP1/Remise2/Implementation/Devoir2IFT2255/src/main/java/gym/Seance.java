package gym;

import java.util.ArrayList;

public class Seance {
	private Service parent;
	private ArrayList<Membre> inscrits;
	private String jour;
	
	public Seance( Service s, String jour ) {
		parent = s;
		this.jour = jour;
		inscrits = new ArrayList<Membre>();
	}

	public void addMember(Membre m) {
		inscrits.add(m);		
	}
	
	public ArrayList<Membre> getInscrits(){
		return inscrits;
	}
	
	public String getJour() {
		return jour;
	}
	
	public boolean hasMember( Membre m ) {
		for(Membre membre : inscrits) {
			if ( membre.numeroMembre.equals(m.numeroMembre) )
				return true;
		}
		
		//Default
		return false;
	}
}
