import java.util.Scanner;

public class UTM {

    public static StateMachine sm;
    private static final String TESTSTRING = "010010001010011000101010010110001001001010011000100010001010111";
    private static final String TESTSTRING2 = "10100101001001101010001010011000100101001001100010100101001111010010100100110101000101001100010010100100110001010010100111";
    // TM zum Multiplizieren
    private static String inputBand;
    private static String finalMachine;
    private static final String MUL = "010010001000100110101000000000001000100110000000000010010000000000010001001100000000000100010010001001100010010001001001100010100001010011000010010000010001001100000100100000100100110000010001000000100010011000000100100000010010011000000100010000000100101100000001001000000010010110000000100010000000010001011000000001001000000001001011000000001000100001001001100001000100000000010001011000000000100100000000010010110000000001010000000000101011000000000010010000000000100101100000000001000101000100111";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wähle eine Turingmaschine: 1, 2 oder MUL");
        String selectedString = scanner.nextLine();
        String choice = chooseMaschine(selectedString);

        if(choice.equals(TESTSTRING) || choice.equals(TESTSTRING2)) {
            System.out.println("Gib dein eingabeBand binär an.");
            inputBand = scanner.nextLine();
            finalMachine = choice + inputBand;
        } else {
            System.out.println("Gib eine Multiplikation ein.");
            inputBand = scanner.nextLine();
            finalMachine = choice + createMULinput(inputBand);
        }
        System.out.println("Gibt S für Step-Mode und D für direktes Ergebnis ein:");
        String choosenMode = scanner.nextLine();

        boolean isStepMode = choosenMode.equalsIgnoreCase("S");
        // Hier einfach String einfügen um jeweile TM inkls. Input zu starten
        sm = new StateMachine(finalMachine);

        try {
            sm.printMachineState();
            while (!sm.step()) {
                if(isStepMode) {
                    sm.printMachineState();
                    // Das ist quasi der "Step-Mode")
                    Thread.sleep(100);
                    scanner.nextLine();
                }
            }
        } catch (StateMachienException | InterruptedException e) {
            sm.printMachineState();
            e.printStackTrace();
        }
        sm.printMachineState();
        System.out.println("Number of 1s: " + sm.countCharacters('1'));
        scanner.close();
        System.exit(0);
    }

    public static String chooseMaschine(String choice) {
        switch (choice) {
            case "1":
                return TESTSTRING;
            case "2":
                return TESTSTRING2;
            case "MUL":
                return MUL;
            default:
                return null;

        }
    }

    public static String numberToOne(int number) {
        String one = "";
        for (int i = 0; i < number; i++) {
            one += "1";
        }
        return one;
    }

    public static String createMULinput(String input) {
        String[] numbers = input.split("x");
        int a = Integer.parseInt(numbers[0].trim());
        int b = Integer.parseInt(numbers[1].trim());
        String oneA = numberToOne(a);
        String oneB = numberToOne(b);
        return oneA + "0" + oneB;
    }

}