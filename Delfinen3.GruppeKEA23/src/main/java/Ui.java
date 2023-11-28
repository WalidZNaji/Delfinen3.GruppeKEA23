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
        controller.loadMedlemmereFraCSV();
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
                   1. Administrativ menu
                   2. Træner menu
                   3. Afslut program
                    """);

            try {
                userInput = Integer.parseInt(scan.nextLine());
                if (userInput < 1 || userInput > 3) {
                    System.out.println("Ugyldigt input. Indtast et tal mellem 1-3");
                } else {
                    switch (userInput) {
                        case 1 -> {
                            administrativ();
                        }
                        case 2 -> {
                            // træner

                        }
                        case 3 -> {
                            System.out.println("Programmet er lukket.");
                            runAgain = false;
                            controller.gemTilCSV(controller.newlyMadeMedlemmere);

                        }

                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Ugyldigt input. Indtast et tal mellem 1-3");
            }
        }
    }

    public void administrativ() {
        boolean runAgain = true;
        int userInput;

        while (runAgain) {
            System.out.print("""
                    1. Opret medlem
                    2. Vis medlemsoversigt
                    3. Vis kontigent oversigt
                    5. Tilbage til hovedmenu
                    """);
            try {
                userInput = Integer.parseInt(scan.nextLine());
                if (userInput < 1 || userInput > 4) {
                    System.out.println("Ugyldigt input. Indtast et tal mellem 1-4");
                } else {
                    switch (userInput) {
                        case 1 -> {
                            createMedlem();
                        }
                        case 2 -> {
                            medlemsOversigt();
                        }
                        case 3 -> {
                            System.out.println("Samlet indkomst: ");
                            System.out.println(controller.udregnIndkomst() + "kr");
                        }
                        case 4 -> {
                            runAgain = false;
                        }
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Ugyldigt input. Indtast et tal mellem 1-4");
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
        ArrayList<Medlem> listeFraCSV = controller.getMedlemmere();
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



