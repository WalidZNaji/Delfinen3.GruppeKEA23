import java.util.ArrayList;
import java.util.List;

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
    public void gemResultaterTilCSV(){
        database.gemResultaterTilCSV();
    }
    public ArrayList<Medlem> getMedlemmere() {
        return database.medlemmere;
    }

    public void loadMedlemmereFraCSV () {
         database.loadMedlemmereFromCSV();
    }
    public void loadResultatFraCSV() {
        database.loadResultatFraCSV();
    }
    public ArrayList<Medlem> findMedlemByName(String medlemsNavn) {
        return database.findMedlemByName(medlemsNavn);
    }

    public ArrayList<Resultat> findResultatObjectByMedlemID(int medlemsID) {
        return database.findResultatObjectByMedlemID(medlemsID);
    }

    public void addResultat(String name, int age, int medlemID, boolean konkurrenceSvømmer,
                            boolean aktiv,double crawlTid, double brystTid, double butterflyTid, double rygCrawlTid){
        database.addResultat(name, age, medlemID, konkurrenceSvømmer,aktiv, crawlTid, brystTid, butterflyTid, rygCrawlTid);
    }
    public ArrayList<Resultat> getResultater() {
        return database.getResultater();
    }
    public List<Resultat> top5Crawl() {
        return database.top5Crawl();
    }

    public void setCrawlTid(double nyCrawlTid) {
        database.setCrawlTid(nyCrawlTid);
    }
    public void setBrystTid(double nyBrystTid) {
        database.setBrystTid(nyBrystTid);
    }

    public void setButterflyTid(double nyButterflyTid) {
        database.setButterflyTid(nyButterflyTid);
    }

    public void setRygCrawlTid(double nyRygCrawlTid) {
        database.setRygCrawlTid(nyRygCrawlTid);
    }

}
