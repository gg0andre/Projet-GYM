package gym;
import java.util.Scanner;

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
    public boolean setPrenom() {
        ConsoleUI.printUserPrompt("Prenom actuel: " + prenom + " Nouveau prenom: ");
        String option =  ConsoleUI.getInput();
        prenom = option;
        return true;
    }
    public boolean setNom() {
        ConsoleUI.printUserPrompt("Nom actuel: " + nom + " Nouveau nom: ");
        String option =  ConsoleUI.getInput();
        nom = option;
        return true;
    }
    public boolean setDateDeNaissance() {
        ConsoleUI.printUserPrompt("Date de naissance (format: mm/jj/aaaa) actuelle: " + dateDeNaissance + " Nouvelle date: ");
        String option =  ConsoleUI.getInput();
        if(option.length() != 10){
            ConsoleUI.printError("Please enter a valid date of length 10.");
        }
        else {
            dateDeNaissance = option;
            return true;
        }

        return setDateDeNaissance();
    }
    public boolean setAdresse() {
        ConsoleUI.printUserPrompt("Adresse actuelle: " + adresse + " Nouvelle adresse: ");
        String option =  ConsoleUI.getInput();
        adresse = option;
        return true;
    }
    public boolean setNumeroDeTel(){
        ConsoleUI.printUserPrompt("Numero de tel (format: xxxxxxxxxx) actuel " + numeroDeTel + " Nouveau numero: " );
        String option = ConsoleUI.getInput();
        if(option.length() != 10){
            ConsoleUI.printError("Please enter a valid number of length 10.");
        }
        else {
            numeroDeTel = option;
            return true;
        }

        return setNumeroDeTel();
    }
    public boolean setEmail() {
        ConsoleUI.printUserPrompt("Email actuel: " + email + " Nouveau email: ");
        String option =  ConsoleUI.getInput();
        email = option;
        return true;
    }
}
