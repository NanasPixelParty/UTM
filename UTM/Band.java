
public class Band {
    // Das ist mein Teststring um die Maschine zu testen
    private String band;
    private static final String TESTSTRING = "010010001010011000101010010110001001001010011000100010001010111101010";
    private int headPosition = 0;

    public Band() {
        InputData inputData = new InputData();
        inputData.splitInput(TESTSTRING);
        inputData.readTransitions(inputData.splitInput(TESTSTRING)[0]);
        band = inputData.splitInput(TESTSTRING)[1];
    }

    public String getBand() {
        return this.band;
    }

    public Band(String withBand) {
        band = withBand;
    }

    public void printBand() {
        printBand(15);
    }

    public boolean isEnd() {
        return headPosition == band.length() - 1;
    }

    public void printBand(int padding) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sbHead = new StringBuilder();
        sbHead.append('>');
        sb.append('|');

        int start = headPosition - padding;
        if (start < 0) {
            start = 0;
        }

        int beforeHead = 0;
        int afterHead = 0;

        for (int i = start; i < band.length(); i++) {
            sb.append(band.charAt(i));

            if (i == headPosition) {
                sbHead.append('^');
            } else {
                sbHead.append(' ');
            }

            if(i < headPosition) {
                beforeHead++;
            }
            if(i > headPosition) {
                afterHead++;
            }

            if (i - start >= 2 * padding) {
                break;
            }
        }
        sb.insert(1,"_".repeat(padding-beforeHead));
        sb.append("_".repeat(padding-afterHead));
        sb.append('|');
        sbHead.insert(1," ".repeat(padding-beforeHead));
        sbHead.append(" ".repeat(padding-afterHead));
        sbHead.append('<');

        System.out.println(sb.toString());
        System.out.println(sbHead.toString());
    }

    public void write(char c) {
        StringBuilder sb = new StringBuilder(band);
        sb.setCharAt(headPosition, c);
        band = sb.toString();
    }

    public char read() {
        return band.charAt(headPosition);
    }

    public void moveHeadLeft() {
        if (headPosition > 0) {
            headPosition--;
        }
    }

    public void moveHeadRight() {
        if (headPosition < band.length() - 1) {
            headPosition++;
        }
    }

    public static void main(String[] args) {
        Band tb = new Band();
        tb.printBand();

        for (int i = 0; i < 60; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            tb.moveHeadRight();
            tb.printBand();
        }
    }
}
