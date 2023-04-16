import java.util.ArrayList;

public class RWH {

    private final String X1 = "0"; // Bandsymbol 0 ist 0
    private final String X2 = "1"; // Bandsymbol 1 ist 00
    private final String X3 = "_"; // Bandsymbol BLANK ist 000

    private ArrayList<ArrayList<String>> bandInput;

    private ArrayList<ArrayList<String>> translatedBandInput;

    public RWH(ArrayList<ArrayList<String>> _bandInput) {
        bandInput = _bandInput;
        translatedBandInput = new ArrayList<ArrayList<String>>();
    }

    public ArrayList<ArrayList<String>> getTranslatedBandInput() {
        return translatedBandInput;
    }

    public ArrayList<ArrayList<String>> translateFirstState() {
        for (int i = 0; i < bandInput.size(); i++) {
            ArrayList<String> smallTranslation = new ArrayList<String>();
            String startstate = bandInput.get(i).get(0);

            smallTranslation.add(0, "Q" + startstate.length());
            translatedBandInput.add(i, smallTranslation);
        }

        return translatedBandInput;
    }

    public ArrayList<ArrayList<String>> translateSecondState() {
        for (int i = 0; i < bandInput.size(); i++) {
            String startstate = bandInput.get(i).get(2);

            translatedBandInput.get(i).add(2, "Q" + startstate.length());
        }

        return translatedBandInput;
    }

    public ArrayList<ArrayList<String>> translateBandsymbol(int index) {

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

        return translatedBandInput;
    }

    public ArrayList<ArrayList<String>> translateDirection() {
        for(int i = 0; i < bandInput.size(); i++) {
            String direction = bandInput.get(i).get(4);
            
            switch(direction) {
                case "0":
                    translatedBandInput.get(i).add(4, "L");
                    break;
                case "00":
                    translatedBandInput.get(i).add(4, "R");
                    break;
                default: System.out.println("direction not found");
                    break;
            }
        }
        return translatedBandInput;
    }

}
