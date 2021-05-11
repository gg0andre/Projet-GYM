package gym;

import java.util.ArrayList;

public class Professionnel extends Client {
    public String numeroProfessionnel;
    public ArrayList<Service> services;
    public Professionnel(String prenom, String nom, String dateDeNaissance, String adresse, String numeroDeTel,
                         String email, String numeroProfessionnel) {//constructeur
        super(prenom, nom, dateDeNaissance, adresse, numeroDeTel, email);
        this.numeroProfessionnel = numeroProfessionnel;
        services = new ArrayList<Service>();
    }
    
    public void addService( Service s ) {
    	services.add(s);
    }
    
    public ArrayList<Service> getServices(){
    	return services;
    }
}
