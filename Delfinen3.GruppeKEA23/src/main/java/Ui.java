import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Ui {
    private final Scanner scan;
    private final Controller controller;


    public Ui() {
        scan = new Scanner(System.in);
        controller = new Controller();
    }

    public void startProgram() {
        //controller.loadMedlemmereFraCSV(); Hvis vi loader fra start så viser den 2x under 'vis oversigt' da den også loader.
        // Måske kan man hive Objekterne ud af listen og bare vise den under vis oversigt, og ikke loade direkte?
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
                            controller.gemTilCSV(controller.newlyMadeMedlemmere);

                        }

                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Ugyldigt input. Indtast et tal mellem 1-5");
            }
        }
    }

    public void formandMenu() {
        boolean runAgain = true;
        int formandInput;

        while (runAgain) {
            System.out.print("""
                    1. Opret medlem
                    2. Vis medlemsoversigt
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
                        case 2 -> {
                            medlemsOversigt();
                        }
                        case 5 -> {
                            runAgain = false;
                        }
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Ugyldigt input. Indtast et tal mellem 1-5");
            }

        }
    }

    public void createMedlem() {

        try {
            System.out.print("Indtast navn på Medlem: ");
            String medlemName = scan.nextLine();
            System.out.print("Indtast alder på Medlem: ");
            int medlemAge = getIntegerInput();
            System.out.print("Indtast medlemsID: ");
            int medlemID = getIntegerInput();

            System.out.print("Konkurrence svømmer? [y/n]: ");
            boolean konkurrenceSvømmerYesNo = getBooleanInput();


            System.out.print("Aktivt medlem? [y/n]: ");
            boolean aktivYesNo = getBooleanInput();

            // Adder til oprindelig.
            controller.addMedlem(medlemName, medlemAge, medlemID,
                    konkurrenceSvømmerYesNo, aktivYesNo);

            // Adder til newly made så det kun er den vi gemmer til CSV under 'exit'.
            controller.addToNewlyMadeMedlemmere(medlemName, medlemAge, medlemID,
                    konkurrenceSvømmerYesNo, aktivYesNo);

            System.out.println(medlemName + " bliver tilføjet til databasen så snart programmet lukkes."); // Dobbelt tjek Mads' krav.
            scan.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Ugyldig indtastning. Prøv igen.");
            scan.nextLine();
        }

    }

    public void medlemsOversigt() {
        ArrayList<Medlem> listeFraCSV = controller.loadMedlemmereFraCSV();
        for (Medlem m:listeFraCSV) {
            System.out.println(m);
        }
    }


    public int getIntegerInput() {
        while (true) {
            try {
                return scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Ugyldig indtastning. Brug hel tal.");
                scan.nextLine();
            }
        }
    }
    public boolean getBooleanInput() {
        while (true) {
            try {
                String input = scan.next();
                if (input.equalsIgnoreCase("y")) {
                    return true;
                } else if (input.equalsIgnoreCase("n")) {
                        return false;
                    } else {
                    System.out.println("Ugyldig indtastning. Indtast 'y' eller 'n'. Prøv igen.");
                }
                } catch (InputMismatchException e) {
                System.out.println("Ugyldig indtastning. Indtast 'y' eller 'n'. Prøv igen.");
                scan.nextLine();
            }

        }
    }

}



