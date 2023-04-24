
public class UTM {

    public static StateMachine sm;
    private static final String TESTSTRING = "10100101001001101010001010011000100101001001100010100101001111010";
    private static final String TESTSTRING2 = "01001000101001100010101001011000100100101001100010001000101011111";
    //TM zum Multiplizieren
    private static final String MUL ="01010001010110001001000100101100010100101001101001000010100110000100100001001001100001010000010100110000010010000001010011000000100100000010010011000000101000000010100110000000100100000001001001100000001010000000010010110000000010010000000010010110000000010100000000010101100000000010100000100100110000000001001000000000100101100000101000000000010100110000000000100100000000001001011000000000010100000000000101011000000000001001000000000001001011000000000001010100100111111011111";


    public static void main(String[] args) {
        //Hier einfach String einfügen um jeweile TM inkls. Input zu starten
        sm = new StateMachine(TESTSTRING2);

        try {
            sm.printMachieneState();
            while(!sm.step()) {
                sm.printMachieneState();
                //Das ist quasi der "Step-Mode"
                Thread.sleep(300);
            }
        } catch (StateMachienException | InterruptedException e) {
            sm.printMachieneState();
            e.printStackTrace();
        }
        sm.printMachieneState();




    }


}