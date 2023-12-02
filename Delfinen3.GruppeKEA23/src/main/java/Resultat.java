public class Resultat extends Medlem {

    private double crawlTid;
    private double brystTid;
    private double butterflyTid;
    private double rygCrawlTid;
    private String crawlDato;
    private String brystDato;
    private String butterflyDato;
    private String rygCrawlDato;

    public void setCrawlDato(String crawlDato) {
        this.crawlDato = crawlDato;
    }

    public void setBrystDato(String brystDato) {
        this.brystDato = brystDato;
    }

    public void setButterflyDato(String butterflyDato) {
        this.butterflyDato = butterflyDato;
    }

    public void setRygCrawlDato(String rygCrawlDato) {
        this.rygCrawlDato = rygCrawlDato;
    }

    public String getCrawlDato() {
        return crawlDato;
    }

    public String getBrystDato() {
        return brystDato;
    }

    public String getButterflyDato() {
        return butterflyDato;
    }

    public String getRygCrawlDato() {
        return rygCrawlDato;
    }

    public Resultat() {
    }
        public String getAgeCategory() {
            if (getAge() < 18) {
                return "Junior";
            } else {
                return "Senior";
            }
        }

        public Resultat(String name, int age, int meldlemID, boolean konkurrenceSvømmer, boolean aktiv,
                    double crawlTid, double brystTid, double butterflyTid, double rygCrawlTid,
                        String crawlDato, String brystDato, String butterDato, String rygCrawlDato ) {
        super(name, age, meldlemID, konkurrenceSvømmer, aktiv);
        this.crawlTid = crawlTid;
        this.brystTid = brystTid;
        this.butterflyTid = butterflyTid;
        this.rygCrawlTid = rygCrawlTid;
        this.crawlDato = crawlDato;
        this.brystDato = brystDato;
        this.butterflyDato = butterDato;
        this.rygCrawlDato = rygCrawlDato;
    }


   public void setCrawlTid(double nyCrawlTid) {
        this.crawlTid = nyCrawlTid;
   }
   public void setBrystTid(double nyBrystTid) {
        this.brystTid = nyBrystTid;
   }

    public void setButterflyTid(double nyButterflyTid) {
        this.butterflyTid = nyButterflyTid;
    }

    public void setRygCrawlTid(double nyRygCrawlTid) {
        this.rygCrawlTid = nyRygCrawlTid;
    }

    public double getCrawlTid() {
        return crawlTid;
    }

    public double getBrystTid() {
        return brystTid;
    }

    public double getButterflyTid() {
        return butterflyTid;
    }

    public double getRygCrawlTid() {
        return rygCrawlTid;
    }

    @Override
    public int getMedlemID() {
        return super.getMedlemID();
    }

    public String getResultStringForDiscipline(String discipline) {
        String resultString = "\n" + "Navn: " + Resultat.super.getName() + "\n" +
                "Alder: " + Resultat.super.getAge() + "\n" +
                "Medlemsid: " + Resultat.super.getMedlemID() + "\n";

        switch (discipline) {
            case "Crawl" -> resultString += "Crawl: " + crawlTid + ", Dato: " + crawlDato;
            case "BrystSvømning" -> resultString += "Brystsvømning: " + brystTid + ", Dato: " + brystDato;
            case "Butterfly" -> resultString += "Butterfly: " + butterflyTid + ", Dato: " + butterflyDato;
            case "Rygcrawl" -> resultString += "Rygcrawl: " + rygCrawlTid + ", Dato: " + rygCrawlDato;
            default -> throw new IllegalArgumentException("Ugyldig disciplin");
        }

        resultString += "\n" + "\u2500".repeat(50);
        return resultString;
    }
    @Override
    public String toString() {
        return "\n" + "Navn: " + Resultat.super.getName() + "\n"  +
                "Alder: " + Resultat.super.getAge() + "\n"  +
                "Medlemsid: " + Resultat.super.getMedlemID() + "\n"  +
                "Crawl: " + crawlTid + ", Dato: " + crawlDato +  "\n" +
                "Brystsvømning: " + brystTid + ", Dato: " + brystDato + "\n" +
                "Butterfly: " + butterflyTid + ", Dato: " + butterflyDato + "\n" +
                "Rygcrawl: " + rygCrawlTid + ", Dato: " + rygCrawlDato + "\n" +
                "\u2500".repeat(50);
    }
}
