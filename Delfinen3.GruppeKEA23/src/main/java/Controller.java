public class Controller {
    private Database database;

    public Controller() {
        this.database = new Database();
    }
    public void addMedlem(String name, int age, int medlemID,
                          boolean konkurrenceSvømmer,
                          boolean aktiv) {
        database.medlemmere.add(new Medlem(name, age, medlemID,
                konkurrenceSvømmer,aktiv));
    }
    public void gemTilCSV (ArrayList<Medlem> medlemmere){
        database.gemMedlemmereTilCSV(medlemmere);
    }
    public ArrayList<Medlem> getMedlemmere() {
        return database.medlemmere;
    }

}
