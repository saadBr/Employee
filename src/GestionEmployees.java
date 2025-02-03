import java.util.Arrays;
import java.util.Scanner;

public class GestionEmployees {

    private static final int MAX_EMPLOYEES = 5; // max employees 5 pour testing
    private final Employee[] employees; // declaration de tables des employees
    private int nbEmployees; // declaration de nombre des employees
    // constructeur par défault pour initialiser le tableau des employees avec max et nombre des employees à 0
    public GestionEmployees() {
        employees = new Employee[MAX_EMPLOYEES];
        nbEmployees = 0;
    }
    // fonction d'affichage de menu
    public void printMenu(){
        boolean exit = false;
        Scanner sc = new Scanner(System.in);
        while(!exit) {
            System.out.println("""
                    Menu:
                    1-Ajouter un employee
                    2-Modifier un employee
                    3-Supprimer un employee
                    4-Afficher les employees
                    5-Rechercher un Employee
                    6-Calculer masse salariale
                    7-Trier les employees
                    8-Quitter
                    Choisir une operation:""");
            int op = sc.nextInt();
            switch (op) {
                case 1:
                    if (nbEmployees >= MAX_EMPLOYEES) {
                        System.out.println("Le tableau d'employés est plein. Virez quelqu'un d'abord hehe");
                        break;
                    }
                    System.out.println("Entez id employee:");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Entez nom:");
                    String nom = sc.nextLine();
                    System.out.println("Entez poste:");
                    String poste = sc.nextLine();
                    System.out.println("Entez Salaire:");
                    double salaire = sc.nextDouble();
                    ajouterEmployee(new Employee(id, nom, poste, salaire));
                    break;
                case 2:
                    System.out.println("Entez id employee:");
                    id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Entez nouveau nom:");
                    String nouveauNom = sc.nextLine();
                    System.out.println("Entez nouveau poste:");
                    String nouveauPoste = sc.nextLine();
                    System.out.println("Entez nouveau Salaire:");
                    double nouveauSalaire = sc.nextDouble();
                    modifierEmployee(id, nouveauNom, nouveauPoste, nouveauSalaire);
                    break;
                case 3:
                    System.out.println("Entez id employee:");
                    id = sc.nextInt();
                    supprimerEmployee(id);
                    break;
                case 4:
                    afficherEmployees();
                    break;
                case 5:
                    System.out.println("Entez id employee:");
                    id = sc.nextInt();
                    rechercherEmployees(id);
                    break;
                case 6:
                    System.out.println("Masse salariale:"+calculerMasseSalariale());
                    break;
                case 7:
                    System.out.println("Entrez la facon de tri:\n" +
                            "1-Croissant\n" +
                            "2-Décroissant");
                    int tri = sc.nextInt();

                    if (tri == 1) {
                        trierEmployeesParSalaire(true);
                    } else if (tri == 2) {
                        trierEmployeesParSalaire(false);
                    }
                    break;
                case 8:
                    exit = true;
                    break;
                default:
                    System.out.println("Option invalide");
            }
        }
    }
    // fonction d'ajout des employees
    public void ajouterEmployee(Employee e) {
        for (int i = 0; i < nbEmployees; i++) {
            if (employees[i].getId() == e.getId()) {
                System.out.println("Un employé avec cet ID existe déjà !");
                return;
            }
        }
        employees[nbEmployees++] = e;
        System.out.println("Employé ajouté avec succès !");
    }
    // fonction de mise à jour des employees
    public void modifierEmployee(int id, String nouveauNom, String nouveauPoste, double nouveauSalaire) {
        for(int i = 0; i < nbEmployees; i++) {
            if(employees[i].getId() == id) {
                employees[i].setNom(nouveauNom);
                employees[i].setPoste(nouveauPoste);
                employees[i].setSalaire(nouveauSalaire);
                System.out.println("Employé mis à jour avec succès !");
                return;
            }
        }
        System.out.println("Employé inexistant");
    }
    // fonction de suppression des employees
    public void supprimerEmployee(int id) {
        for (int i = 0; i < nbEmployees; i++) {
            if (employees[i].getId() == id) {
                for (int j = i; j < nbEmployees - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[--nbEmployees] = null;
                System.out.println("Employé supprimé avec succès.");
                return;
            }
        }
        System.out.println("Employé inexistant");
    }
    // fonction d'affichage des employees
    public void afficherEmployees(){
        for(int i = 0; i < nbEmployees; i++) {
            System.out.println(employees[i].toString());
        }
    }
    // fonction de recherche d'un employee par ID
    public void rechercherEmployees(int id){
        for (int i = 0; i < nbEmployees; i++) {
            if(employees[i].getId() == id) {
                System.out.println(employees[i].toString());
                return;
            }
        }
        System.out.println("Aucun employé trouvé avec l'ID " + id);
    }
    // fonction de calcul de masse salariale
    public double calculerMasseSalariale(){
        if (nbEmployees == 0) {
            System.out.println("Aucun employé enregistré.");
            return 0.0;
        }
        double salaire = 0;
        for (int i = 0; i < nbEmployees; i++) {
            salaire += employees[i].getSalaire();
        }
        return salaire;
    }
    // fonction de triage par salaire
    public void trierEmployeesParSalaire(boolean ordreCroissant){
        Arrays.sort(employees,0,nbEmployees,(e1,e2)->
                ordreCroissant ? Employee.compareParSalaire(e1,e2) : Employee.compareParSalaire(e2,e1));

    }
    public static void main(String[] args) {
        GestionEmployees ges = new GestionEmployees();
        ges.printMenu();
    }
}

