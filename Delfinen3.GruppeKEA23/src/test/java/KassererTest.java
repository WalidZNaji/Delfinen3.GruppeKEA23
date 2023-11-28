import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KassererTest {

    Controller controller;

    @BeforeEach
    void setup() {
        controller = new Controller();
    }


@Test
    void generateIncomeOverviewJuniorTest() {
        premadeMedlemmereJunior();
        double expected = controller.udregnIndkomst();
        double actual = 3000.0; // 1000 * 3
        assertEquals(expected, actual, 0.1);
    }
    @Test
    void generateIncomeOverviewSeniorRabatTest() {
        premadeMedlemmereSeniorRabat();
        double expected = controller.udregnIndkomst();
        double actual = 3600.0; // (1600 * 3) * 0,75
        assertEquals(expected, actual, 0.1);
    }
    @Test
    void generateIncomeOverviewSeniorTest() {
        premadeMedlemmereSenior();
        double expected = controller.udregnIndkomst();
        double actual = 4800.0; // 1600 * 3
        assertEquals(expected, actual, 0.1);
    }
    @Test
    void generateIncomeOverviewPassivTest() {
        premadeMedlemmerePassiv();
        double expected = controller.udregnIndkomst();
        double actual = 1500.0; // 500 * 3
        assertEquals(expected, actual, 0.1);
    }

    void premadeMedlemmereJunior() {
            controller.addMedlem("Sina", 17, 1, true, true);
            controller.addMedlem("Jasser", 17, 2, true, true);
            controller.addMedlem("Yusef", 17, 3, true, true);
    }
    void premadeMedlemmereSeniorRabat() {
            controller.addMedlem("Sina", 61, 1, true, true);
            controller.addMedlem("Jasser", 61, 2, true, true);
            controller.addMedlem("Yusef", 61, 3, true, true);
    }
    void premadeMedlemmereSenior() {
        controller.addMedlem("Sina", 18, 1, true, true);
        controller.addMedlem("Jasser", 18, 2, true, true);
        controller.addMedlem("Yusef", 18, 3, true, true);
    }
    void premadeMedlemmerePassiv() {
        controller.addMedlem("Sina", 17, 1, true, false);
        controller.addMedlem("Jasser", 18, 2, true, false);
        controller.addMedlem("Yusef", 61, 3, true, false);
    }
}