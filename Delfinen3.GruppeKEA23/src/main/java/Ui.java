import java.util.Scanner;

public class Ui {
    private final Scanner scan;
    private final Controller controller;

    public Ui() {
        scan = new Scanner(System.in);
        controller = new Controller();

    }
    public void startProgram() {
        boolean runAgain = true;
        startMessage();
        startMenu();







    }
    public void startMessage() {
        System.out.println("Velkommen til Svømmeklubben Delfinen!");
    }
    public void startMenu() {
        int userInput = 0;


        while (true) {
            System.out.println("Log ind som:");
            System.out.print("""
                1. Formand
                2. Træner
                3. Kasserer
                4. Elev
                """);

            if (scan.hasNextInt()) {
                userInput = scan.nextInt();
                if (userInput < 1 || userInput > 4) {
                    System.out.println("Ugyldigt input. Indtast et tal mellem 1-4");
                    scan.nextLine();
                }

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

                }
            }
        }
    }
    public void formandMenu () {
        System.out.print("""
                    1. Opret medlem.
                    """);
        int formandInput = scan.nextInt();
        switch (formandInput) {
            case 1 -> {
                createMedlem();
                System.out.println();
            }
        }
    }




    public void createMedlem() {
        scan.nextLine(); // scanner bug
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

        formandMenu();


    }
    public void loginErrorHandling(){

    }
}
