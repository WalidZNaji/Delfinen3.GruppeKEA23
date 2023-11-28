import java.util.ArrayList;

public class Controller {
    private final Database database;
    ArrayList<Medlem> newlyMadeMedlemmere = new ArrayList<>();

    public Controller() {
        this.database = new Database();
    }

    public void udregnIndkomst(){
        kasserer.generateIncomeOverview();
    }


    public void addToNewlyMadeMedlemmere(String name, int age, int medlemID,
                                         boolean konkurrenceSvømmer,
                                         boolean aktiv) {
        newlyMadeMedlemmere.add(new Medlem(name, age, medlemID, konkurrenceSvømmer,aktiv));
    }

    public void addMedlem(String name, int age, int medlemID,
                          boolean konkurrenceSvømmer,
                          boolean aktiv){
        database.addMedlem(name, age, medlemID,
                konkurrenceSvømmer,aktiv);

    }
    public void gemTilCSV (ArrayList<Medlem> medlemmere){
        database.gemMedlemmereTilCSV(medlemmere);
    }
    public ArrayList<Medlem> getMedlemmere() {
        return database.medlemmere;
    }

    public void loadMedlemmereFraCSV () {
         database.loadMedlemmereFromCSV();

    }

}
