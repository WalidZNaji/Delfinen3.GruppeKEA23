import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    Controller controller;

    @BeforeEach
    void setup() {
        controller = new Controller();
    }

    @Test
    void addMedlem() {
        premadeMedlemmerePassiv(); // Adder 3 medlemmere.
        int expected = 3;
        int actual = controller.getMedlemmere().size();
        assertEquals(expected, actual, 0.1);
    }

    void premadeMedlemmerePassiv() {
        controller.addMedlem("Sina", 17, true, false);
        controller.addMedlem("Jasser", 18, true, false);
        controller.addMedlem("Yusef", 61, true, false);
    }

    @Test
    void getMedlemmereIRestance() {
        controller.addMedlemToRestance("Jasser", 1);
        controller.addMedlemToRestance("sina", 2);
        controller.addMedlemToRestance("Yung mulah", 3);

        int expected = 3;
        int actual = controller.getMedlemmereIRestance().size();

        assertEquals(expected, actual);

    }

    @Test
    void addMedlemToRestance() {
        controller.addMedlemToRestance("Jasser", 1);
        controller.addMedlemToRestance("sina", 2);
        controller.addMedlemToRestance("Yung mulah", 3);
        int expected = 3;
        int actual = controller.getMedlemmereIRestance().size();

        assertEquals(expected, actual);
    }
    @Test
    void removeMedlemFromRestance() {
        controller.addMedlemToRestance("Jasser", 1);
        controller.addMedlemToRestance("sina", 2);
        controller.addMedlemToRestance("Yung mulah", 3);
        
        controller.removeMedlemFromRestance(1);
        
        int expected = 2;
        int actual = controller.getMedlemmereIRestance().size();
        
        assertEquals(expected, actual);
    }
}