public class Resultat extends Medlem {

    private double crawlTid;
    private double brystTid;
    private double butterflyTid;
    private double rygCrawlTid;

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
                    double crawlTid, double brystTid, double butterflyTid, double rygCrawlTid) {
        super(name, age, meldlemID, konkurrenceSvømmer, aktiv);
        this.crawlTid = crawlTid;
        this.brystTid = brystTid;
        this.butterflyTid = butterflyTid;
        this.rygCrawlTid = rygCrawlTid;
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

    @Override
    public String toString() {
        return "\n" + "Navn: " + Resultat.super.getName() + "\n"  +
                "Alder: " + Resultat.super.getAge() + "\n"  +
                "Medlemsid: " + Resultat.super.getMedlemID() + "\n"  +
                "Crawl: " + crawlTid + "\n" +
                "Brystsvømning: " + brystTid + "\n" +
                "Butterfly: " + butterflyTid + "\n" +
                "Rygcrawl: " + rygCrawlTid + "\n" +
                "\u2500".repeat(50);
    }

}
