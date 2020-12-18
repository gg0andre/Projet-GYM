import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class GestionDeComptes {
        public Membre newMember(ArrayList<Membre> listeMembres){
                System.out.println("First name: ");
                Scanner clavier = new Scanner(System.in);
                String option =  clavier.next();
                String prenom = option;
                System.out.println("Name: ");
                option = clavier.next();
                String nom = option;
                System.out.println("Date de naissance : ");
                option = clavier.next();
                String dateDeNaissance = option;
                System.out.println("Adresse: ");
                option = clavier.next();
                String adresse = option;
                System.out.println("Numero de tel: ");
                option = clavier.next();
                String numeroDeTel = option;
                System.out.println("Email: ");
                option = clavier.next();
                String email = option;
                clavier.close();
                float fraisAdhesionMensuel = 100;
                float fraisAPayer = fraisAdhesionMensuel;
                String statutMembre = "Membre suspendu";
                String numeroMembre = generateNumberMember(listeMembres);
                Membre nouveau = new Membre(prenom, nom, dateDeNaissance, adresse, numeroDeTel, email, fraisAdhesionMensuel, fraisAPayer, statutMembre, numeroMembre);
                return nouveau;
        }

        public Professionnel newProfessionnel(ArrayList<Professionnel> listeProfessionnels){
                System.out.println("First name: ");
                Scanner clavier = new Scanner(System.in);
                String option =  clavier.next();
                String prenom = option;
                System.out.println("Name: ");
                option = clavier.next();
                String nom = option;
                System.out.println("Date de naissance : ");
                option = clavier.next();
                String dateDeNaissance = option;
                System.out.println("Adresse: ");
                option = clavier.next();
                String adresse = option;
                System.out.println("Numero de tel: ");
                option = clavier.next();
                String numeroDeTel = option;
                System.out.println("Email: ");
                option = clavier.next();
                String email = option;
                clavier.close();
                String numeroProfessionnel = generateNumberProf(listeProfessionnels);
                Professionnel nouveau = new Professionnel(prenom, nom, dateDeNaissance, adresse, numeroDeTel, email, numeroProfessionnel);
                return nouveau;
        }

        public void verifierMembre(){

        }

        public String generateNumberMember(ArrayList<Membre> listeMembres){
                Random rand = new Random();
                int num = rand.nextInt(900000000) + 100000000;
                String currentNum = Integer.toString(num);
                for (Membre currentM: listeMembres) {
                        if (currentM.numeroMembre.equals(currentNum)) {
                                generateNumberMember(listeMembres);
                        };
                };
                return currentNum;
        }

        public String generateNumberProf(ArrayList<Professionnel> listeProfessionnels){
                Random rand = new Random();
                int num = rand.nextInt(900000000) + 100000000;
                String currentNum = Integer.toString(num);
                for (Professionnel currentP: listeProfessionnels) {
                        if (currentP.numeroProfessionnel.equals(currentNum)) {
                                generateNumberProf(listeProfessionnels);
                        };
                };
                return currentNum;
        }
}
