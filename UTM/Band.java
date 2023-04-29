

/*
 * Diese Klasse repräsentiert das Band der TM
 */

import javax.imageio.spi.RegisterableService;

public class Band {
    private String band;
    private int headPosition = 0;

    /**
     * Konstruktor um Band aus spezifischem String zu erstellen
     * @param withBand
     */
    public Band(String withBand) {
        band = withBand;
    }

    /**
     * Erhalte das aktuelle band
     * @return
     */
    public String getBand() {
        return this.band;
    }

    /**
     * Gebe das Band mit 15 Zeichen vor und nach dem Leseschreiblopf aus
     */
    public void printBand() {
        printBand(15);
    }

    /**
     * Erstellt die optische Darstellung auf der Konsole des bandes
     * @param padding
     */
    public void printBand(int padding) {
        StringBuilder sbBand = new StringBuilder();
        StringBuilder sbHead = new StringBuilder();
        String localBand = String.format("%s%s%s", "_".repeat(padding),band,"_".repeat(padding));
        sbHead.append('>');
        sbBand.append('|');


        int start = headPosition;
        int end = start + padding*2;

        for (int i = start; i < localBand.length(); i++) {
            sbBand.append(localBand.charAt(i));

            if (i == headPosition+padding) {
                sbHead.append('^');
            } else {
                sbHead.append(' ');
            }

            if (i >= end) {
                break;
            }
        }
        sbBand.append('|');
        sbHead.append('<');

        System.out.println(sbBand.toString());
        System.out.println(sbHead.toString());
    }

    public void printBandRaw() {
        System.out.println(this.band);
    }

    /**
     * Ersetzt/überschreibt das aktuell auf dem band gelesene Zeichen
     * @param c
     */
    public void write(char c) {
        StringBuilder sb = new StringBuilder(band);
        if(headPosition >= band.length()) {
            sb.append(c);
        } else {
            sb.setCharAt(headPosition, c);
        }
        band = sb.toString();
    }

    /**
     * Liest das aktuelle Zeichen auf dem Band dort wo sich der Lese-Schreibkopf befindet
     * @return
     */
    public char read() {
        if(headPosition >= band.length()) {
            return '_';
        }

        return band.charAt(headPosition);
    }

    /**
     * Bewegt den Lese-Schreibkopf nach links
     */
    public void moveHeadLeft() {
        headPosition--;
    }

    /**
     * Bewegt den Leseschreibkopf nach rechts
     */
    public void moveHeadRight() {
        headPosition++;
    }
}
