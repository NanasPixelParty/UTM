

/*
 * Diese Klasse repräsentiert das Band der TM
 */
public class Band {
    // Das ist mein Teststring um die Maschine zu testen
    private String band;
    private static final String TESTSTRING = "010010001010011000101010010110001001001010011000100010001010111101010";
    private int headPosition = 0;

    /**
     * Default Konstruktor um das Band zu testen
     */
    public Band() {
        InputData inputData = new InputData();
        inputData.splitInput(TESTSTRING);
        inputData.readTransitions(inputData.splitInput(TESTSTRING)[0]);
        band = inputData.splitInput(TESTSTRING)[1];
    }

    /**
     * Erhalte das aktuelle band
     * @return
     */
    public String getBand() {
        return this.band;
    }

    /**
     * Konstruktor um Band aus spezifischem String zu erstellen
     * @param withBand
     */
    public Band(String withBand) {
        band = withBand;
    }

    /**
     * Gebe das Band mit 15 Zeichen vor und nach dem Leseschreiblopf aus
     */
    public void printBand() {
        printBand(15);
    }

    /**
     * Sagt ob das Band zu Ende ist
     * @return true = band ist zu ende false = band ist nicht zu ende
     */
    public boolean isEnd() {
        return headPosition == band.length() - 1;
    }

    /**
     * Erstellt die optische Darstellung auf der Konsole des bandes
     * @param padding
     */
    public void printBand(int padding) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sbHead = new StringBuilder();
        sbHead.append('>');
        sb.append('|');

        int start = headPosition - padding;
        int end = start + padding*2;
        if (start < 0) {
            start = 0;
            end = start + padding;
        }

        int beforeHead = 0;
        int afterHead = 0;

        for (int i = start; i < band.length(); i++) {
            sb.append(band.charAt(i));

            if (i == headPosition) {
                sbHead.append('^');
            } else {
                sbHead.append(' ');
            }

            if(i < headPosition) {
                beforeHead++;
            }
            if(i > headPosition) {
                afterHead++;
            }

            if (i >= end) {
                break;
            }
        }
        sb.insert(1,"_".repeat(padding-beforeHead));
        System.out.println(afterHead);
        sb.append("_".repeat(padding-afterHead));
        sb.append('|');
        sbHead.insert(1," ".repeat(padding-beforeHead));
        sbHead.append(" ".repeat(padding-afterHead));
        sbHead.append('<');

        System.out.println(sb.toString());
        System.out.println(sbHead.toString());
    }

    /**
     * Ersetzt/überschreibt das aktuell auf dem band gelesene Zeichen
     * @param c
     */
    public void write(char c) {
        StringBuilder sb = new StringBuilder(band);
        sb.setCharAt(headPosition, c);
        band = sb.toString();
    }

    /**
     * Liest das aktuelle Zeichen auf dem Band dort wo sich der Lese-Schreibkopf befindet
     * @return
     */
    public char read() {
        return band.charAt(headPosition);
    }

    /**
     * Bewegt den Lese-Schreibkopf nach links
     */
    public void moveHeadLeft() {
        if (headPosition > 0) {
            headPosition--;
        }
    }

    /**
     * Bewegt den Leseschreibkopf nach rechts
     */
    public void moveHeadRight() {
        if (headPosition < band.length() - 1) {
            headPosition++;
        }
    }


    //Das ist nur zum Testen, kann sonst ignoriert werden. das Prograamm wird in der Klasse UTM.java gestartet
    public static void main(String[] args) {
        Band tb = new Band();
        tb.printBand();

        for (int i = 0; i < 60; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            tb.moveHeadRight();
            tb.printBand();
        }
    }
}
