package gym;
public class Professionnel extends Client {
    public String numeroProfessionnel;
    public Professionnel(String prenom, String nom, String dateDeNaissance, String adresse, String numeroDeTel,
                         String email, String numeroProfessionnel) {//constructeur
        super(prenom, nom, dateDeNaissance, adresse, numeroDeTel, email);
        this.numeroProfessionnel = numeroProfessionnel;
    }
}
