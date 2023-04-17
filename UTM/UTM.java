
public class UTM {
    private static Band band;
    private static RWH rwh;

    public static void main(String[] args) {
        band = new Band();
        
        rwh = new RWH(band.getTransitionList());
        System.out.println(rwh.getAndTranslateBandInput());



    }


}