import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner scan;
    private final Controller controller;

    public Ui() {
        scan = new Scanner(System.in);
        controller = new Controller();
    }
    public void startProgram() {
        startMessage();
        startMenu();
    }
    public void startMessage() {
        System.out.println("Velkommen til Svømmeklubben Delfinen!");
    }
    public void startMenu() {
        int userInput = 0;
        boolean runAgain = true;

        while (runAgain) {
            System.out.println("Log ind som:");
            System.out.print("""
                1. Formand
                2. Træner
                3. Kasserer
                4. Elev
                5. Afslut program
                """);

            try {
                userInput = Integer.parseInt(scan.nextLine());
                if (userInput < 1 || userInput > 5) {
                    System.out.println("Ugyldigt input. Indtast et tal mellem 1-5");
                } else {
                    switch (userInput) {
                        case 1 -> {
                            System.out.println("Du er logget ind som formand.");
                            formandMenu();
                        }
                        case 2 -> {
                            System.out.println("Du er logget ind som træner");
                        }
                        case 3 -> {
                            System.out.println("Du er logget ind som kasserer");
                        }
                        case 4 -> {
                            System.out.println("Du er logget ind som elev");
                        }
                        case 5 -> {
                            System.out.println("Programmet er lukket.");
                            runAgain = false;
                            controller.gemTilCSV(controller.getMedlemmere());

                        }

                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Ugyldigt input. Indtast et tal mellem 1-5");
            }
        }
    }

    public void formandMenu () {
        boolean runAgain = true;
        int formandInput;

        while (runAgain) {
            System.out.print("""
                    1. Opret medlem
                    5. Tilbage til hovedmenu
                    """);
            try {
                formandInput = Integer.parseInt(scan.nextLine());
                if (formandInput < 1 || formandInput > 5) {
                    System.out.println("Ugyldigt input. Indtast et tal mellem 1-5");
                } else {
                    switch (formandInput) {
                        case 1 -> {
                            createMedlem();
                        }
                        case 5 -> {
                            runAgain = false;
                        }
                    }
                }
            } catch (NumberFormatException e ) {
                System.out.println("Ugyldigt input. Indtast et tal mellem 1-5");
            }

        }
    }
    public void createMedlem() {
        System.out.print("Indtast navn på Medlem: ");
        String medlemName = scan.nextLine();
        System.out.print("Indtast alder på Medlem: ");
        int medlemAge = scan.nextInt();
        System.out.print("Indtast medlemsID: ");
        int medlemID = scan.nextInt();


        System.out.print("Konkurrence svømmer? [y/n]: ");
        String konkurrenceSvømmerYesNo = scan.next();
        boolean konkurrenceSvømmer;
        if (konkurrenceSvømmerYesNo.equalsIgnoreCase("y")) {
            konkurrenceSvømmer = true;
        } else konkurrenceSvømmer = false;
        scan.nextLine(); // scanner bug

        System.out.print("Aktivt medlem? [y/n]: ");
        String aktivYesNo = scan.nextLine();
        boolean aktiv;
        if(aktivYesNo.equalsIgnoreCase("y")){
            aktiv = true;
        }else aktiv=false;


        controller.addMedlem(medlemName, medlemAge, medlemID,
                konkurrenceSvømmer, aktiv);

        System.out.println(medlemName + " er blevet tilføjet til databasen.");

    }

    }

