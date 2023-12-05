import java.lang.reflect.Array;
import java.util.*;

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
                    4. Vis enkelt medlemds kontingent oversigt
                    5. Restance
                    6. Tilbage til hovedmenu
                    """);
            try {
                userInput = Integer.parseInt(scan.nextLine());
                if (userInput < 1 || userInput > 6) {
                    System.out.println("Ugyldigt input. Indtast et tal mellem 1-6");
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
                            System.out.print("Søg efter medlems ID: ");
                            int søgning = scan.nextInt();
                            scan.nextLine();
                            String navnPåMedlem = controller.findMedlemByID(søgning);
                            double kontingentBetaling = controller.getSingleMedlemKontingent(søgning);
                            System.out.println(navnPåMedlem + "s kontingent betaling: " + kontingentBetaling
                            + "\n");
                        }
                        case 5 -> restanceMenu();
                        case 6 -> runAgain = false;
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
                    3. vis resultater over top 5
                    4. Tilbage til hovedmenu.
                    """);
            try {
                userInput = Integer.parseInt(scan.nextLine());
                if (userInput < 1 || userInput > 4) {
                    System.out.println("Ugyldigt input. Indtast et tal mellem 1-4");
                } else {
                    switch (userInput) {
                        case 1 -> editResultat();
                        case 2 -> visResultater();
                        case 3 -> top5Swimmers();
                        case 4 -> runAgain = false;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Ugyldigt input. Indtast et tal mellem 1-4");
            }
        }
    }


    public void restanceMenu() {
        boolean runAgain = true;
        int userInput;

        while (runAgain) {
            System.out.println("""
                    1. Tilføj medlem til restance.
                    2. Fjern medlem fra restance.
                    3. Vis oversigt over medlemmere i restance.
                    4. Tilbage.
                     """);
            try {
                userInput = Integer.parseInt(scan.nextLine());
                if (userInput < 1 || userInput > 4) {
                    System.out.println("Ugyldigt input. Indtast et tal mellem 1-4");
                } else {
                    switch (userInput) {
                        case 1 -> {
                            System.out.println("Indtast medlemmets ID: ");
                            int medlemID = scan.nextInt();
                            scan.nextLine();
                            String medlemName = controller.findMedlemByID(medlemID);
                            if (controller.findMedlemByID(medlemID) != null) {
                                controller.addMedlemToRestance(medlemName, medlemID);
                                System.out.println(medlemName + ", " + medlemID + " er blevet tilføjet" +
                                        " til restance listen.");
                            } else
                                System.out.println("Intet medlem fundet.");
                        }
                        case 2 -> {
                            System.out.println("Indtast medlemmets ID: ");
                            int medlemID = scan.nextInt();
                            scan.nextLine();
                            String medlemName = controller.findMedlemByID(medlemID);
                            if (controller.findMedlemByID(medlemID) != null) {
                                controller.removeMedlemFromRestance(medlemID);
                                System.out.println(medlemName + ", " + medlemID + " er blevet fjernet" +
                                        " fra restance listen.");
                            } else
                                System.out.println("Intet medlem fundet.");

                        }
                        case 3 -> {
                            oversigtRestance();
                        }
                        case 4 -> runAgain = false;

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

            System.out.print("Konkurrence svømmer? [y/n]: ");
            boolean konkurrenceSvømmerYesNo = getBooleanInput();


            System.out.print("Aktivt medlem? [y/n]: ");
            boolean aktivYesNo = getBooleanInput();

            // Adder til oprindelig.
            controller.addMedlem(medlemName, medlemAge,
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
                    //scan.nextLine(); // Consume the newline character
                    if (medlemPick > 0 && medlemPick <= søgeresultat.size()) {
                        medlemToEdit = søgeresultat.get(medlemPick - 1);
                        break;
                    } else {
                        System.out.print("Tal ikke inde for rækkevidde. Prøv igen: ");
                    }
                } catch (InputMismatchException e) {
                    System.out.print("Ugyldigt input. Indtast et tal fx. '1': ");
                    scan.nextLine(); // Consume the invalid input
                }
            }
        }



        if (medlemToEdit != null) {
            System.out.println("Vælg disciplin:");
            listeAfDiscipliner();
            System.out.print("Indtast tal: ");
            int disciplinInput = scan.nextInt();
            //scan.nextLine();

            double crawlTid = 0.0;
            double brystTid = 0.0;
            double butterflyTid = 0.0;
            double rygCrawlTid = 0.0;
            String crawlDato = null;
            String brystDato = null;
            String butterflyDato = null;
            String rygcrawlDato = null;

            ArrayList<Resultat> existingResultat =
                    controller.findResultatObjectByMedlemID(medlemToEdit.getMedlemID());

            if (!existingResultat.isEmpty()) {
                Resultat existingResult = existingResultat.get(0);
                crawlTid = existingResult.getCrawlTid();
                brystTid = existingResult.getBrystTid();
                butterflyTid = existingResult.getButterflyTid();
                rygCrawlTid = existingResult.getRygCrawlTid();
                crawlDato = existingResult.getCrawlDato();
                brystDato = existingResult.getBrystDato();
                butterflyDato = existingResult.getButterflyDato();
                rygcrawlDato = existingResult.getRygCrawlDato();
            }
            boolean inputValid = true; // Bruges til kontrol ved udprintning.
            boolean runAgain = true; // Til at loop hver case # indtil korretkt indput.
            switch (disciplinInput) {
                case 1 -> {
                    while (runAgain) {
                        try {
                            System.out.println("Indtast crawl tid: ");
                            crawlTid = scan.nextDouble();
                            System.out.println("Dato: ");
                            crawlDato = scan.next(); // læser som string så der kan bruges '/, -'
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Ugyldigt svar.");
                            inputValid = false; // Ugyldigt input, se 'else' ved udprintnings blok.
                        }
                        runAgain = false;
                    }
                }
                case 2 -> {
                    while (runAgain) {
                        try {
                            System.out.println("Indtast brystsvømnings tid: ");
                            brystTid = scan.nextDouble();
                            System.out.println("Dato: ");
                            brystDato = scan.next();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Ugyldigt svar.");
                            inputValid = false;
                        }
                        runAgain = false;
                    }
                }
                case 3 -> {
                    while (runAgain) {
                        try {
                            System.out.println("Indtast butterfly tid: ");
                            butterflyTid = scan.nextDouble();
                            System.out.println("Dato: ");
                            butterflyDato = scan.next();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Ugyldigt svar.");
                            inputValid = false;
                        }
                        runAgain = false;
                    }
                }
                case 4 -> {
                    while (runAgain) {
                        try {
                            System.out.println("Indtast rygcrawl tid: ");
                            rygCrawlTid = scan.nextDouble();
                            System.out.println("Dato: ");
                            rygcrawlDato = scan.next();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Ugyldigt svar.");
                            inputValid = false;
                        }
                        runAgain = false;
                    }
                }
            }

            if (inputValid) { // Kontrol før ændring.
                if (!existingResultat.isEmpty()) {
                    Resultat existingResult = existingResultat.get(0);

                    if (crawlTid > 0) existingResult.setCrawlTid(crawlTid);
                    if (brystTid > 0) existingResult.setBrystTid(brystTid);
                    if (butterflyTid > 0) existingResult.setButterflyTid(butterflyTid);
                    if (rygCrawlTid > 0) existingResult.setRygCrawlTid(rygCrawlTid);
                    if (crawlDato != null) existingResult.setCrawlDato(crawlDato);
                    if (brystDato != null) existingResult.setBrystDato(brystDato);
                    if (butterflyDato != null) existingResult.setButterflyDato(butterflyDato);
                    if (rygcrawlDato != null) existingResult.setRygCrawlDato(rygcrawlDato);

                    System.out.println(medlemToEdit.getName() + "'s resultat er blevet opdateret.");
                } else {
                    controller.addResultat(
                            medlemToEdit.getName(), medlemToEdit.getAge(), medlemToEdit.getMedlemID(),
                            medlemToEdit.isKonkurrenceSvømmer(), medlemToEdit.isAktiv(), crawlTid,
                            brystTid, butterflyTid, rygCrawlTid, crawlDato, brystDato, butterflyDato,
                            rygcrawlDato
                    );

                    System.out.println(medlemToEdit.getName() + "'s resultat er blevet tilføjet.");
                }
            }
        }

        scan.nextLine();
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
        for (Resultat r : resultaterFraCSV) {
            System.out.println(r);
        }
    }

    public void top5Swimmers() {
        System.out.println("Top 5 Svømmere:");

        System.out.println("Vælg disciplin:");
        listeAfDiscipliner();
        System.out.print("Indtast tal: ");
        int disciplinInput = scan.nextInt();
        scan.nextLine(); // Consume the newline character

        String discipline;
        switch (disciplinInput) {
            case 1 -> discipline = "Crawl";
            case 2 -> discipline = "BrystSvømning";
            case 3 -> discipline = "Butterfly";
            case 4 -> discipline = "Rygcrawl";
            default -> {
                System.out.println("Ugyldigt valg. Vælg en disciplin fra listen.");
                return;
            }
        }

        System.out.println("Top 5 Svømmere for " + discipline + ":");

        ArrayList<Resultat> topJunior = findTopSwimmers(discipline, "Junior");
        ArrayList<Resultat> topSenior = findTopSwimmers(discipline, "Senior");

        System.out.println("Top 5 Junior Svømmere:");
        displaySwimmers(topJunior, discipline);

        System.out.println("Top 5 Senior Svømmere:");
        displaySwimmers(topSenior, discipline);
    }

    private ArrayList<Resultat> findTopSwimmers(String discipline, String ageCategory) {
        ArrayList<Resultat> allResults = controller.getResultater();
        ArrayList<Resultat> topSwimmers = new ArrayList<>();

        // Filtrer resultat baseret på alder og disciplin
        for (Resultat result : allResults) {
            if (result.getAgeCategory().equals(ageCategory)) {
                double time = switch (discipline) {
                    case "Crawl" -> result.getCrawlTid();
                    case "BrystSvømning" -> result.getBrystTid();
                    case "Butterfly" -> result.getButterflyTid();
                    case "Rygcrawl" -> result.getRygCrawlTid();
                    default -> throw new IllegalArgumentException("Ugyldig disciplin");
                };

                if (time >= 0) {
                    topSwimmers.add(result);
                }
            }
        }

        // Sorter top svømmere baseret på tid
        topSwimmers.sort(Comparator.comparingDouble(result -> {
            switch (discipline) {
                case "Crawl" -> {
                    return result.getCrawlTid();
                }
                case "BrystSvømning" -> {
                    return result.getBrystTid();
                }
                case "Butterfly" -> {
                    return result.getButterflyTid();
                }
                case "Rygcrawl" -> {
                    return result.getRygCrawlTid();
                }
                default -> throw new IllegalArgumentException("Ugyldig disciplin");
            }
        }));

        // Behold kun top 5
        return new ArrayList<>(topSwimmers.subList(0, Math.min(5, topSwimmers.size())));
    }

    private void displaySwimmers(ArrayList<Resultat> swimmers, String discipline) {
        for (Resultat swimmer : swimmers) {
            System.out.println(swimmer.getResultStringForDiscipline(discipline));
        }
        System.out.println();  // skille linje mellem disciplinerne
    }
    public void oversigtRestance() {
        if (!controller.getMedlemmereIRestance().isEmpty()) {
            System.out.println("Medlemmere i restance:");
            for (Restance re : controller.getMedlemmereIRestance()) {
                System.out.println("Navn: " + re.getName() + " ID: " + re.getMedlemID());
            }
        } else if (controller.getMedlemmereIRestance().isEmpty()) {
            System.out.println("Ingen medlemmere i restance");
        }
    }


}






