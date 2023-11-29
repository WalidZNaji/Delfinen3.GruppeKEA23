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
        double expected = 3000.0; // 1000 * 3
        double actual = controller.udregnIndkomst(); // 1000 * 3
        assertEquals(expected, actual, 0.1);
    }
    @Test
    void generateIncomeOverviewSeniorRabatTest() {
        premadeMedlemmereSeniorRabat();
        double expected = 3600.0; // (1600 * 3) * 0,75
        double actual = controller.udregnIndkomst();
        assertEquals(expected, actual, 0.1);
    }
    @Test
    void generateIncomeOverviewSeniorTest() {
        premadeMedlemmereSenior();
        double expected = 4800.0; // 1600 * 3
        double actual = controller.udregnIndkomst();
        assertEquals(expected, actual, 0.1);
    }
    @Test
    void generateIncomeOverviewPassivTest() {
        premadeMedlemmerePassiv();
        double expected = 1500.0; // 500 * 3
        double actual = controller.udregnIndkomst();
        assertEquals(expected, actual, 0.1);
    }

    void premadeMedlemmereJunior() {
        // Under 18 && aktiv = 1000kr.
            controller.addMedlem("Sina", 17, 1, true, true);
            controller.addMedlem("Jasser", 17, 2, true, true);
            controller.addMedlem("Yusef", 17, 3, true, true);
    }
    void premadeMedlemmereSeniorRabat() {
        // Over 60 && aktiv = 1600kr + 25% rabat.
            controller.addMedlem("Sina", 61, 1, true, true);
            controller.addMedlem("Jasser", 61, 2, true, true);
            controller.addMedlem("Yusef", 61, 3, true, true);
    }
    void premadeMedlemmereSenior() {
        // >= 18 && aktiv = 1600kr
            controller.addMedlem("Sina", 18, 1, true, true);
            controller.addMedlem("Jasser", 18, 2, true, true);
            controller.addMedlem("Yusef", 18, 3, true, true);
    }
    void premadeMedlemmerePassiv() {
        // Passivt medlemsskab uanset alder = 500kr
            controller.addMedlem("Sina", 17, 1, true, false);
            controller.addMedlem("Jasser", 18, 2, true, false);
            controller.addMedlem("Yusef", 61, 3, true, false);
    }
}