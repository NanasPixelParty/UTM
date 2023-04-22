import java.util.ArrayList;
import java.util.HashMap;

import javax.management.RuntimeErrorException;

public class StateMachine {
    private Band band;
    private HashMap<String,String> transitionMap;

    private String state = "Q1";
    private String acceptedState = "Q2";
    private int step = 0;

    public StateMachine(String fullBand) {
        InputData bandInput = new InputData();
        String[] splittedInout = bandInput.splitInput(fullBand);
        this.band = new Band(splittedInout[1]);
        bandInput.readTransitions(splittedInout[0]);
        System.out.println(bandInput.getTransitionList());
        InputTranslator it = new InputTranslator(bandInput.getTransitionList());
        convertArrayListToHashMap(it.getAndTranslateBandInput());
    }

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
        }
    }

    public void printMachieneState() {
        System.out.println("Current State: "+this.state);
        System.out.println("Current Step: "+this.step);
        this.band.printBand();
    }

    /**
     * 
     * @return true if the band is at its enda
     * @throws StateMachienException
     */
    public boolean step() throws StateMachienException {
        step++;
        doTransition(band.read());

        return this.state.equals("Q2");
    }
}
