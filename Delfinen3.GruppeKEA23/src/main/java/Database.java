import java.util.ArrayList;

public class Database {
    Filehandler filehandler = new Filehandler();

    ArrayList<Medlem> medlemmere;
    ArrayList<Medlem> konkurrenceSvømmereResultater;

    public Database() {
        this.medlemmere = new ArrayList<>();
    }
    public void addResultat(String name, int age, int medlemID, boolean konkurrenceSvømmer,
                            boolean aktiv, String disciplin, double tid){

        konkurrenceSvømmereResultater.add(new Resultat(name, age, medlemID, konkurrenceSvømmer,aktiv,
                disciplin, tid));
    }


    public ArrayList<Medlem> getResultater() {
        return konkurrenceSvømmereResultater;
    }

    public void addMedlem(String name, int age, int medlemID, boolean konkurrenceSvømmer,
                          boolean aktiv) {

            medlemmere.add(new Medlem(name, age, medlemID, konkurrenceSvømmer,aktiv));
        }

    public void gemMedlemmereTilCSV(){
        filehandler.gemTilCSV(medlemmere);
    }

    public void loadMedlemmereFromCSV() {
        medlemmere = filehandler.loadMedlemmereFromCSV();
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

}


