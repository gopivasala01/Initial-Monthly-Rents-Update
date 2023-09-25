package PDFDataExtract;

import java.io.File;
import java.io.FileInputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import mainPackage.CommonMethods;
import mainPackage.PDFReader;
import mainPackage.RunnerClass;

public class Alabama_Format2 
{
	public static String text ="";
	public static  boolean  format2() throws Exception
	//public static void main(String[] args) throws Exception 
	{
		//File file = new File("C:\\Gopi\\Projects\\Property ware\\Lease Close Outs\\PDFS\\Tennessee\\Format 2\\Lease_031.22_05.23_1327_Everwood_Dr_Ashland_C_(1).pdf");
		/*
		File file = CommonMethods.getLastModified();
		FileInputStream fis = new FileInputStream(file);
		PDDocument document = PDDocument.load(fis);
	    text = new PDFTextStripper().getText(document);
		 */
		text = PDFReader.text;
	    text = text.replaceAll(System.lineSeparator(), " ");
	    text = text.trim().replaceAll(" +", " ");
	    text = text.toLowerCase();
	    //System.out.println(text);
	    //System.out.println("------------------------------------------------------------------");
	    
	    try
	    {
	    	String commensementRaw = text.substring(text.indexOf(PDFAppConfig.Alabama_Format2.commensementDate_Prior)+PDFAppConfig.Alabama_Format2.commensementDate_Prior.length()+1).trim();//,text.indexOf(PDFAppConfig.Alabama_Format2.commensementDate_After)).trim();
	    	 PDFReader.commencementDate = commensementRaw.substring(0, commensementRaw.indexOf('(')).trim();
	    	 PDFReader.commencementDate = PDFReader.commencementDate.trim().replaceAll(" +", " ");
		    System.out.println("Commensement Date = "+PDFReader.commencementDate);//.substring(commensementDate.lastIndexOf(":")+1));
	    }
	    catch(Exception e)
	    {
	    	PDFReader.commencementDate = "Error";
	    	e.printStackTrace();
	    }
	    
	    try
	    {
	    	String expirationDateRaw  = text.substring(text.indexOf(PDFAppConfig.Alabama_Format2.expirationDate_Prior)+PDFAppConfig.Alabama_Format2.expirationDate_Prior.length()).trim();//,text.indexOf(PDFAppConfig.Alabama_Format2.expirationDate_After)).trim();
	    	PDFReader.expirationDate = expirationDateRaw.substring(0,expirationDateRaw.indexOf('(')).trim();
	    	PDFReader.expirationDate = PDFReader.expirationDate.trim().replaceAll(" +", " ");
	    	System.out.println("Expiration Date = "+PDFReader.expirationDate);//.substring(commensementDate.lastIndexOf(":")+1));
	    }
	    catch(Exception e)
	    {
	    	PDFReader.expirationDate = "Error";
	    	e.printStackTrace();
	    }
	    
	    try
	    {
	    	PDFReader.monthlyRent = text.substring(text.indexOf(PDFAppConfig.Alabama_Format2.monthlyRent_Prior)+PDFAppConfig.Alabama_Format2.monthlyRent_Prior.length()).split(" ")[0].trim();
	    	if(!PDFReader.monthlyRent.contains("."))
	    		PDFReader.monthlyRent = text.substring(text.indexOf(PDFAppConfig.Alabama_Format2.monthlyRent_Prior2)+PDFAppConfig.Alabama_Format2.monthlyRent_Prior2.length()).split(" ")[0].trim();
	    	PDFReader.monthlyRent = PDFReader.monthlyRent.replaceAll("[^0-9.]", "");
	    }
	    
	    catch(Exception e)
	    {
	    	PDFReader.monthlyRent = "Error";
	    	e.printStackTrace();
	    }
	    System.out.println("Monthly Rent = "+PDFReader.monthlyRent);
	    
	    try
	    {
	    	PDFReader.monthlyRentTaxAmount = text.substring(text.indexOf(PDFAppConfig.Alabama_Format2.monthlyRentTaxAmount)+PDFAppConfig.Alabama_Format2.monthlyRentTaxAmount.length()).split(" ")[0].trim();
	    	if(PDFReader.monthlyRentTaxAmount.trim().equalsIgnoreCase("0.00")||PDFReader.monthlyRentTaxAmount.trim().equalsIgnoreCase("N/A")||PDFReader.monthlyRentTaxAmount.trim().equalsIgnoreCase("n/a")||PDFReader.monthlyRentTaxAmount.trim().equalsIgnoreCase("na")||PDFReader.monthlyRentTaxAmount.trim().equalsIgnoreCase("")||PDFReader.monthlyRentTaxAmount.trim().equalsIgnoreCase(".*[a-zA-Z]+.*"))
	    	{
	    		PDFReader.monthlyRentTaxFlag = false;
	    	}
	    	else
	    	{
	    		PDFReader.monthlyRentTaxFlag = true;
	    		PDFReader.totalMonthlyRentWithTax = text.substring(text.indexOf(PDFAppConfig.Alabama_Format2.totalMonthlyRent2)+PDFAppConfig.Alabama_Format2.totalMonthlyRent2.length()).split(" ")[0].trim();
	    		
	    	}
	    }
	    catch(Exception e)
	    {
	    	PDFReader.monthlyRentTaxFlag = false;
	    	PDFReader.monthlyRentTaxAmount = "";
	    }
	    if (PDFReader.monthlyRentTaxFlag == true && !PDFReader.totalMonthlyRentWithTax.matches(".*[a-zA-Z]+.*")) 
	    {
	        PDFReader.monthlyRent = PDFReader.totalMonthlyRentWithTax;
	        System.out.println("Monthly Rent = " + PDFReader.monthlyRent);
	        System.out.println("Monthly Rent Tax Amount = " + PDFReader.monthlyRentTaxAmount);
	        System.out.println("Monthly Rent Tax Flag = " + PDFReader.monthlyRentTaxFlag);
	        System.out.println("Total Monthly Rent With Tax = " + PDFReader.totalMonthlyRentWithTax);
	    } else 
	    {
	        PDFReader.monthlyRent = PDFReader.monthlyRent.replaceAll("[^0-9.]", "");
	    }

	    if(text.contains(PDFAppConfig.Alabama_Format2.petAgreementAvailabilityCheck)==true)
	    	PDFReader.petFlag =  true;
	    else if(PDFReader.petFlag = text.contains(PDFAppConfig.Alabama_Format2.petAgreementAvailabilityCheck2)==true)
	    	PDFReader.petFlag =  true;
	    else PDFReader.petFlag =  false;
	    
	    System.out.println("Pet Addendum Available = "+PDFReader.petFlag);
	    if(PDFReader.petFlag ==true)
	    {
	    	try
    		{
    			PDFReader.petRent = text.substring(text.indexOf(PDFAppConfig.Alabama_Format2.petRent_Prior)+PDFAppConfig.Alabama_Format2.petRent_Prior.length()).split(" ")[0].trim();
				 //System.out.println("Pet rent = "+PDFReader.petRent.trim());
				 if(CommonMethods.onlyDigits(PDFReader.petRent)==false)
				    {
				    	 PDFReader.petRent = text.substring(text.indexOf(PDFAppConfig.Alabama_Format2.petRent_Prior2)+PDFAppConfig.Alabama_Format2.petRent_Prior2.length()).trim().split(" ")[0];
				    }
				 if(PDFReader.petRent.matches(".*[a-zA-Z]+.*"))
				    {
				    	PDFReader.petRent = "Error";
				    }
    		}
    		
    		catch(Exception e1)
		    {
		    	PDFReader.petRent = "Error";  
		    	e1.printStackTrace();
		    }
	    	System.out.println("Pet rent= "+PDFReader.petRent.trim());
	    
	    }
		return true;
	}

}
