
public class UTM {
    private static Band band;
    public static StateMachine sm;
    private static final String TESTSTRING = "10100101001001101010001010011000100101001001100010100101001111010";
    private static final String TESTSTRING2 = "01001000101001100010101001011000100100101001100010001000101011111";

    public static void main(String[] args) {
        sm = new StateMachine(TESTSTRING2);

        try {
            sm.printMachieneState();
            while(!sm.step()) {
                sm.printMachieneState();
                Thread.sleep(300);
            }
        } catch (StateMachienException | InterruptedException e) {
            sm.printMachieneState();
            e.printStackTrace();
        }
        sm.printMachieneState();




    }


}