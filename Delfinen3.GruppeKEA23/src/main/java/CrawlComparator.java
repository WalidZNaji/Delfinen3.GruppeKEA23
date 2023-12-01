import java.util.Comparator;

public class CrawlComparator implements Comparator<Resultat> {

    @Override
    public int compare(Resultat crawlTid1, Resultat crawlTid2) {
        return Double.compare(crawlTid1.getCrawlTid(), crawlTid2.getCrawlTid());
    }
    }


