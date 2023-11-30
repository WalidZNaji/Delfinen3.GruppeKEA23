import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Database {
    Filehandler filehandler = new Filehandler();
    Resultat resultat = new Resultat();

    ArrayList<Medlem> medlemmere;
    ArrayList<Resultat> konkurrenceSvømmereResultater;

    public Database() {
        this.medlemmere = new ArrayList<>();
        this.konkurrenceSvømmereResultater = new ArrayList<>();
    }
    public void addResultat(String name, int age, int medlemID, boolean konkurrenceSvømmer,
                            boolean aktiv, double crawlTid, double brystTid, double butterflyTid, double rygCrawlTid){

        konkurrenceSvømmereResultater.add(new Resultat(name, age, medlemID, konkurrenceSvømmer,aktiv, crawlTid, brystTid, butterflyTid, rygCrawlTid));
    }


    public ArrayList<Resultat> getResultater() {
        return konkurrenceSvømmereResultater;
    }

    public void addMedlem(String name, int age, int medlemID, boolean konkurrenceSvømmer,
                          boolean aktiv) {

            medlemmere.add(new Medlem(name, age, medlemID, konkurrenceSvømmer,aktiv));
        }

    public void gemMedlemmereTilCSV(){
        filehandler.gemTilCSV(medlemmere);
    }
    public void gemResultaterTilCSV(){
        filehandler.gemResultaterTilCSV(konkurrenceSvømmereResultater);
    }

    public void loadMedlemmereFromCSV() {
        medlemmere = filehandler.loadMedlemmereFromCSV();
    }
    public void loadResultatFraCSV() {
        konkurrenceSvømmereResultater = filehandler.loadResultaterFraCSV();
    }
    public ArrayList<Medlem> findMedlemByName(String medlemsNavn) {
        ArrayList<Medlem> lokalMedlemmere = new ArrayList<>();

        for (Medlem medlem : medlemmere) {
            if (medlem.getName().toLowerCase().contains(medlemsNavn.toLowerCase()) && medlem.isAktiv()) {
                lokalMedlemmere.add(medlem);
        }

        }
        return lokalMedlemmere;
    }
    public ArrayList<Resultat> findResultatObjectByMedlemID(int medlemsID) {
        ArrayList<Resultat> lokalListe = new ArrayList<>();
        for (Resultat r : konkurrenceSvømmereResultater) {
            if (r.getMedlemID() == medlemsID) {
                lokalListe.add(r);
            }
        }
        return lokalListe;
    }

    public List<Resultat> top5Crawl() {

        List<Resultat> crawlResultater = new ArrayList<>(konkurrenceSvømmereResultater);

        Collections.sort(crawlResultater, new CrawlComparator());
        int størsteStørrelse = Math.min(5, konkurrenceSvømmereResultater.size());
        List<Resultat> top5CrawlResultater = crawlResultater.subList(0, størsteStørrelse);

        for (Resultat resultat : top5CrawlResultater) {
            System.out.println("Svømmer " + resultat.getName() + "Crawltid" + resultat.getCrawlTid());

        }
        return top5CrawlResultater;
    }



    public void setCrawlTid(double nyCrawlTid) {
        resultat.setCrawlTid(nyCrawlTid);
    }
    public void setBrystTid(double nyBrystTid) {
        resultat.setBrystTid(nyBrystTid);
    }

    public void setButterflyTid(double nyButterflyTid) {
        resultat.setButterflyTid(nyButterflyTid);
    }

    public void setRygCrawlTid(double nyRygCrawlTid) {
        resultat.setRygCrawlTid(nyRygCrawlTid);
    }

}


