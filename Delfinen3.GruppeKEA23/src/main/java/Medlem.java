public class Medlem {
    private String name;
    private int age;
    private int medlemID;
    private boolean konkurrenceSvømmer; // hvis false = motionist;
    private boolean aktiv; // Hvis false = passiv.

public Medlem() {
}
    public Medlem(String name, int age, int meldlemID, boolean konkurrenceSvømmer,
                  boolean aktiv) {
        this.name = name;
        this.age = age;
        this.medlemID = meldlemID;
        this.konkurrenceSvømmer = konkurrenceSvømmer;
        this.aktiv = aktiv;
    }



    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getMedlemID() {
        return medlemID;
    }

    public boolean isKonkurrenceSvømmer() {
        return konkurrenceSvømmer;
    }

    public boolean isAktiv() {
        return aktiv;
    }

    //_____________________________________________________________________________

    public String toString() {

        String konkurrenceSvømmer = isKonkurrenceSvømmer() ? "Konkurrencesvømmer" : "Ikke Konkurrencesvømmer";
        String aktiv = isKonkurrenceSvømmer() ? "Aktiv" : "Ikke Aktiv";

        return "\n" + "Navn: " + name + "\n"  +
                  "Alder: " + age + "\n"  +
                   "Medlemsid: " + medlemID + "\n"  +
                    "Niveau: " + konkurrenceSvømmer + "\n" +
                "Aktiv/Uaktiv: " +  aktiv  + "\n" +  "\u2500".repeat(50);

    }


}
