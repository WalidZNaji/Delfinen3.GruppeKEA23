public class Medlem {
    private String name;
    private int age;
    private int medlemID;
    private boolean konkurrenceSvømmer; // hvis false = motionist;
    private boolean aktiv; // Hvis false = passiv.


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

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMedlemID(int medlemID) {
        this.medlemID = medlemID;
    }

    public void setKonkurrenceSvømmer(boolean konkurrenceSvømmer) {
        this.konkurrenceSvømmer = konkurrenceSvømmer;
    }

    public void setAktiv(boolean aktiv) {
        this.aktiv = aktiv;
    }

}
