
public class UTM {

    public static StateMachine sm;
    private static final String TESTSTRING = "010010001010011000101010010110001001001010011000100010001010111____";
    private static final String TESTSTRING2 = "10100101001001101010001010011000100101001001100010100101001111010010100100110101000101001100010010100100110001010010100";
    //TM zum Multiplizieren
    private static final String MUL ="010010001000100110101000000000001000100110000000000010010000000000010001001100000000000100010010001001100010010001001001100010100001010011000010010000010001001100000100100000100100110000010001000000100010011000000100100000010010011000000100010000000100101100000001001000000010010110000000100010000000010001011000000001001000000001001011000000001000100001001001100001000100000000010001011000000000100100000000010010110000000001010000000000101011000000000010010000000000100101100000000001000101000100111111111101111111";


    public static void main(String[] args) {
        //Hier einfach String einfügen um jeweile TM inkls. Input zu starten
        sm = new StateMachine(MUL);

        try {
            sm.printMachineState();
            while(!sm.step()) {
                sm.printMachineState();
                //Das ist quasi der "Step-Mode"
                Thread.sleep(100);
            }
        } catch (StateMachienException | InterruptedException e) {
            sm.printMachineState();
            e.printStackTrace();
        }
        sm.printMachineState();
        System.out.println("Number of 1s: "+sm.countCharacters('1'));




    }


}