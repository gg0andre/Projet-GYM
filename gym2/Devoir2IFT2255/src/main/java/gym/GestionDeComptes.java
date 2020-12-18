package gym;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class GestionDeComptes {
    private ArrayList<Professionnel> listeProfessionnels;
    private ArrayList<Membre> listeMembres;

    // Constructor
    public GestionDeComptes() {
        listeProfessionnels = new ArrayList<Professionnel>();
        listeMembres = new ArrayList<Membre>();

        // Below are test members for prototyping purposes
        Membre a = new Membre("Meg", "Dandurand", "08/27/1999",
                "750 rue du Parc", "911", "megane.dandurand@umontreal.ca",
                100, 150, "Valide", "111111111");
        listeMembres.add(a);

        Professionnel p = new Professionnel("Kevin", "Mwa", "04/19/1997", "420 Blaze Street", "(514) 420-1337", "kevin@gym.tp", "222222222");
        listeProfessionnels.add(p);
    }

    public boolean newMember(){

        ConsoleUI.printUserPrompt("First Name");
        String option = ConsoleUI.getInput();
        String prenom = option;

        ConsoleUI.printUserPrompt("Name");
        option = ConsoleUI.getInput();
        String nom = option;

        ConsoleUI.printUserPrompt("Adresse");
        option = ConsoleUI.getInput();
        String adresse = option;

        ConsoleUI.printUserPrompt("Email");
        option = ConsoleUI.getInput();
        String email = option;

        float fraisAdhesionMensuel = 100; // Make constant at some point
        float fraisAPayer = fraisAdhesionMensuel;
        String statutMembre = "Membre suspendu";
        String numeroMembre = generateNumberMember();
        Membre nouveau = new Membre(prenom, nom, "mm/jj/aaaa", adresse, "xxxxxxxxxx", email, fraisAdhesionMensuel, fraisAPayer, statutMembre, numeroMembre);
        nouveau.setDateDeNaissance();
        nouveau.setNumeroDeTel();

        ConsoleUI.printFeedback("Compte membre cree : "+numeroMembre); // Feedback pour l'agent
        return true;
    }

    public boolean newProfessionnel(){
        ConsoleUI.printUserPrompt("First Name");
        String option = ConsoleUI.getInput();
        String prenom = option;

        ConsoleUI.printUserPrompt("Name");
        option = ConsoleUI.getInput();
        String nom = option;

        ConsoleUI.printUserPrompt("Adresse");
        option = ConsoleUI.getInput();
        String adresse = option;

        ConsoleUI.printUserPrompt("Email");
        option = ConsoleUI.getInput();
        String email = option;

        String numeroProfessionnel = generateNumberProf();
        Professionnel nouveau = new Professionnel(prenom, nom, "mm/jj/aaaa", adresse, "xxxxxxxxxx", email, numeroProfessionnel);
        nouveau.setDateDeNaissance();
        nouveau.setNumeroDeTel();

        ConsoleUI.printFeedback("Compte professionnel cree : "+numeroProfessionnel); // Feedback pour l'agent
        return true;
    }

    public boolean supprimerCompteMembre(){
        boolean flag = false;
        ConsoleUI.printUserPrompt("Please write the member's unique code: ");
        String num = ConsoleUI.getInput();
        for (Membre currentM: listeMembres) {
            if (currentM.numeroMembre.equals(num)) {
                ConsoleUI.printFeedback("Membre " +num+ " supprime");
                listeMembres.remove(currentM);
                flag = true;
                break;
            };
        };
        if(!flag)
            ConsoleUI.printError("Numero de membre invalide");
        return true;
    }

    public boolean modifierCompteMembre(){
        boolean reset = false;
        boolean flag = false;
        ConsoleUI.printUserPrompt("Entrez le numero du client");
        String num = ConsoleUI.getInput();


        for (Membre currentM: listeMembres) {
            if (currentM.numeroMembre.equals(num)) {
                do {
                    ConsoleUI.printMenu("Champs � modifier",
                            new String[]{
                                    "Pr�nom",
                                    "Nom",
                                    "Date de naissance",
                                    "Adresse",
                                    "Num�ro de t�l�phone",
                                    "Email",
                                    "Frais d'adh�sion mensuel",
                                    "Frais � payer",
                                    "Statut du membre",
                                    "Retour"});

                    // Input loop
                    boolean repeat = false;
                    do {
                        // User input
                        ConsoleUI.printUserPrompt("Votre choix");
                        String option =  ConsoleUI.getInput();

                        switch(option) {
                            case "1": reset = currentM.setPrenom(); break;
                            case "2": reset = currentM.setNom(); break;
                            case "3": reset = currentM.setDateDeNaissance(); break;
                            case "4": reset = currentM.setAdresse(); break;
                            case "5": reset = currentM.setNumeroDeTel(); break;
                            case "6": reset = currentM.setEmail(); break;
                            case "7": reset = currentM.setFraisAdhesionMensuel(); break;
                            case "8": reset = currentM.setFraisAPayer(); break;
                            case "9": reset = currentM.setStatutMembre(); break;
                            case "10": return true; // Back to last menu
                            default: ConsoleUI.printError("Input invalide"); repeat = true; break; //Error and loop around
                        }
                    } while (repeat);
                } while (reset);

            };
        };
        if(!flag)
            ConsoleUI.printError("Numero de membre invalide");


        //Return false by default
        return false;
    }
    public boolean verifierMembre(){
        boolean flag = false;
        ConsoleUI.printUserPrompt("Entrez le numero du client");
        String num = ConsoleUI.getInput();

        for (Membre currentM: listeMembres) {
            if (currentM.numeroMembre.equals(num)) {
                ConsoleUI.printFeedback(currentM.statutMembre);
                flag = true;
                break;
            };
        };
        if(!flag)
            ConsoleUI.printError("Numero de membre invalide");

        return true;
    }

    public String generateNumberMember(){
        Random rand = new Random();
        int num = rand.nextInt(900000000) + 100000000;
        String currentNum = Integer.toString(num);
        boolean valid = true;
        for (Membre currentM: listeMembres) {
            if (currentM.numeroMembre.equals(currentNum)) {
                valid = false;
                break;
            };
        };
        if(valid)
            return currentNum;
        else
            return generateNumberMember(); // Tail recursion
    }

    public String generateNumberProf(){
        Random rand = new Random();
        int num = rand.nextInt(900000000) + 100000000;
        String currentNum = Integer.toString(num);
        boolean valid = true;
        for (Professionnel currentP: listeProfessionnels) {
            if (currentP.numeroProfessionnel.equals(currentNum)) {
                valid = false;
                break;
            };
        };
        if(valid)
            return currentNum;
        else
            return generateNumberMember(); // Tail recursion
    }

    /*****************************************************************************************************************
     *  MENU METHODS
     ****************************************************************************************************************/
    public boolean openMemberMenu() {
        boolean reset = false;
        do {
            // Print Member Menu
            ConsoleUI.printMenu("Gestion des membres",
                    new String[]{
                            "Creer un compte membre",
                            "Creer un compte professionnel",
                            "Modifier un compte",
                            "Supprimer un compte",
                            "Retour"});

            // Input loop
            boolean repeat = false;
            do {
                // User input
                ConsoleUI.printUserPrompt("Votre choix");
                String option =  ConsoleUI.getInput();

                switch(option) {
                    case "1": reset = newMember(); break;
                    case "2": reset = newProfessionnel(); break;
                    case "3": reset = modifierCompteMembre(); break;
                    case "4": reset = supprimerCompteMembre(); break;
                    case "5": return true; // Back to last menu
                    default: ConsoleUI.printError("Input invalide"); repeat = true; break; //Error and loop around
                }
            } while (repeat);
        } while (reset);

        //Return false by default
        return false;
    }
}
