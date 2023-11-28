import java.util.ArrayList;

public class Database {
    Filehandler filehandler = new Filehandler();

    ArrayList<Medlem> medlemmere;

    public Database() {
        this.medlemmere = new ArrayList<>();
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

}


