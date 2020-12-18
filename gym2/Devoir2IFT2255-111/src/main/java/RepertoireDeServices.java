import java.util.ArrayList;
import java.util.Scanner;

public class RepertoireDeServices {
    public ArrayList<Service> listService = new ArrayList<Service>();

    //constructeur
    public RepertoireDeServices() {

    }

    public void printChoices() {
        System.out.println("Please write the number of the desired option  to proceed: " +
                " 1 - Creer un service\n 2 - Modifier un service\n 3 - Supprimer un service" +
                "4 - Rapport synthese 5 - Repertoire de Services");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        if (option == 1) {
           creerService();
        }


        sc.close();
    }

    public void creerService() {
        Scanner sc = new Scanner(System.in);

    }
}
