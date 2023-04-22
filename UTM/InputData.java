import java.util.ArrayList;


public class InputData {

    // Bandsymbol 0 ist 0
    String B0 = "0";
    // Bandsymbol 1 ist 00
    String B1 = "00";
    // Bandsymbol BLANK ist 000
    String BB = "000";
    // In dieser Array List sind die Informationspakete die von den 11 getrennt sind
    private ArrayList<String> transitions;
    // In dieser Array List sind die kleinen Pakete die von 1 getrennt sind
    private static ArrayList<String> smallPackages;
    private String[] splitStrings = new String[2];
    // Das ist mein Teststring um die Maschine zu testen


    /**
     * Diese Funktion kann aufgerufen werden, wenn man den eingegebenen binärcodierten String
     * auslesen und bearbeiten möchte. Die Funktion gibt eine ArrayList zurück, die alle
     * Informationspakete enthält. Diese Pakete sind wiederum in einer ArrayList gespeichert.
     * Das erste Array enthält die ganze Übergangsfunktion, das zweite Array die kleineren Teile der Übergangsfunktion.
     * 
     * @return ArrayList<ArrayList<String>>
     */
    public ArrayList<ArrayList<String>> getTransitionList() {
        //Dies wird die formatierte Übergangsfunktions ArrayList sein
        ArrayList<ArrayList<String>> formatedTransitions = new ArrayList<ArrayList<String>>();
        // Hier werden die einzelnen Übergänge aus der ArrayList ausgelesen und erneut anhand der codierung formatiert 
        // und in die formatedTransitions ArrayList gespeichert
        for (int i = 0; i < transitions.size(); i++) {
            ArrayList<String> transitionParts = new ArrayList<String>();
            transitionParts = getTransition(getTransitions().get(i));
            formatedTransitions.add(transitionParts);
        }
        // Die formatierte Übergangsfunktion wird zurückgegeben
        return formatedTransitions;
    }


    /**
     * Die Funktion nimmt die binär codierte Turingmaschine entgegen und den input für die Maschine
     * Diese funktion unterteilt den String in zwei hälften, einmal die TM und in den Input den diese verarbeiten soll
     * @param input den String der die TM ist und ab 111 unterteilt ist in den Input den sie verarbeiten soll
     * @return ein array in dem der String geteilt ist in die oben genannten Unterteileunegn
     */
    public String[] splitInput(String input) {
        int index = input.indexOf("111");
        int end = input.length();

        if(index == -1) {
            return null; // Kein Input für die Maschine vorhanden
        }

        splitStrings[0] = input.substring(0, index);
        splitStrings[1] = input.substring(index +3,end);
        return splitStrings;
    }


    /**
     * Diese Funktion liest die Übergänge aus dem String aus und speichert sie in einer ArrayList
     * 
     * @param input
     * @return ArrayList<String>
     */
    public ArrayList<String> readTransitions(String input) {
        transitions = new ArrayList<String>();

        int smallPackageStart = 0;

        for (int i = 0; i < input.length(); i++) {
            if(i == 0 && input.charAt(i) == '1') {
                smallPackageStart = 1;
                continue;
            }

            if (input.charAt(i) == '1' && input.charAt(i - 1) == '1') {
                String newPackage = input.substring(smallPackageStart, i - 1);
                transitions.add(newPackage);
                smallPackageStart = i + 1;
            }
        }
        transitions.add(input.substring(smallPackageStart, input.length()));
        return transitions;
    }

    /**
     * @return Die ArrayList mit den Übergängen
     */
    private ArrayList<String> getTransitions() {
        return transitions;
    }


    /**
     * Diese Funktion liest die kleineren Übergänge aus dem String der ArrayListe mit den grossen Übergängen aus und speichert sie in einer ArrayList
     * @param transition Nimmt die Liste mit alls Übergängen entgegen
     * @return Die ArrayList mit den kleineren Übergängen
     */
    private ArrayList<String> getTransition(String transition) {
        smallPackages = new ArrayList<String>();

        int newPackageStart = 0;

        for (int j = 1; j < transition.length(); j++) {
            if (transition.charAt(j) == '1') {
                String newSmallPackage = transition.substring(newPackageStart, j);
                smallPackages.add(newSmallPackage);
                newPackageStart = j + 1;
            }
        }
        smallPackages.add(transition.substring(newPackageStart, transition.length()));

        return smallPackages;
    }



}
