import java.util.ArrayList;
import java.util.Map;

public class InputTranslator {

    private final String X1 = "0"; // Bandsymbol 0 ist 0
    private final String X2 = "1"; // Bandsymbol 1 ist 00
    private final String X3 = "_"; // Bandsymbol BLANK ist 000

    private final String D1 = "L"; // 0 codiert die Richtung des Lese-Schreibekopf nach Links
    private final String D2 = "R"; // 00 codiert die Richtung des Lese-Schreibekopf nach Rechts

    private ArrayList<ArrayList<String>> bandInput; // Hier liegt die binärcodierte Eingabe der TM die ausgeführt werden soll

    private ArrayList<ArrayList<String>> translatedBandInput; // Hier liegt die übersetzte Eingabe der TM die ausgeführt werden soll

    /**
     * Wir geben dem Konstruktor das Array der binärcodierten Transitions die übersetzt werden sollen
     * @param _bandInput binärcodierte Übergangsberechnungen
     */
    public InputTranslator(ArrayList<ArrayList<String>> _bandInput) {
        bandInput = _bandInput;
        translatedBandInput = new ArrayList<ArrayList<String>>();
    }

    /**
     * Diese Funktion gibt die Übersetzung das übersetzte Band zurück
     * @return Übersetzung des Bandes
     */
    public ArrayList<ArrayList<String>> getAndTranslateBandInput() {
        translateFirstState();
        translateBandsymbol(1);
        translateSecondState();
        translateBandsymbol(3);
        translateDirection();

        return translatedBandInput;
    }

    public int getNumberOfTransitions() {
        return translatedBandInput.size();
    }

    public String getState(int out, int in) {
        return ""+translatedBandInput.get(out).get(in);
    }


    /**
     * Hier wird der Startzustand übersetzt und alle weiteren Zustände,
     * indem wir die ersten Fächer der jeweiligen Arrays durchgehen und die Länge des Strings
     * der jeweiligen Nullen an das Q anhängen
     */
    private void translateFirstState() {
        for (int i = 0; i < bandInput.size(); i++) {
            ArrayList<String> smallTranslation = new ArrayList<String>();
            String startstate = bandInput.get(i).get(0);

            smallTranslation.add(0, "Q" + startstate.length());
            translatedBandInput.add(i, smallTranslation);
        }
    }

    /**
     * Hier wird der zweite Zustand einer Übergangsfunktion übersetzt
     * in dem wir in jedem Kästchen des ersten arrays das dritte Kästchen des inneren Arrays auslesen
     * und die dort enthaltenen Nullen zählen und die Länge des Strings an das Q anhängen
     */
    private void translateSecondState() {
        for (int i = 0; i < bandInput.size(); i++) {
            String startstate = bandInput.get(i).get(2);

            translatedBandInput.get(i).add(2, "Q" + startstate.length());
        }
    }

    /**
     * Hier wird ein Bandsymbol übersetzt
     * Wenn wir das erste Symbol der Transition auslesen wollen, üssen wir in jedem Kästchen des
     * äusseren Arrays das zweite Kästchen des inneren Arrays auslesen und
     * das vierte Kästchen des inneren Arrays auslesen 
     * @param index entweder 1 (erstes Bandsymbol) oder 3 (zweites Bandsymbol)
     */
    private void translateBandsymbol(int index) {

        for (int i = 0; i < bandInput.size(); i++) {
            String bandsymbol = bandInput.get(i).get(index);

            switch (bandsymbol) {
                case "0":
                    translatedBandInput.get(i).add(index, X1);
                    break;
                case "00":
                    translatedBandInput.get(i).add(index, X2);
                    break;
                case "000":
                    translatedBandInput.get(i).add(index, X3);
                    break;
                default:
                    translatedBandInput.get(i).add(index, "X" + bandsymbol.length());
            }

        }
    }

    /**
     * Hier wird die Richtung der Übergangsfunktion übersetzt
     * Diese befindet sich in jeder Transition im fünften Kästchen des inneren Arrays
     */
    private void translateDirection() {
        for(int i = 0; i < bandInput.size(); i++) {
            String direction = bandInput.get(i).get(4);
            
            switch(direction) {
                case "0":
                    translatedBandInput.get(i).add(4, D1);
                    break;
                case "00":
                    translatedBandInput.get(i).add(4, D2);
                    break;
                default: System.out.println("direction not found");
                    break;
            }
        }
    }

}
