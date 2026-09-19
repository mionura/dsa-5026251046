public class ColourPrint extends PrintJob {
    public ColourPrint(String id, int pages) {
        super(id, pages);
    }

    @Override
    public int calculateCharge() {
        int pages = getPages();
        int totalCharge = 0;

        if (pages <= 10) {
            totalCharge = pages * 1500;
        } else {
            totalCharge = (10 * 1500) + ((pages - 10) * 1000);
        }

        return totalCharge + 2000; 
    } 

    @Override
    public String label() {
        return "Colour";
    }
    
}
