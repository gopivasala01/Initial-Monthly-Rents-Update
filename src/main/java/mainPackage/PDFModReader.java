package mainPackage;

public class PDFModReader 
{
	public static String monthlyRent = "";
    public static String currentRentCharge = "Your current rent charge is".toLowerCase();
    public static String Mod_Format = "This Amendment of Lease Agreement";

    public static void PDFModReader1(String text, boolean checkLeaseAgreementAvailable) 
    {
        if (checkLeaseAgreementAvailable) {
            String pdfMod_Format = Mod_Format;
            System.out.println("PDF Format Type = " + pdfMod_Format);

            String monthlyRent = extractMonthlyRent(text, currentRentCharge);
            monthlyRent = monthlyRent.replaceAll("[^0-9.]", "");
        } else {
            System.out.println("Lease agreement not available. Unable to extract monthly rent.");
        }
    }

    private static String extractMonthlyRent(String text, String pattern) {
        String[] parts = text.substring(text.indexOf(pattern) + pattern.length()).split(" ");
        String extractedRent = parts[0].trim();
        if (!extractedRent.contains(".")) {
            extractedRent = text.substring(text.indexOf(pattern) + pattern.length()).split(" ")[0].trim();
        }
        return extractedRent;
    }

}
