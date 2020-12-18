public abstract class Client {
    public String prenom, nom, dateDeNaissance, adresse, numeroDeTel, email; //attributs

    public Client(String prenom, String nom, String dateDeNaissance, String adresse, String numeroDeTel, String email) { //constructeur
        this.prenom = prenom;
        this.nom = nom;
        this.dateDeNaissance = dateDeNaissance;
        this.adresse = adresse;
        this.numeroDeTel = numeroDeTel;
        this.email = email;

    }
}
