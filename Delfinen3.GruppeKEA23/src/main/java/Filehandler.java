import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Filehandler {

    PrintStream fileWriter;
    File file = new File("Medlemmere.csv");
    File file2 = new File("Resultater.csv");
    File file3 = new File("Restance.csv");

    public void gemTilCSV(ArrayList<Medlem> medlemmere) {
        try {
            fileWriter = new PrintStream(new FileOutputStream(file));

            if(file.length()==0) {
                fileWriter.println("Name,Age,MedlemID,KonkurrenceSvømmer,Aktiv");
            }
            for (Medlem medlem : medlemmere) {
                String linje = medlem.getName() + "," +
                        medlem.getAge() + "," +
                        medlem.getMedlemID() + "," +
                        medlem.isKonkurrenceSvømmer() + "," +
                        medlem.isAktiv();
                fileWriter.println(linje);
            }
            fileWriter.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Medlem> loadMedlemmereFromCSV() {

        ArrayList<Medlem> medlemmereICSV = new ArrayList<>();

        try (Scanner fileReader = new Scanner(file)) {
            if (!(file.length() == 0)) {
                fileReader.nextLine(); // Skipping the header
            }


            while (fileReader.hasNext()) {
                String linje = fileReader.nextLine();
                String[] attributes = linje.split(",");
                Medlem medlem = new Medlem(attributes[0].trim(),
                        Integer.parseInt(attributes[1].trim()),
                        Integer.parseInt(attributes[2].trim()),
                        Boolean.parseBoolean(attributes[3].trim()),
                        Boolean.parseBoolean(attributes[4].trim()));
                medlemmereICSV.add(medlem);
            }
            return medlemmereICSV;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void gemResultaterTilCSV(ArrayList<Resultat> resultater) {
        try {
            fileWriter = new PrintStream(new FileOutputStream(file2));

            if(file2.length()==0) {
                fileWriter.println("Name,Age,MedlemID,KonkurrenceSvømmer,Aktiv,Crawltid,Brysttid,Butterflytid,Rygcrawltid,CrawlDato,BrystDato,ButterflyDato,RygCrawlDato");
            }
            for (Resultat resultat : resultater) {
                String linje = resultat.getName() + "," +
                        resultat.getAge() + "," +
                        resultat.getMedlemID() + "," +
                        resultat.isKonkurrenceSvømmer() + "," +
                        resultat.isAktiv() + "," +
                        resultat.getCrawlTid() + "," +
                        resultat.getBrystTid() + "," +
                        resultat.getButterflyTid() + "," +
                        resultat.getRygCrawlTid() + "," + resultat.getCrawlDato() + "," +
                        resultat.getBrystDato() + "," + resultat.getButterflyDato() + "," +
                        resultat.getRygCrawlDato();
                fileWriter.println(linje);
            }
            fileWriter.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Resultat> loadResultaterFraCSV() {

        ArrayList<Resultat> resultaterICSV = new ArrayList<>();

        try (Scanner fileReader = new Scanner(file2)) {
            if (!(file.length() == 0)) {
                fileReader.nextLine(); // Skipping the header
            }


            while (fileReader.hasNext()) {
                String linje = fileReader.nextLine();
                String[] attributes = linje.split(",");
                Resultat resultat = new Resultat(attributes[0].trim(), Integer.parseInt(attributes[1].trim()),
                        Integer.parseInt(attributes[2].trim()), Boolean.parseBoolean(attributes[3].trim()),
                        Boolean.parseBoolean(attributes[4].trim()), Double.parseDouble(attributes[5].trim()),
                        Double.parseDouble(attributes[6].trim()), Double.parseDouble(attributes[7].trim()),
                        Double.parseDouble(attributes[8].trim()), attributes[9],
                        attributes[10], attributes[11],
                        attributes[12]);
                resultaterICSV.add(resultat);
            }
            return resultaterICSV;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}