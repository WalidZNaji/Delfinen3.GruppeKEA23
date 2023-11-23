import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Filehandler {

    PrintStream fileWriter;

    Scanner fileReader;
    ArrayList<Medlem> medlemmereICSV = new ArrayList<>();
    File file = new File("Medlemmere.csv");



    public void gemTilCSV(ArrayList<Medlem> medlemmere) {
        try {
            fileWriter = new PrintStream(new FileOutputStream(file,true));

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
        try {
            fileReader = new Scanner(file);
            if (fileReader.hasNextLine()) {
                fileReader.nextLine(); // Skip the header
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
                fileReader.close();
            }
            return medlemmereICSV;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}




