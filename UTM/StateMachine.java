import java.util.ArrayList;
import java.util.HashMap;


public class StateMachine {
    private Band band;
    private HashMap<String,String> transitionMap;

    private String state = "Q1";
    private final String acceptedState = "Q2";
    private int step = 0;

    //Hier bauen wir quasi unsere State Machine zusammen um später durch die verschiedenen Zustände iterieren zu können,
    // die wir aus dem Bandinput lesen. SplittetInput 0 enthält die eingegebene TM und SplittetInput 1 den String der als 
    //Eingabe auf das Band geschrieben wird
    public StateMachine(String fullBand) {
        //Eingegebene binär codierte TM inkls. InputString
        InputData bandInput = new InputData();
        // Eingabe wird zwei geteilt in TM und Input
        String[] splittedInput = bandInput.splitInput(fullBand);
        //Erstellt das Band auf dem die TM arbeitet mit input String
        this.band = new Band(splittedInput[1]);
        //Die Übergangsfunktionen werden aus der binär codierten TM ersttellt
        bandInput.readTransitions(splittedInput[0]);
        //hier wird das binäre in Menschlich verständlich notierte übergangsfunktionen übersetzt
        InputTranslator it = new InputTranslator(bandInput.getTransitionList());
        //hier werden die Übergnagsfunktionen aus einer ArrayList in eine HashMap übersetzt
        convertArrayListToHashMap(it.getAndTranslateBandInput());
    }

    /**
     * @param translatedTrasitions das ist ein zweidimensionales array das alle übergangsfunktionen enthält
     * @return die HashMap in diesem Format: {Q3_1=Q2,0,R, Q3_0=Q1,1,L, Q1_1=Q3,0,R, Q3__=Q3,0,L}
     */
    public HashMap<String, String> convertArrayListToHashMap(ArrayList<ArrayList<String>> translatedTrasitions) {
        transitionMap = new HashMap<>();
        for(ArrayList<String> entry : translatedTrasitions) {
            System.out.println(entry);
            String key = entry.get(0) + "_" + entry.get(1);
            String value = entry.get(2) + "," + entry.get(3) + "," + entry.get(4);
            transitionMap.put(key, value);

        }
        return transitionMap;
        
    } 

    /**
     * Hier werden die Zustände anhand des Bandes und der transitionMap umgesetzt
     * @param c das ist der charakter den wir vom Band lesen
     * @throws StateMachienException
     */
    public void doTransition(char c) throws StateMachienException {
        if(state == acceptedState){
            System.out.println("Akzeptierter Zustand erreicht.");
            return;
        }

        String transition = transitionMap.get(state + "_" + c);
         
        if(transition == null) {
            System.out.println("Error");
            throw new StateMachienException("No transition available");
        } 
        
        String[] actions = transition.split(",");

        this.state = actions[0];
        this.band.write(actions[1].charAt(0));

        switch(actions[2]) {
            case "L":
                this.band.moveHeadLeft();
                break;
            case "R":
                this.band.moveHeadRight();
                break;
            default:
                System.err.println("hä?");
                throw new StateMachienException("Do not know where to move head");
        }
    }

    /**
     * Hier geben wir aus in welchem Zustand wir uns aktuell befinden und wie viele Steps wir bisher gemacht haben
     */
    public void printMachineState() {
        System.out.println("Current State: "+this.state);
        System.out.println("Current Step: "+this.step);
        this.band.printBand(15);
    }

    public int countCharacters(char c) {
        /*int count = 0;
        for(int i = 0; i < this.band.getBand().length(); i++) {
            if(this.band.getBand().charAt(i) == c) {
                count++;
            }
        }
        return count;*/
        return (int)this.band.getBand().chars().filter(lc -> lc == (int)c).count();
    }

    /**
     * 
     * @return true wenn das Band zu ende ist
     * @throws StateMachienException
     */
    public boolean step() throws StateMachienException {
        step++;
        doTransition(band.read());

        return this.state.equals(acceptedState);
    }
}
