import java.util.ArrayList;

public class Database {

    ArrayList<Medlem> medlemmere = new ArrayList<>();

    public void addMedlem(String name, int age, int medlemID, boolean konkurrenceSvømmer,
                          boolean aktiv) {
        medlemmere.add(new Medlem(name, age, medlemID, konkurrenceSvømmer,aktiv));
    }
}
