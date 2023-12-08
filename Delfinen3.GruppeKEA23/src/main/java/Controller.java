import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controller {
    private final Database database;
    private  final Kasserer kasserer;
    private Random random;


    public Controller() {
        this.database = new Database();
        this.kasserer = new Kasserer(this);
        this.random = new Random();
    }

    public double udregnIndkomst(){
       return kasserer.generateIncomeOverview();
    }
    public double getSingleMedlemKontingent(int medlemID) {
        return kasserer.getSingleMedlemKontingent(medlemID);
    }
    public String findMedlemByID(int medlemID) {
        return database.findMedlemByID(medlemID);
    }


    public void addMedlem(String name, int age, boolean konkurrenceSvømmer, boolean aktiv) {
        int medlemID = generateUniqueMedlemID();
        database.addMedlem(name, age, konkurrenceSvømmer, aktiv);
        System.out.println("MedlemID: " + medlemID); // Få det ind i Ui
    }

    private int generateUniqueMedlemID() {
        int potentialMedlemID = 1;

        while (medlemIDExists(potentialMedlemID)) {
            potentialMedlemID++;
        }
        return potentialMedlemID;
    }

    private boolean medlemIDExists(int medlemID) {
        return database.medlemIDExists(medlemID);
    }
    public void gemTilCSV (){
        database.gemMedlemmereTilCSV();
    }
    public void gemResultaterTilCSV(){
        database.gemResultaterTilCSV();
    }
    public void gemRestanceCSV(){
        database.gemRestanceCSV();
    }
    public ArrayList<Medlem> getMedlemmere() {
        return database.medlemmere;
    }

    public void loadMedlemmereFraCSV() {
         database.loadMedlemmereFromCSV();
    }
    public void loadResultatFraCSV() {
        database.loadResultatFraCSV();
    }
    public void loadRestanceFraCSV(){
        database.loadRestanceFraCSV();
    }
    public ArrayList<Medlem> findMedlemByName(String medlemsNavn) {
        return database.findMedlemByName(medlemsNavn);
    }

    public ArrayList<Resultat> findResultatObjectByMedlemID(int medlemsID) {
        return database.findResultatObjectByMedlemID(medlemsID);
    }


    public void addResultat(String name, int age, int medlemID, boolean konkurrenceSvømmer,
                            boolean aktiv,double crawlTid, double brystTid, double butterflyTid,
                            double rygCrawlTid, String crawlDato, String brystDato, String butterflyDato, String rygcrawlDato){
        database.addResultat(name, age, medlemID, konkurrenceSvømmer,aktiv, crawlTid, brystTid, butterflyTid,
                rygCrawlTid, crawlDato, brystDato, butterflyDato, rygcrawlDato);
    }
    public ArrayList<Resultat> getResultater() {
        return database.getResultater();
    }
    public ArrayList<Restance> getMedlemmereIRestance() {
        return database.medlemmereIRestance;
    }
    public void addMedlemToRestance(String name, int medlemID) {
        database.addMedlemToRestance(name, medlemID);
    }
    public void removeMedlemFromRestance(int medlemID) {
        database.removeMedlemFromRestance(medlemID);
    }
}
