package mainPackage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PDFModReader {
    public static String monthlyRent = "";
    public static String currentRentCharge = "Your current rent charge is $".toLowerCase();
    public static String Mod_Format = "This Amendment of Lease Agreement";

    public static void PDFModReader1(boolean checkModLeaseAgreementAvailable) throws Exception {
        if (checkModLeaseAgreementAvailable) {
            String pdfMod_Format = Mod_Format;
            System.out.println("PDF Format Type = " + pdfMod_Format);

            try {
                File file = CommonMethods.getLastModified();
                System.out.println(file);

                FileInputStream fis = new FileInputStream(file);
                PDDocument document = PDDocument.load(fis);
                String text = new PDFTextStripper().getText(document);
                document.close();

                
                text = text.replaceAll(System.lineSeparator(), " ");
                text = text.replaceAll(" +", " ");
                text = text.toLowerCase();

                
                String[] rent = text.substring(text.indexOf(currentRentCharge) + currentRentCharge.length()).split(" ");
                String extractedRent = rent[0].trim();
                if (!extractedRent.contains(".")) {
                    extractedRent = text.substring(text.indexOf(currentRentCharge) + currentRentCharge.length()).split(" ")[0].trim();
                }
                monthlyRent = extractedRent.replaceAll("[^0-9.]", "");
                if (monthlyRent == null)
                {
                	monthlyRent = PDFReader.monthlyRent;
                }

                System.out.println("Monthly Rent ="+ monthlyRent.trim());
                

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
           //System.out.println("Lease agreement not available. Unable to extract monthly rent.");
        }
    }
}
