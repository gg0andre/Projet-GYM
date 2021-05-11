package gym;
import java.util.*;
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
    public boolean setStatutMembre() {
        ConsoleUI.printUserPrompt("Statut Membre actuel: " + statutMembre + " Nouveau statut membre: ");        
        String option =  ConsoleUI.getInput();
        statutMembre = option;
        return true;
    }
    public boolean setFraisAdhesionMensuel() {
        ConsoleUI.printUserPrompt("Frais d'adhesion mensuel actuels " + fraisAdhesionMensuel + " Nouveaux frais d'adhesion mensuel: ");        
        String option =  ConsoleUI.getInput();
        fraisAdhesionMensuel = Float.parseFloat(option);
        return true;
    }
    public boolean setFraisAPayer() {
        ConsoleUI.printUserPrompt("Frais a payer actuels: " + fraisAPayer + " Nouveaux frais a payer: ");        
        String option =  ConsoleUI.getInput();
        fraisAPayer = Float.parseFloat(option);
        return true;
    }
}
