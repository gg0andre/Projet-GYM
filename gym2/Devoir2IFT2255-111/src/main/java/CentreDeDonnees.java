import java.util.ArrayList;
import java.util.Scanner;

public class CentreDeDonnees {
    public ArrayList<Professionnel> listeProfessionnels = new ArrayList<Professionnel>();
    public ArrayList<Membre> listeMembres = new ArrayList<Membre>();
    public static void main(String[] args) {
        CentreDeDonnees c = new CentreDeDonnees();
        GestionDeComptes g = new GestionDeComptes();

        System.out.println("Please write the number of the desired option  to proceed: " +
                " 1 - Update or Create an account 2 - Verify member 3 - Procedure Comptable" +
                "4 - Rapport synthese 5 - Repertoire de Services");
        Scanner clavier = new Scanner(System.in);
        String option =  clavier.next();
        if(option.equals("1")) { //update create account
            System.out.println("Please write the number of the desired option to proceed: " +
                    "1- Create a new account 2 - Edit an account 3 - Delete an account");
            option = clavier.next();
            if(option.equals("1")){
                System.out.println("Please write '1' for a Gym member account and '2' for a Professional account");
                option =  clavier.next();
                if(option.equals("1")){
                    c.listeMembres.add(g.newMember(c.listeMembres));
                    for(Membre m : c.listeMembres) {
                        System.out.println(m.numeroMembre);
                        System.out.println("Prenom: " + m.prenom + ", Nom: " + m.nom);
                    };
                }
                else if(option.equals("2")){
                    c.listeProfessionnels.add(g.newProfessionnel(c.listeProfessionnels));
                    for(Professionnel p : c.listeProfessionnels) {
                        System.out.println(p.numeroProfessionnel);
                        System.out.println("Prenom: " + p.prenom + ", Nom: " + p.nom);
                    };
                }
                else {
                    System.err.println("Probleme avec le input. Veuillez ecrire un numero valide");
                }
                clavier.close();
            }
            else if(option.equals("2")){

            }
            else if(option.equals("3")){

            }
            else {
                System.err.println("Probleme avec le input. Veuillez ecrire un numero valide");
            }
            clavier.close();
        }
        else if(option.equals("2")) {//si verify member
            // verifierMembre();
            clavier.close();
        }
        else {
            System.err.println("Probleme avec le input. Veuillez ecrire un numero valide");
        }
    }

}