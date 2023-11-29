import java.util.ArrayList;

public class Controller {
    private final Database database;
    private  final Kasserer kasserer;


    public Controller() {
        this.database = new Database();
        this.kasserer = new Kasserer(this);
    }

    public double udregnIndkomst(){
       return kasserer.generateIncomeOverview();
    }


    public void addMedlem(String name, int age, int medlemID,
                          boolean konkurrenceSvømmer,
                          boolean aktiv){
        database.addMedlem(name, age, medlemID,
                konkurrenceSvømmer,aktiv);

    }
    public void gemTilCSV (){
        database.gemMedlemmereTilCSV();
    }
    public ArrayList<Medlem> getMedlemmere() {
        return database.medlemmere;
    }

    public void loadMedlemmereFraCSV () {
         database.loadMedlemmereFromCSV();
    }
    public ArrayList<Medlem> findMedlemByName(String medlemsNavn) {
        return database.findMedlemByName(medlemsNavn);
    }
    public void addResultat(String name, int age, int medlemID, boolean konkurrenceSvømmer,
                            boolean aktiv, String disciplin, Double tid){
        database.addResultat(name, age, medlemID, konkurrenceSvømmer,aktiv, disciplin, tid);
    }
    public ArrayList<Medlem> getResultater() {
        return database.getResultater();
    }

}
