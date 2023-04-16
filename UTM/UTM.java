
public class UTM {
    private static Band band;
    private static RWH rwh;

    private static final int firstBandsymbolIndex = 1;
    private static final int secondBandsymbolIndex = 3;

    public static void main(String[] args) {
        band = new Band();
        
        rwh = new RWH(band.getTransitionList());
        rwh.translateFirstState();
        System.out.println(rwh.getTranslatedBandInput().toString());
        rwh.translateBandsymbol(firstBandsymbolIndex);
        System.out.println(rwh.getTranslatedBandInput().toString());
        rwh.translateSecondState();
        System.out.println(rwh.getTranslatedBandInput().toString());
        rwh.translateBandsymbol(secondBandsymbolIndex);
        System.out.println(rwh.getTranslatedBandInput().toString());
        rwh.translateDirection();
        System.out.println(rwh.getTranslatedBandInput().toString());



    }


}