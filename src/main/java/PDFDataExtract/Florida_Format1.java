package PDFDataExtract;

import mainPackage.CommonMethods;
import mainPackage.PDFReader;

public class Florida_Format1 
{
	public static String text="";
	public static boolean format1() throws Exception
	//public static void main(String args[]) throws Exception
	{
		/*
		File file = CommonMethods.getLastModified();
		//File file = new File("C:\\SantoshMurthyP\\Lease Audit Automation\\Lease_02.22_02.23_200_Doc_Johns_Dr_ATX_Smith (3).pdf");
		FileInputStream fis = new FileInputStream(file);
		PDDocument document = PDDocument.load(fis);
	    text = new PDFTextStripper().getText(document);
	    */
		text = PDFReader.text;
	    text = text.replaceAll(System.lineSeparator(), " ");
	    text = text.trim().replaceAll(" +", " ");
	    text = text.toLowerCase();
	    //System.out.println(text);
	    System.out.println("------------------------------------------------------------------");
	    try
	    {
	    	PDFReader.commencementDate = text.substring(text.indexOf(PDFAppConfig.Florida_Format1.AB_commencementDate_Prior)+PDFAppConfig.Florida_Format1.AB_commencementDate_Prior.length());
	    	PDFReader.commencementDate = PDFReader.commencementDate.substring(0, PDFReader.commencementDate.indexOf(PDFAppConfig.Florida_Format1.AB_commencementDate_After)).trim();
	    	PDFReader.commencementDate = PDFReader.commencementDate.trim().replaceAll(" +", " ");
	    }
	    catch(Exception e)
	    {
	    	PDFReader.commencementDate = "Error";
	    	e.printStackTrace();
	    }
	    System.out.println("Commensement Date = "+PDFReader.commencementDate);
	   try
	    {
		   String expirationDateWaw = text.substring(text.indexOf(PDFAppConfig.Florida_Format1.AB_expirationDate_Prior)+PDFAppConfig.Florida_Format1.AB_expirationDate_Prior.length());
		   PDFReader.expirationDate =expirationDateWaw.substring(0,expirationDateWaw.indexOf(PDFAppConfig.Florida_Format1.AB_expirationDate_After)).trim();
		   PDFReader.expirationDate = PDFReader.expirationDate.trim().replaceAll(" +", " ");
	    }
	    catch(Exception e)
	    {
	    	 PDFReader.expirationDate = "Error";
	    	 e.printStackTrace();
	    }
	   System.out.println("Expiration Date = "+PDFReader.expirationDate);

	   try
	    {
		    PDFReader.monthlyRent = text.substring(text.indexOf(PDFAppConfig.Florida_Format1.AB_fullRent_Prior)+PDFAppConfig.Florida_Format1.AB_fullRent_Prior.length()).trim().split(" ")[0].trim();//,text.indexOf(PDFAppConfig.Florida_Format1.AB_fullRent_After)).substring(1).replaceAll("[^.0-9]", "");;
		    if(CommonMethods.onlyDigits(PDFReader.monthlyRent.replace(".", "").replace(",", ""))==false)
		    {
		    	PDFReader.monthlyRent = text.substring(text.indexOf(PDFAppConfig.Florida_Format1.AB_fullRent2_Prior)+PDFAppConfig.Florida_Format1.AB_fullRent2_Prior.length()).trim().split(" ")[0].trim();
		    }
		    if(PDFReader.monthlyRent.contains("*"))
		    {
		    	PDFReader.monthlyRent = PDFReader.monthlyRent.replace("*","");
		    }
		    if(PDFReader.monthlyRent.matches(".*[a-zA-Z]+.*"))
		    {
		    	PDFReader.monthlyRent = "Error";
		    }
		    if(PDFReader.monthlyRent.endsWith(","))
		    {
		    	PDFReader.monthlyRent = PDFReader.monthlyRent.substring(0,PDFReader.monthlyRent.length()-1);
		    }
	    }
	    catch(Exception e)
	    {
	    	 PDFReader.monthlyRent = "Error";
	    	 e.printStackTrace();
	    }
	    System.out.println("Monthly Rent "+PDFReader.monthlyRent.trim());
	    
	    PDFReader.petFlag = text.contains(PDFAppConfig.Florida_Format1.AB_petAgreementAvailabilityCheck);
	    System.out.println("Pet Addendum Available = "+PDFReader.petFlag);
	    if(PDFReader.petFlag ==true)
	    {
	    	PDFReader.petFlag = true;
	    	try
		    {
	    		 PDFReader.petRent = text.substring(text.indexOf(PDFAppConfig.Florida_Format1.AB_petRent_Prior)+PDFAppConfig.Florida_Format1.AB_petRent_Prior.length()).trim().split(" ")[0];
	    		 if(PDFReader.petRent.contains(",for"))
	    		 {
	    			 PDFReader.petRent = PDFReader.petRent.split(",")[0].trim();
	    		 }
	    		 else
	    		 {
		    		 if(PDFReader.petRent.matches(".*[a-zA-Z]+.*")==true)
		    			 PDFReader.petRent = text.substring(text.indexOf(PDFAppConfig.Florida_Format1.AB_petRent1_Prior)+PDFAppConfig.Florida_Format1.AB_petRent1_Prior.length()).trim().split(" ")[0];
		    		 else 
		    		 PDFReader.petRent = CommonMethods.extractNumber(PDFReader.petRent);
	    		 }
		    }
	    	catch(Exception e)
		    {
	    		try
	    		{
	    			e.printStackTrace();
	    			PDFReader.petRent = text.substring(text.indexOf(PDFAppConfig.Florida_Format1.AB_petRent1_Prior)+PDFAppConfig.Florida_Format1.AB_petRent1_Prior.length()).trim().split(" ")[0];
//					 System.out.println("Pet rent = "+PDFReader.petRent.trim());
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
		    }
	    	System.out.println("Pet rent = "+PDFReader.petRent.trim());
	    }
	    
		return true;
	}


}
