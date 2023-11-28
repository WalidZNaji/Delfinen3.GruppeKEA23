import java.util.ArrayList;

public class Database {
    Filehandler filehandler = new Filehandler();

    ArrayList<Medlem> medlemmere  = filehandler.medlemmereICSV;

    public void addMedlem(String name, int age, int medlemID, boolean konkurrenceSvømmer,
                          boolean aktiv) {

            medlemmere.add(new Medlem(name, age, medlemID, konkurrenceSvømmer,aktiv));
        }

    public void gemMedlemmereTilCSV(ArrayList<Medlem> medlemmere){
        filehandler.gemTilCSV(medlemmere);
    }

    public void loadMedlemmereFromCSV() {
        filehandler.loadMedlemmereFromCSV();
    }

}


