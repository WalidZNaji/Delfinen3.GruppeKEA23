import java.util.ArrayList;

public class Database {
    Filehandler filehandler = new Filehandler();
    Resultat resultat = new Resultat();
    Restance restance = new Restance();


    ArrayList<Medlem> medlemmere;
    ArrayList<Resultat> konkurrenceSvømmereResultater;
    ArrayList<Restance> medlemmereIRestance;

    public Database() {
        this.medlemmere = new ArrayList<>();
        this.konkurrenceSvømmereResultater = new ArrayList<>();
        this.medlemmereIRestance = new ArrayList<>();
    }

    public String findMedlemByID(int medlemID) {
        for (Medlem m:medlemmere) {
            if (m.getMedlemID() == medlemID) {
                return m.getName();
            }
        }
        return null;
    }
    public void addResultat(String name, int age, int medlemID, boolean konkurrenceSvømmer,
                            boolean aktiv, double crawlTid, double brystTid, double butterflyTid, double rygCrawlTid,
                            String crawlDato, String brystDato, String butterflyDato, String rygcrawlDato){

        konkurrenceSvømmereResultater.add(new Resultat(name, age, medlemID, konkurrenceSvømmer,aktiv,
                crawlTid, brystTid, butterflyTid, rygCrawlTid,
                crawlDato, brystDato, butterflyDato, rygcrawlDato));
    }


    public ArrayList<Resultat> getResultater() {
        return konkurrenceSvømmereResultater;
    }

    public void addMedlem(String name, int age, boolean konkurrenceSvømmer, boolean aktiv) {
        int medlemID = generateUniqueMedlemID(); // Generer uniks medlemsID når der oprettes nyt Medlem.
        medlemmere.add(new Medlem(name, age, medlemID, konkurrenceSvømmer, aktiv));
    }

    private int generateUniqueMedlemID() {
        int potentialMedlemID = 1;

        while (medlemIDExists(potentialMedlemID)) {
            potentialMedlemID++;
        }

        return potentialMedlemID;
    }

    public boolean medlemIDExists(int medlemID) {
        for (Medlem medlem : medlemmere) {
            if (medlem.getMedlemID() == medlemID) {
                return true;
            }
        }
        return false;
    }

    public void gemMedlemmereTilCSV(){
        filehandler.gemTilCSV(medlemmere);
    }
    public void gemResultaterTilCSV(){
        filehandler.gemResultaterTilCSV(konkurrenceSvømmereResultater);
    }
    public void gemRestanceCSV(){
        filehandler.gemRestanceCSV(medlemmereIRestance);
    }

    public void loadMedlemmereFromCSV() {
        medlemmere = filehandler.loadMedlemmereFromCSV();
    }
    public void loadResultatFraCSV() {
        konkurrenceSvømmereResultater = filehandler.loadResultaterFraCSV();
    }
    public void loadRestanceFraCSV(){
        medlemmereIRestance = filehandler.loadFromRestance();
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

    // Restance - Metoder
    public ArrayList<Restance> getMedlemmereIRestance() {
        return medlemmereIRestance;
    }
    public void addMedlemToRestance(String name, int medlemId) {
        restance = new Restance(name, medlemId);
        medlemmereIRestance.add(restance);
    }
    public void removeMedlemFromRestance(int medlemID) {
        ArrayList<Restance> fjernMedlemmere = new ArrayList<>();
        for (Restance re:medlemmereIRestance) {
            if (re.getMedlemID() == medlemID) {
                fjernMedlemmere.add(re);
            }
        }
        medlemmereIRestance.removeAll(fjernMedlemmere);
    }

}



