import java.lang.reflect.Array;
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
        controller.loadResultatFraCSV();
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
                            trænerMenu();
                        }
                        case 3 -> {
                            System.out.println("Programmet er lukket.");
                            runAgain = false;
                            controller.gemTilCSV();
                            controller.gemResultaterTilCSV();

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
                    4. Tilbage til hovedmenu
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

    public void trænerMenu() {
        boolean runAgain = true;
        int userInput;

        while (runAgain) {
            System.out.print("""
                    1. Indtast resultat.
                    2. Vis resultater oversigt.
                    3. Tilbage til hovedmenu.
                    """);
            try {
                userInput = Integer.parseInt(scan.nextLine());
                if (userInput < 1 || userInput > 4) {
                    System.out.println("Ugyldigt input. Indtast et tal mellem 1-4");
                } else {
                    switch (userInput) {
                        case 1 -> editResultat();
                        case 2 -> visResultater();
                        case 3 -> runAgain = false;
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


            System.out.println(medlemName + " bliver tilføjet til databasen så snart programmet lukkes."); // Dobbelt tjek Mads' krav.
            scan.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Ugyldig indtastning. Prøv igen.");
            scan.nextLine();
        }
    }

    public void medlemsOversigt() {
        ArrayList<Medlem> listeFraCSV = controller.getMedlemmere();
        for (Medlem m : listeFraCSV) {
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

    public void editResultat() {
        System.out.println("Indtast medlemmets navn");
        String brugerinput = scan.nextLine();

        //find personer
        ArrayList<Medlem> søgeresultat = controller.findMedlemByName(brugerinput);
        Medlem medlemToEdit = null;

        //Søgningen finder ingen personer
        if (søgeresultat.isEmpty()) {
            System.out.println("Der er ingen medlemmer, hvis navn stemmer overens med det indtastede. ");
        } else {
            // Vælg en person i søgeresultat med flere personer
            System.out.println("Vælg medlem");
            int tæller = 1;
            for (Medlem medlem : søgeresultat) {
                System.out.println(tæller++ + ". " +
                        medlem.getName() + ", " +
                        medlem.getAge() + ", " +
                        medlem.getMedlemID() + ", " +
                        medlem.isKonkurrenceSvømmer() + ", " +
                        medlem.isAktiv() + ", ");

            }
            int medlemPick = 0;
            while (true) {
                try {
                    medlemPick = scan.nextInt();
                    scan.nextLine(); // Håndterer Scanner Bug
                    if (medlemPick > 0 && medlemPick <= søgeresultat.size()) {
                        medlemToEdit = søgeresultat.get(medlemPick - 1);
                        break;
                    } else {
                        System.out.print("Tal ikke inde for rækkevidde. Prøv igen: ");
                    }
                } catch (InputMismatchException e) {
                    System.out.print("Ugyldigt input. Indtast et tal fx. '1': ");
                    scan.nextLine();

                }
            }

        }

        if (medlemToEdit != null) {
            System.out.println("Vælg disciplin:");
            listeAfDiscipliner();
            System.out.print("Indtast tal: ");
            int disciplinInput = scan.nextInt();


            double crawlTid = 0.0;
            double brystTid = 0.0;
            double butterflyTid = 0.0;
            double rygCrawlTid = 0.0;

            ArrayList<Resultat> existingResultat =
                    controller.findResultatObjectByMedlemID(medlemToEdit.getMedlemID());

            if (!existingResultat.isEmpty()) {
                Resultat existingResult = existingResultat.get(0);
                crawlTid = existingResult.getCrawlTid();
                brystTid = existingResult.getBrystTid();
                butterflyTid = existingResult.getButterflyTid();
                rygCrawlTid = existingResult.getRygCrawlTid();
            }

            switch (disciplinInput) {
                case 1 -> {
                    System.out.println("Indtast crawl tid: ");
                    crawlTid = scan.nextDouble();
                }
                case 2 -> {
                    System.out.println("Indtast brystsvømnings tid: ");
                    brystTid = scan.nextDouble();
                }
                case 3 -> {
                    System.out.println("Indtast butterfly tid: ");
                    butterflyTid = scan.nextDouble();
                }
                case 4 -> {
                    System.out.println("Indtast rygcrawl tid: ");
                    rygCrawlTid = scan.nextDouble();
                }
            }


            if (!existingResultat.isEmpty()) {
                Resultat existingResult = existingResultat.get(0);

                if (crawlTid > 0) existingResult.setCrawlTid(crawlTid);
                if (brystTid > 0) existingResult.setBrystTid(brystTid);
                if (butterflyTid > 0) existingResult.setButterflyTid(butterflyTid);
                if (rygCrawlTid > 0) existingResult.setRygCrawlTid(rygCrawlTid);

                System.out.println(medlemToEdit.getName() + "'s resultat er blevet opdateret.");
            } else {

                controller.addResultat(
                        medlemToEdit.getName(),
                        medlemToEdit.getAge(),
                        medlemToEdit.getMedlemID(),
                        medlemToEdit.isKonkurrenceSvømmer(),
                        medlemToEdit.isAktiv(),
                        crawlTid,
                        brystTid,
                        butterflyTid,
                        rygCrawlTid
                );

                System.out.println(medlemToEdit.getName() + "'s resultat er blevet tilføjet.");
            }
        }

        scan.nextLine(); // Consume the newline character
    }
    public void listeAfDiscipliner() {
        System.out.println("""
                    1. Crawl
                    2. BrystSvømning
                    3. Butterfly
                    4. Rygcrawl
                    """);
        }
    public void visResultater() {
        ArrayList<Resultat> resultaterFraCSV = controller.getResultater();
            for (Resultat r:resultaterFraCSV) {
                System.out.println(r);
            }
        }

}






