public class Membre extends Client {
    public String statutMembre, numeroMembre;
    public float fraisAdhesionMensuel, fraisAPayer;
    public Membre(String prenom, String nom, String dateDeNaissance, String adresse, String numeroDeTel, String email,
                  float fraisAdhesionMensuel, float fraisAPayer, String statutMembre, String numeroMembre) {//constructeur
                         super(prenom, nom, dateDeNaissance, adresse, numeroDeTel, email);
                         this.statutMembre = statutMembre;
                         this.numeroMembre = numeroMembre;
                         this.fraisAdhesionMensuel = fraisAdhesionMensuel;
                         this.fraisAPayer = fraisAPayer;
    }

}
