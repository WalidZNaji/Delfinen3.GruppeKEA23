public class Kasserer {

    private final Controller controller;

    public Kasserer(Controller controller) {
        this.controller = controller;
    }
    public double generateIncomeOverview() {
            double totalIndkomst = 0.0;


            double ungeIndkomst = udregnUngeIndkomst();
            totalIndkomst += ungeIndkomst;



            double seniorIndkomst = udregnSeniorIndkomst();
            totalIndkomst += seniorIndkomst;


            double passivIndkomst = udregnPassivIndkomst();
            totalIndkomst += passivIndkomst;

            return totalIndkomst;
        }

        private double udregnUngeIndkomst() {
            double ungeIndkomst = 0.0;

            for (Medlem medlem : controller.getMedlemmere()) {
                if (medlem.isAktiv() && medlem.getAge() < 18) {
                    ungeIndkomst += 1000;
                }
            }

            return ungeIndkomst;
        }

        private double udregnSeniorIndkomst() {
            double seniorIndkomst = 0.0;

            for (Medlem medlem : controller.getMedlemmere()) {
                if (medlem.isAktiv() && medlem.getAge() >= 18) {
                    double basisGebyr = 1600;
                    if (medlem.getAge() > 60) {
                        basisGebyr *= 0.75;
                    }
                    seniorIndkomst += basisGebyr;
                }
            }

            return seniorIndkomst;
        }

        private double udregnPassivIndkomst() {
            double passivIndkomst = 0.0;

            for (Medlem medlem : controller.getMedlemmere()) {
                if (!medlem.isAktiv()) {
                    passivIndkomst += 500;
                }
            }

            return passivIndkomst;
        }
}

