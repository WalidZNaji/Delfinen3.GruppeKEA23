public class Resultat extends Medlem {

    private Medlem medlem;

    private String disciplin;

    private double tid;

    public Resultat(String name, int age, int meldlemID, boolean konkurrenceSvømmer, boolean aktiv,
                    String disciplin, double tid) {
        super(name, age, meldlemID, konkurrenceSvømmer, aktiv);
        this.disciplin = disciplin;
        this.tid = tid;
    }


    public void setDisciplin(String disciplin) {
        this.disciplin = disciplin;
    }

    public void setTid(double tid) {
        this.tid = tid;
    }
    @Override
    public String toString() {
        return "\n" + "Navn: " + Resultat.super.getName() + "\n"  +
                "Alder: " + Resultat.super.getAge() + "\n"  +
                "Medlemsid: " + Resultat.super.getMedlemID() + "\n"  +
                "Disciplin: " + disciplin + "\n" +
                "Tid: " + tid +  "\n" +
                "\u2500".repeat(50);
    }
}
