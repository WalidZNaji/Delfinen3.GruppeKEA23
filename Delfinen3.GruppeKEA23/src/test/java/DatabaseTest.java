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
        controller.addMedlem("Sina", 17, 1, true, false);
        controller.addMedlem("Jasser", 18, 2, true, false);
        controller.addMedlem("Yusef", 61, 3, true, false);
    }
}